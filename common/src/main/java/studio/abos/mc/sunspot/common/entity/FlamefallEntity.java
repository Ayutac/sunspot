package studio.abos.mc.sunspot.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.event.SPServerEvents;

public class FlamefallEntity extends Mob {

    public static final String TARGET_KEY = "target";

    public static final double SPEED = 5d;

    protected boolean primed;

    protected Player target;

    public FlamefallEntity(final @NotNull EntityType<? extends FlamefallEntity> entityType, final @NotNull Level level) {
        super(entityType, level);
        setPersistenceRequired();
        setNoGravity(true);
        noPhysics = true;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(final Player target) {
        this.target = target;
        if (target != null) {
            primed = true;
        }
    }

    @Override
    public void readAdditionalSaveData(final @NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        try {
            setTarget(level().getPlayerByUUID(compoundTag.getUUID(TARGET_KEY)));
        }
        catch (final NullPointerException | IllegalArgumentException ex) {
            Sunspot.LOGGER.warn("Target UUID missing!", ex);
        }
    }

    @Override
    public void addAdditionalSaveData(final @NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if (getTarget() != null) {
            compoundTag.putUUID(TARGET_KEY, getTarget().getUUID());
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            if (primed && (target == null || target.isSpectator() || ((ServerPlayer)target).hasDisconnected())) {
                discard();
            }
            else if (primed) {
                final Vec3 rawDirection = target.position().subtract(position());
                final double distance = rawDirection.length();
                if (distance <= SPEED) {
                    if (!target.isSpectator())  {
                        SPServerEvents.ignite(target);
                    }
                    discard();
                }
                else {
                    setDeltaMovement(rawDirection.multiply(SPEED / distance, SPEED / distance, SPEED / distance));
                }
            }
        }
    }
}
