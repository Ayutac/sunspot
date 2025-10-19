package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.inventory.ImplementedInventory;
import studio.abos.mc.sunspot.common.inventory.SeverMenu;
import studio.abos.mc.sunspot.common.recipe.SeverRecipe;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

public class SeverBlockEntity extends GlyphBlockEntity implements ImplementedInventory, MenuProvider {

    public static final String PROGRESS_KEY = "progress";

    public static final int TOTAL_SEVER_TIME = 24;

    protected final @NotNull NonNullList<ItemStack> items = NonNullList.withSize(SeverMenu.SLOT_COUNT, ItemStack.EMPTY);
    protected final @NotNull ContainerData containerData = new SimpleContainerData(1);

    protected final RecipeManager.CachedCheck<SingleRecipeInput, ? extends SeverRecipe> quickCheck;

    public SeverBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(Util.getSeverBET(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected SeverBlockEntity(final @NotNull BlockEntityType<? extends SeverBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        quickCheck = RecipeManager.createCheck(SPRecipeRegistry.SEVER_TYPE.get());
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(SPGlyphTypeRegistry.SEVER.get().getTranslation());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(final int index, final @NotNull Inventory inventory, final @NotNull Player player) {
        return new SeverMenu(index, inventory, this, containerData);
    }

    public int getSeverTicks() {
        return containerData.get(SeverMenu.PROGRESS_DATA_SLOT);
    }

    public void increaseSeverTicks() {
        containerData.set(SeverMenu.PROGRESS_DATA_SLOT, getSeverTicks() + 1);
    }

    public void resetSeverTicks() {
        containerData.set(SeverMenu.PROGRESS_DATA_SLOT, 0);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull SeverBlockEntity severEntity) {
        GlyphBlockEntity.tick(level, pos, state, severEntity);
        final ItemStack input = severEntity.items.get(SeverMenu.INPUT_SLOT);
        if (!severEntity.isPowered() || input.isEmpty()) {
            return;
        }
        final var recipeHolder = severEntity.quickCheck.getRecipeFor(new SingleRecipeInput(input), level).orElse(null);
        final int maxStackSize = severEntity.getMaxStackSize();
        if (canSever(level.registryAccess(), recipeHolder, severEntity.items, maxStackSize)) {
            severEntity.increaseSeverTicks();
            if (severEntity.getSeverTicks() == TOTAL_SEVER_TIME) {
                severEntity.resetSeverTicks();
                if (sever(level.registryAccess(), recipeHolder, severEntity.items, maxStackSize)) {
                    /* Cache the recipe? See AbstractFurnaceBlockEntity and RecipeCraftingHolder for more details */
                }
            }
        }
    }

    public static boolean canSever(final @NotNull RegistryAccess registryAccess, final @Nullable RecipeHolder<?> recipeHolder, final @NotNull NonNullList<ItemStack> containerItems, final int maxStackSize) {
        if (recipeHolder == null || containerItems.get(SeverMenu.INPUT_SLOT).isEmpty()) {
            return false;
        }
        final ItemStack potentialResult = recipeHolder.value().getResultItem(registryAccess);
        if (potentialResult.isEmpty()) {
            return false;
        } else {
            final ItemStack output = containerItems.get(SeverMenu.OUTPUT_SLOT);
            if (output.isEmpty()) {
                return true;
            } else if (!ItemStack.isSameItemSameComponents(output, potentialResult)) {
                return false;
            } else {
                return (output.getCount() < maxStackSize && output.getCount() < output.getMaxStackSize()) || output.getCount() < potentialResult.getMaxStackSize();
            }
        }
    }

    private static boolean sever(final @NotNull RegistryAccess registryAccess, final @NotNull RecipeHolder<? extends SeverRecipe> recipeHolder, final @NotNull NonNullList<ItemStack> items, final int maxStackSize) {
        final ItemStack input = items.get(SeverMenu.INPUT_SLOT);
        final ItemStack result = recipeHolder.value().getResultItem(registryAccess);
        final ItemStack output = items.get(SeverMenu.OUTPUT_SLOT);
        if (output.isEmpty()) {
            items.set(SeverMenu.OUTPUT_SLOT, result.copy());
        } else if (ItemStack.isSameItemSameComponents(output, result)) {
            output.grow(result.getCount());
        }
        input.shrink(1);
        return true;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);
        ContainerHelper.loadAllItems(tag, items, lookup);
        containerData.set(SeverMenu.PROGRESS_DATA_SLOT, tag.getInt(PROGRESS_KEY));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        ContainerHelper.saveAllItems(tag, items, lookup);
        tag.putInt(PROGRESS_KEY, containerData.get(SeverMenu.PROGRESS_DATA_SLOT));
    }
}
