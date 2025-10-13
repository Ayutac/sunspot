package studio.abos.mc.sunspot.fabric.common.component.player;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import studio.abos.mc.sunspot.common.component.player.CommonFlameComponent;

public class FlameComponent extends CommonFlameComponent implements AutoSyncedComponent {

    protected final Player player;

    public FlameComponent(final @NotNull Player player) {
        super();
        this.player = player;
    }

    @Override
    public void readFromNbt(final CompoundTag compoundTag, final HolderLookup.Provider lookup) {
        readNbt(compoundTag);
    }

    @Override
    public void writeToNbt(final CompoundTag compoundTag, final HolderLookup.Provider lookup) {
        writeNbt(compoundTag);
    }

    @Override
    public boolean shouldSyncWith(final ServerPlayer player) {
        return this.player == player;
    }
}
