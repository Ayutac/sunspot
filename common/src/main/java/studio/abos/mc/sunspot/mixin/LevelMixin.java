package studio.abos.mc.sunspot.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.blockentity.FlameBlockEntity;

@Mixin(Level.class)
public class LevelMixin {

    @Inject(method = "destroyBlock(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;I)Z", at = @At("RETURN"))
    void sunspot$recalculateNetwork(final @NotNull BlockPos blockPos, final boolean bl, final @Nullable Entity entity, final int i, final @NotNull CallbackInfoReturnable<Boolean> cir) {
        final Level level = (Level)(Object)this;
        if (cir.getReturnValueZ() && level.getBlockEntity(blockPos) instanceof FlameBlockEntity) {
            Util.invalidateNetworkCacheAround(level, blockPos);
            Util.buildNetworkCacheAround(level, blockPos);
        }
    }

}
