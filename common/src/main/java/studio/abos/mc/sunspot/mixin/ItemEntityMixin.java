package studio.abos.mc.sunspot.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Shadow
    private int health;

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("RETURN"))
    void sunspot$ashResidue(final @NotNull DamageSource damageSource, final float f, final @NotNull CallbackInfoReturnable<Boolean> cir) {
        final ItemEntity entity = (ItemEntity)(Object)this;
        final Level level = entity.level();
        if (cir.getReturnValueZ() && !level.isClientSide() && health <= 0 && Util.isOfDamageType(damageSource, SPDamageTypeRegistry.ASH, entity.level())) {
            final Vec3 pos = entity.position();
            level.addFreshEntity(new ItemEntity(level, pos.x(), pos.y(), pos.z(), new ItemStack(SPItemPreRegistry.ASH_RESIDUE, entity.getItem().getCount())));
        }
    }

}
