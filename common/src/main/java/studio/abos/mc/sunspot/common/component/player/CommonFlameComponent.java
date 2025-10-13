package studio.abos.mc.sunspot.common.component.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public abstract class CommonFlameComponent {

    public static final int FLAME_MAX = 100;

    public static final String FLAME_KEY = "flame";

    public static final String FLAMETOUCHED_KEY = "flametouched";

    protected int flame;

    protected boolean flametouched;

    public int getFlame() {
        return flame;
    }

    public void setFlame(final int flame) {
        if (flame < 0) {
            throw new IllegalArgumentException("Flame amount cannot be negative!");
        }
        if (flame > FLAME_MAX) {
            throw new IllegalArgumentException("Flame amount cannot be that big!");
        }
        this.flame = flame;
    }

    public void setFlame(final int flame, final @NotNull ServerPlayer player) {
        setFlame(flame);
    }

    public boolean isFlametouched() {
        return flametouched;
    }

    public void setFlametouched(final boolean flametouched) {
        this.flametouched = flametouched;
        if (!flametouched) {
            setFlame(0);
        }
    }

    public void setFlametouched(final boolean flametouched, final @NotNull ServerPlayer player) {
        setFlametouched(flametouched);
    }

    public void writeNbt(final CompoundTag nbt) {
        nbt.putBoolean(FLAMETOUCHED_KEY, isFlametouched());
        if (isFlametouched()) {
            nbt.putInt(FLAME_KEY, getFlame());
        }
    }

    public void readNbt(final CompoundTag nbt) {
        setFlametouched(nbt.getBoolean(FLAMETOUCHED_KEY));
        if (isFlametouched()) {
            setFlame(Mth.clamp(0, nbt.getInt(FLAME_KEY), FLAME_MAX));
        }
    }
}
