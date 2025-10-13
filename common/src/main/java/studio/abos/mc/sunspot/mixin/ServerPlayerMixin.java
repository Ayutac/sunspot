package studio.abos.mc.sunspot.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;

import java.util.Set;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z", at = @At("HEAD"))
    void sunspot$setGravity(final ServerLevel serverLevel, final double d, final double e, final double f, final Set<RelativeMovement> set, final float g, final float h, final CallbackInfoReturnable<Boolean> cir) {
        final ServerPlayer player = (ServerPlayer)(Object)this;
        if (serverLevel == player.level()) {
            return;
        }
        if (serverLevel.dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY && !player.isSpectator()) {
            player.setNoGravity(true);
        }
        else if (player.level().dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY && !player.isSpectator()) {
            player.setNoGravity(false);
        }
    }
}
