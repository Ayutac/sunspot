package studio.abos.mc.sunspot.fabric.common.component.player;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.Component;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;

public class FourspaceShifterOriginComponent extends CommonFourspaceShifterOriginComponent implements Component {

    public FourspaceShifterOriginComponent(final @NotNull Player player) {
        super();
    }

    @Override
    public void readFromNbt(final CompoundTag compoundTag, final HolderLookup.Provider lookup) {
        readNbt(compoundTag);
    }

    @Override
    public void writeToNbt(final CompoundTag compoundTag, final HolderLookup.Provider lookup) {
        writeNbt(compoundTag);
    }
}
