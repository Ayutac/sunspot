package studio.abos.mc.sunspot.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.event.SPServerEvents;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    private Vec3 deltaMovement;

    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z", at = @At("HEAD"))
    void sunspot$setGravity(final ServerLevel serverLevel, final double d, final double e, final double f, final Set<RelativeMovement> set, final float g, final float h, final @NotNull CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity)(Object)this;
        if (serverLevel == entity.level()) {
            return;
        }
        if (serverLevel.dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY) {
            entity.setNoGravity(true);
        }
        else if (entity.level().dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY) {
            entity.setNoGravity(false);
        }
    }

    @Inject(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), cancellable = true)
    void sunspot$affix(final Vec3 vec3, final @NotNull CallbackInfo ci) {
        final Entity entity = (Entity)(Object)this;
        final BlockState blockBelow = entity.level().getBlockState(entity.blockPosition().below());
        if (!entity.getType().is(SPTagRegistry.UNAFFECTED_BY_AFFIX) &&
                Util.isAffix(blockBelow) &&
                blockBelow.getValue(GlyphBlock.POWERED)) {
            deltaMovement = Vec3.ZERO;
            final Vec3 oldPos = entity.position();
            if (oldPos.y() != Math.floor(oldPos.y())) {
                entity.setPos(oldPos.x(), Math.floor(oldPos.y()), oldPos.z());
            }
            ci.cancel();
        }
    }

    @Inject(method = "isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z", at = @At("RETURN"), cancellable = true)
    void sunspot$ashInvulnerability(final @NotNull DamageSource damageSource, final @NotNull CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity)(Object)this;
        if (!cir.getReturnValueZ() && entity instanceof final ItemEntity itemEntity && itemEntity.getItem().is(SPTagRegistry.UNAFFECTED_BY_ASH_TRANSFORMATION) && Util.isOfDamageType(damageSource, SPDamageTypeRegistry.ASH, entity.level())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    void sunspot$impelTick(final @NotNull CallbackInfo ci) {
        SPServerEvents.impelTick((Entity)(Object)this);
    }

}
