package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import studio.abos.mc.sunspot.Sunspot;

public interface SPDamageTypeRegistry {

    ResourceKey<DamageType> ASH = ResourceKey.create(Registries.DAMAGE_TYPE, Sunspot.id("ash"));
    ResourceKey<DamageType> SEVER = ResourceKey.create(Registries.DAMAGE_TYPE, Sunspot.id("sever"));
    ResourceKey<DamageType> FLAMEFALL_FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, Sunspot.id("flamefall_fire"));

}
