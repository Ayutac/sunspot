package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.inventory.ImplementedInventory;
import studio.abos.mc.sunspot.common.inventory.SeverMenu;
import studio.abos.mc.sunspot.common.inventory.WorkbenchMenu;
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipe;
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipeInput;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

public class WorkbenchBlockEntity extends BlockEntity implements ImplementedInventory, WorldlyContainer, MenuProvider {

    public static final String PROGRESS_KEY = "progress";

    protected final @NotNull NonNullList<ItemStack> items = NonNullList.withSize(WorkbenchMenu.SLOT_COUNT, ItemStack.EMPTY);
    protected final @NotNull ContainerData containerData = new SimpleContainerData(1);
    protected final @NotNull RecipeManager.CachedCheck<WorkbenchRecipeInput, ? extends WorkbenchRecipe> quickCheck;

    public WorkbenchBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(SPBlockEntityTypeRegistry.WORKBENCH.get(), blockPos, blockState);
        quickCheck = RecipeManager.createCheck(SPRecipeRegistry.WORKBENCH_TYPE.get());
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new WorkbenchMenu(i, inventory, this);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Workbench");
    }

    @Override
    public int @NotNull [] getSlotsForFace(final Direction direction) {
        return new int[] { WorkbenchMenu.INPUT_SLOT, WorkbenchMenu.INTENT_SLOT, WorkbenchMenu.OUTPUT_SLOT };
    }

    @Override
    public boolean canPlaceItemThroughFace(final int slotIndex, final @NotNull ItemStack itemStack, final @Nullable Direction direction) {
        return slotIndex != WorkbenchMenu.OUTPUT_SLOT;
    }

    @Override
    public boolean canTakeItemThroughFace(final int slotIndex, final @NotNull ItemStack itemStack, final Direction direction) {
        return slotIndex == WorkbenchMenu.OUTPUT_SLOT;
    }

    protected @NotNull WorkbenchRecipeInput getCurrentInput() {
        return new WorkbenchRecipeInput(items.get(WorkbenchMenu.INPUT_SLOT), items.get(WorkbenchMenu.INTENT_SLOT));
    }

    protected @Nullable RecipeHolder<? extends WorkbenchRecipe> getRecipeHolder() {
        return quickCheck.getRecipeFor(getCurrentInput(), getLevel()).orElse(null);
    }

    @Override
    public void setChanged() {
        final var recipeHolder = getRecipeHolder();
        if (recipeHolder != null) {
            items.set(WorkbenchMenu.OUTPUT_SLOT, recipeHolder.value().assemble(getCurrentInput(), getLevel().registryAccess()));
        }
        else {
            items.set(WorkbenchMenu.OUTPUT_SLOT, ItemStack.EMPTY);
        }
        super.setChanged();
    }

    @Override
    public @NotNull ItemStack removeItem(final int slot, final int count) {
        final ItemStack removedItem = ImplementedInventory.super.removeItem(slot, count);
        if (removedItem.isEmpty()) {
            return removedItem;
        }
        if (slot == WorkbenchMenu.OUTPUT_SLOT) {
            items.get(WorkbenchMenu.INPUT_SLOT).shrink(1);
        }
        setChanged();
        return removedItem;
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull WorkbenchBlockEntity workbenchEntity) {

    }

    @Override
    protected void loadAdditional(final @NotNull CompoundTag tag, final @NotNull HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);
        ContainerHelper.loadAllItems(tag, items, lookup);
        containerData.set(SeverMenu.PROGRESS_DATA_SLOT, tag.getInt(PROGRESS_KEY));
    }

    @Override
    protected void saveAdditional(final @NotNull CompoundTag tag, final @NotNull HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        ContainerHelper.saveAllItems(tag, items, lookup);
        tag.putInt(PROGRESS_KEY, containerData.get(WorkbenchMenu.PROGRESS_DATA_SLOT));
    }
}
