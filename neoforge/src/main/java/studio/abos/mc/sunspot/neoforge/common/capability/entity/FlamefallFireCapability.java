package studio.abos.mc.sunspot.neoforge.common.capability.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;

public class FlamefallFireCapability extends CommonFlamefallFireComponent implements INBTSerializable<CompoundTag> {

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
