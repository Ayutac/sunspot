package studio.abos.mc.sunspot.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "setHealth(F)V", at = @At("HEAD"))
    void sunspot$increaseFlame(final float amount, final CallbackInfo ci) {
        final LivingEntity entity = (LivingEntity)(Object)this;
        // theoretically we could make an instanceof check here already, but code readability
        final float health = entity.getHealth();
        final float realAmount = Mth.clamp(amount, 0.0F, entity.getMaxHealth());
        if (realAmount < health) {
            final int flameAmount = (int)Math.floor(health - realAmount);
            if (entity instanceof final Mob mob) {
                final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(mob);
                if (flame != null && flame.isFlametouched()) {
                    flame.setFlame(CommonFlameComponent.clamp(flame.getFlame() + flameAmount));
                }
            }
            else if (entity instanceof final ServerPlayer player) {
                final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(player);
                if (flame != null && flame.isFlametouched()) {
                    flame.setFlame(CommonFlameComponent.clamp(flame.getFlame() + flameAmount), player);
                }
            }
        }
    }

    @Inject(method = "dropAllDeathLoot(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At("HEAD"), cancellable = true)
    void sunspot$doNotDropLootIfAshed(final @NotNull ServerLevel serverLevel, final @NotNull DamageSource damageSource, final @NotNull CallbackInfo ci) {
        final LivingEntity entity = (LivingEntity)(Object)this;
        if (!(entity instanceof Player) && Util.isOfDamageType(damageSource, SPDamageTypeRegistry.ASH, entity.level())) {
            ci.cancel();
        }
    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("RETURN"))
    void sunspot$dropAshOnHurt(final @NotNull DamageSource damageSource, final float f, final @NotNull CallbackInfoReturnable<Boolean> cir) {
        final LivingEntity entity = (LivingEntity)(Object)this;
        final Level level = entity.level();
        if (cir.getReturnValueZ() && !level.isClientSide() && Util.isOfDamageType(damageSource, SPDamageTypeRegistry.ASH, level)) {
            final Vec3 pos = entity.position();
            level.addFreshEntity(new ItemEntity(level, pos.x(), pos.y(), pos.z(), new ItemStack(SPItemPreRegistry.ASH_RESIDUE)));
        }
    }

}
