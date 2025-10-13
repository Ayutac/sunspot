package studio.abos.mc.sunspot.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
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


}
