package studio.abos.mc.sunspot.common.component.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class CommonFourspaceShifterOriginComponent {

    public static final String ORIGIN_KEY = "origin";

    protected ResourceKey<Level> origin;

    public ResourceKey<Level> getOrigin() {
        return origin;
    }

    public void setOrigin(final @Nullable ResourceKey<Level> origin) {
        this.origin = origin;
    }

    public void writeNbt(final CompoundTag nbt) {
        final ResourceKey<Level> origin = getOrigin();
        if (origin == null) {
            return;
        }
        nbt.putString(ORIGIN_KEY, origin.location().toString());
    }

    public void readNbt(final CompoundTag nbt) {
        final String originStr = nbt.getString(ORIGIN_KEY);
        if (originStr.isEmpty()) {
            setOrigin(null);
        }
        setOrigin(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(originStr)));
    }
}
