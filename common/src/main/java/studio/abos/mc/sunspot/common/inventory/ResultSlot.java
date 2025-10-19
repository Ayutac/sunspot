package studio.abos.mc.sunspot.common.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ResultSlot extends Slot {

    public ResultSlot(final @NotNull Container container, final int i, final int j, final int k) {
        super(container, i, j, k);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }
}
