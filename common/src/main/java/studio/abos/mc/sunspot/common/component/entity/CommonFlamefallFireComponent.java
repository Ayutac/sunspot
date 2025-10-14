package studio.abos.mc.sunspot.common.component.entity;

import net.minecraft.nbt.CompoundTag;

public abstract class CommonFlamefallFireComponent {

    public static final String FIRE_TICKS_KEY = "fireTicks";

    public static final int TICKS_FOR_DAMAGE = 10;

    public static final int DAMAGE = 4;

    // * 20 would be an entire health bar, and we go a bit beyond
    public static final int DEFAULT_DURATION = TICKS_FOR_DAMAGE * 30 / DAMAGE;

    protected int remainingFireTicks;

    public int getRemainingFireTicks() {
        return remainingFireTicks;
    }

    public void setRemainingFireTicks(final int remainingFireTicks) {
        if (remainingFireTicks < 0) {
            throw new IllegalArgumentException("Remaining Flamefall fire ticks cannot be negative!");
        }
        this.remainingFireTicks = remainingFireTicks;
    }

    public void decreaseRemainingFireTicks() {
        if (remainingFireTicks < 0) {
            remainingFireTicks = 0;
        }
        else if (remainingFireTicks > 0) {
            remainingFireTicks--;
        }
    }

    public void writeNbt(final CompoundTag nbt) {
        if (getRemainingFireTicks() > 0) {
            nbt.putInt(FIRE_TICKS_KEY, getRemainingFireTicks());
        }
    }

    public void readNbt(final CompoundTag nbt) {
        final int ticks = nbt.getInt(FIRE_TICKS_KEY);
        if (ticks > 0) {
            setRemainingFireTicks(ticks);
        }
    }

}
