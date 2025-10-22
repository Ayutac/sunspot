package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
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
import studio.abos.mc.sunspot.common.inventory.ExtractMenu;
import studio.abos.mc.sunspot.common.inventory.ImplementedInventory;
import studio.abos.mc.sunspot.common.recipe.ExtractRecipe;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

public class ExtractBlockEntity extends GlyphBlockEntity implements ImplementedInventory, WorldlyContainer, MenuProvider {

    public static final String PROGRESS_KEY = "progress";

    public static final int TOTAL_EXTRACT_TIME = 24;

    protected final @NotNull NonNullList<ItemStack> items = NonNullList.withSize(ExtractMenu.SLOT_COUNT, ItemStack.EMPTY);
    protected final @NotNull ContainerData containerData = new SimpleContainerData(1);
    protected float rotationDegrees;

    protected final RecipeManager.CachedCheck<SingleRecipeInput, ? extends ExtractRecipe> quickCheck;

    public ExtractBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        this(Util.getExtractBET(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected ExtractBlockEntity(final @NotNull BlockEntityType<? extends ExtractBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        quickCheck = RecipeManager.createCheck(SPRecipeRegistry.EXTRACT_TYPE.get());
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(SPGlyphTypeRegistry.EXTRACT.get().getTranslation());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(final int index, final @NotNull Inventory inventory, final @NotNull Player player) {
        return new ExtractMenu(index, inventory, this, containerData);
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction direction) {
        return new int[] { ExtractMenu.INPUT_SLOT, ExtractMenu.OUTPUT_SLOT };
    }

    @Override
    public boolean canPlaceItemThroughFace(int slotIndex, ItemStack itemStack, @Nullable Direction direction) {
        return slotIndex != ExtractMenu.OUTPUT_SLOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack itemStack, Direction direction) {
        return slotIndex != ExtractMenu.INPUT_SLOT;
    }

    @Override
    public @Nullable ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(final @NotNull HolderLookup.Provider lookup) {
        CompoundTag tag = super.getUpdateTag(lookup);
        saveAdditional(tag, lookup);
        return tag;
    }

    public int getExtractTicks() {
        return containerData.get(ExtractMenu.PROGRESS_DATA_SLOT);
    }

    public void increaseExtractTicks() {
        containerData.set(ExtractMenu.PROGRESS_DATA_SLOT, getExtractTicks() + 1);
    }

    public void resetExtractTicks() {
        containerData.set(ExtractMenu.PROGRESS_DATA_SLOT, 0);
    }

    public float getRotationDegrees() {
        rotationDegrees += 0.5f;
        if (rotationDegrees >= 360f) {
            rotationDegrees = 0f;
        }
        return rotationDegrees;
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull ExtractBlockEntity extractEntity) {
        GlyphBlockEntity.tick(level, pos, state, extractEntity);
        final ItemStack input = extractEntity.items.get(ExtractMenu.INPUT_SLOT);
        extractEntity.setChanged();
        ((ServerLevel)level).getChunkSource().blockChanged(pos);
        if (!extractEntity.isPowered() || input.isEmpty()) {
            return;
        }
        final var recipeHolder = extractEntity.quickCheck.getRecipeFor(new SingleRecipeInput(input), level).orElse(null);
        if (canExtract(level.registryAccess(), recipeHolder, extractEntity.items, extractEntity.getMaxStackSize())) {
            extractEntity.increaseExtractTicks();
            if (extractEntity.getExtractTicks() == TOTAL_EXTRACT_TIME) {
                extractEntity.resetExtractTicks();
                if (extract(level.registryAccess(), recipeHolder, extractEntity.items)) {
                    /* Cache the recipe? See AbstractFurnaceBlockEntity and RecipeCraftingHolder for more details */
                }
            }
        }
    }

    public static boolean canExtract(final @NotNull RegistryAccess registryAccess, final @Nullable RecipeHolder<?> recipeHolder, final @NotNull NonNullList<ItemStack> containerItems, final int maxStackSize) {
        if (recipeHolder == null || containerItems.get(ExtractMenu.INPUT_SLOT).isEmpty()) {
            return false;
        }
        final ItemStack potentialResult = recipeHolder.value().getResultItem(registryAccess);
        if (potentialResult.isEmpty()) {
            return false;
        } else {
            final ItemStack output = containerItems.get(ExtractMenu.OUTPUT_SLOT);
            if (output.isEmpty()) {
                return true;
            } else if (!ItemStack.isSameItemSameComponents(output, potentialResult)) {
                return false;
            } else {
                return (output.getCount() < maxStackSize && output.getCount() < output.getMaxStackSize()) || output.getCount() < potentialResult.getMaxStackSize();
            }
        }
    }

    private static boolean extract(final @NotNull RegistryAccess registryAccess, final @NotNull RecipeHolder<? extends ExtractRecipe> recipeHolder, final @NotNull NonNullList<ItemStack> items) {
        final ItemStack input = items.get(ExtractMenu.INPUT_SLOT);
        final ItemStack result = recipeHolder.value().getResultItem(registryAccess);
        final ItemStack output = items.get(ExtractMenu.OUTPUT_SLOT);
        if (output.isEmpty()) {
            items.set(ExtractMenu.OUTPUT_SLOT, result.copy());
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
        containerData.set(ExtractMenu.PROGRESS_DATA_SLOT, tag.getInt(PROGRESS_KEY));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        ContainerHelper.saveAllItems(tag, items, lookup);
        tag.putInt(PROGRESS_KEY, containerData.get(ExtractMenu.PROGRESS_DATA_SLOT));
    }

}
