package studio.abos.mc.sunspot.common.component.entity;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

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
        else {
            remainingFireTicks--;
        }
    }

    public static void serverTick(final @NotNull Entity entity, final @NotNull Registry<DamageType> damageTypes) {
        final CommonFlamefallFireComponent flamefallFire = SPComponentPlatformUtils.getFlamefallFireData(entity);
        if (flamefallFire == null) {
            return;
        }
        if (entity instanceof LivingEntity living && living.isAlive() && flamefallFire.getRemainingFireTicks() % TICKS_FOR_DAMAGE == 1) {
            living.hurt(new DamageSource(damageTypes.getHolderOrThrow(SPDamageTypeRegistry.FLAMEFALL_FIRE)), DAMAGE);
        }
        flamefallFire.decreaseRemainingFireTicks();
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
