package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.mixin.SimpleParticleTypeInvoker;

public interface SPParticleTypeRegistry {

    DeferredRegister<ParticleType<?>> PARTICLE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.PARTICLE_TYPE);

    RegistrySupplier<SimpleParticleType> FLAMEFALL_FLAME = PARTICLE_REGISTRY.register(
            Sunspot.id("flamefall_flame"), () -> SimpleParticleTypeInvoker.ctor(false));

    static void init() {
        /* Intentionally left empty */
    }
}
