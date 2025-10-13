package studio.abos.mc.sunspot.neoforge.common.capability.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.component.player.CommonFlameComponent;
import studio.abos.mc.sunspot.neoforge.common.net.FlameData;

public class FlameCapability extends CommonFlameComponent implements INBTSerializable<CompoundTag> {

    public void setFlame(final int flame, final @NotNull ServerPlayer player) {
        super.setFlame(flame, player);
        PacketDistributor.sendToPlayer(player, new FlameData(isFlametouched(), getFlame()));
    }

    @Override
    public void setFlametouched(final boolean flametouched, final @NotNull ServerPlayer player) {
        super.setFlametouched(flametouched, player);
        PacketDistributor.sendToPlayer(player, new FlameData(isFlametouched(), getFlame()));
    }

    @Override
    public @NotNull CompoundTag serializeNBT(final @NotNull HolderLookup.Provider lookup) {
        final CompoundTag nbt = new CompoundTag();
        writeNbt(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(final @NotNull HolderLookup.Provider lookup, final @NotNull CompoundTag nbt) {
        readNbt(nbt);
    }
}
