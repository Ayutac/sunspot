package studio.abos.mc.sunspot.neoforge.common.capability.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.neoforge.common.net.FlamefallFireData;

public class FlamefallFireCapability extends CommonFlamefallFireComponent implements INBTSerializable<CompoundTag> {

    @Override
    public void setRemainingFireTicks(int remainingFireTicks, Entity entity) {
        super.setRemainingFireTicks(remainingFireTicks, entity);
        PacketDistributor.sendToAllPlayers(new FlamefallFireData(getRemainingFireTicks(), entity.getId()));
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
