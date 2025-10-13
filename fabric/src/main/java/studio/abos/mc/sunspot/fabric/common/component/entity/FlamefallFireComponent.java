package studio.abos.mc.sunspot.fabric.common.component.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;

public class FlamefallFireComponent extends CommonFlamefallFireComponent implements AutoSyncedComponent {

    protected final Entity entity;

    public FlamefallFireComponent(final @NotNull Entity entity) {
        super();
        this.entity = entity;
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
