package studio.abos.mc.sunspot.common.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPMenuTypeRegistry;

public class SeverMenu extends AbstractContainerMenu {

    final @NotNull Container container;
    final @NotNull ContainerLevelAccess containerLevelAccess;

    public SeverMenu(final int containerId, final @NotNull Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public SeverMenu(final int containerId, final @NotNull Inventory playerInventory, final @NotNull ContainerLevelAccess containerLevelAccess) {
        super(SPMenuTypeRegistry.SEVER.get(), containerId);
        container = new SimpleContainer(2);
        this.containerLevelAccess = containerLevelAccess;
        // j is x, k is y, i is index
        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Player Hotbar
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        // Own slots
        addSlot(new Slot(container, 0, 56, 35)); // input
        addSlot(new SeverResultSlot(container, 1, 116, 35)); // output
        addDataSlots(new SimpleContainerData(1)); // conversion time
    }

    @Override
    public @NotNull ItemStack quickMoveStack(final Player player, final int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(final Player player) {
        return container.stillValid(player);
    }
}
