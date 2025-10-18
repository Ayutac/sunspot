package studio.abos.mc.sunspot.common.event;

import dev.architectury.event.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.blockentity.ImpelBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.common.entity.FlamefallEntity;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

import java.util.Optional;

public final class SPServerEvents {

    private SPServerEvents() {
        /* No instantiation */
    }

    public static void serverPostTick(final MinecraftServer server) {
        final var damageTypes = server.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
        for (final ServerLevel level : server.getAllLevels()) {
            for (final Entity entity : level.getAllEntities()) {
                // hurt ignited entities
                flamefallFireTick(entity, damageTypes);
            }
        }
    }

    public static void ignite(final @NotNull Entity entity) {
        final CommonFlamefallFireComponent flamefallFire = SPComponentPlatformUtils.getFlamefallFireData(entity);
        if (flamefallFire != null) {
            flamefallFire.setRemainingFireTicks(CommonFlamefallFireComponent.DEFAULT_DURATION);
        }
    }

    public static void flamefallFireTick(final @NotNull Entity entity, final @NotNull Registry<DamageType> damageTypes) {
        final CommonFlamefallFireComponent flamefallFire = SPComponentPlatformUtils.getFlamefallFireData(entity);
        if (flamefallFire == null || flamefallFire.getRemainingFireTicks() == 0) {
            return;
        }
        if (entity instanceof final LivingEntity living && living.isAlive() && flamefallFire.getRemainingFireTicks() % CommonFlamefallFireComponent.TICKS_FOR_DAMAGE == 0) {
            living.hurt(new DamageSource(damageTypes.getHolderOrThrow(SPDamageTypeRegistry.FLAMEFALL_FIRE)), CommonFlamefallFireComponent.DAMAGE);
        }
        flamefallFire.decreaseRemainingFireTicks(entity);
        if (flamefallFire.getRemainingFireTicks() == 0 && entity instanceof final LivingEntity living && living.isAlive()) {
            bestowFlame(living);
        }
    }

    /**
     * This method assumes that the entity is alive.
     */
    private static void bestowFlame(final @NotNull LivingEntity living) {
        if (living instanceof final Mob mob) {
            final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(mob);
            if (flame != null && !flame.isFlametouched()) {
                flame.setFlametouched(true);
            }
        } else if (living instanceof final ServerPlayer player) {
            final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(player);
            if (flame != null && !flame.isFlametouched()) {
                flame.setFlametouched(true, player);
            }
        }
    }

    public static EventResult inferno(final @NotNull LivingEntity living, final DamageSource damageSource) {
        final Level level = living.level();
        if (damageSource == null || !Util.isOfDamageType(damageSource, SPDamageTypeRegistry.FLAMEFALL_FIRE, living.level())) {
            return EventResult.pass();
        }
        if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            final BlockPos blockPos = living.blockPosition();
            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    if (x*x + z*z > 25) {
                        continue;
                    }
                    for (int y = -3; y <= 3; y++) {
                        final BlockPos blockPos2 = blockPos.offset(x, y, z);
                        final BlockState blockState = BaseFireBlock.getState(level, blockPos2);
                        if (level.getBlockState(blockPos2).isAir() && blockState.canSurvive(level, blockPos2)) {
                            level.setBlockAndUpdate(blockPos2, blockState);
                        }
                    }
                }
            }
        }
        return EventResult.pass();
    }

    public static void summonFlamefall(final @NotNull ServerPlayer player) {
        final FlamefallEntity flamefall = new FlamefallEntity(SPEntityTypeRegistry.FLAMEFALL.get(), player.level());
        flamefall.setPos(player.position().x(), player.level().getMaxBuildHeight(), player.position().z());
        flamefall.setTarget(player);
        player.level().addFreshEntity(flamefall);
    }

    public static void sendPlayerUpOnOffsetBlock(final @NotNull ServerPlayer player, final @NotNull BlockPos pos) {
        final BlockEntity blockEntity = player.level().getBlockEntity(pos);
        if (blockEntity instanceof final OffsetBlockEntity offsetBlockEntity) {
            final Optional<BlockPos> target = OffsetBlockEntity.nextUpElevator(pos, player.level());
            if (target.isEmpty()) {
                return;
            }
            offsetBlockEntity.teleport(player, target.get().above());
        }
    }

    public static void impelTick(final @NotNull Entity entity) {
        if (entity.getType().is(SPTagRegistry.UNAFFECTED_BY_IMPEL)) {
            return;
        }
        final BlockPos pos = entity.getOnPos().above();
        final Level level = entity.level();
        Vec3 delta = Vec3.ZERO;
        BlockPos neighbor = pos.east();
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(-ImpelBlockEntity.VELOCITY, 0d, 0d);
        }
        neighbor = pos.west();
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(ImpelBlockEntity.VELOCITY, 0d, 0d);
        }
        neighbor = pos.north();
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(0d, 0d, ImpelBlockEntity.VELOCITY);
        }
        neighbor = pos.south();
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(0d, 0d, -ImpelBlockEntity.VELOCITY);
        }
        neighbor = pos.above(2);
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(0d, -ImpelBlockEntity.VELOCITY, 0d);
        }
        neighbor = pos.below();
        if (level.getBlockEntity(neighbor) instanceof final ImpelBlockEntity impel && impel.isPowered()) {
            delta = delta.add(0d, ImpelBlockEntity.VELOCITY, 0d);
        }
        if (delta != Vec3.ZERO) {
            entity.setDeltaMovement(delta);
            if (entity instanceof Player) {
                entity.hurtMarked = true;
            }
        }
    }
}
