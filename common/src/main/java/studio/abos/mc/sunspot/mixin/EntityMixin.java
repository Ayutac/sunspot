package studio.abos.mc.sunspot.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;

import java.util.Set;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z", at = @At("HEAD"))
    void sunspot$setGravity(final ServerLevel serverLevel, final double d, final double e, final double f, final Set<RelativeMovement> set, final float g, final float h, final CallbackInfoReturnable<Boolean> cir) {
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

}
