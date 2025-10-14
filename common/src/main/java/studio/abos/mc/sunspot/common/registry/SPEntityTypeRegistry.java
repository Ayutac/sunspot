package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.entity.FlamefallEntity;

public interface SPEntityTypeRegistry {

    DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.ENTITY_TYPE);

    RegistrySupplier<EntityType<FlamefallEntity>> FLAMEFALL = ENTITY_TYPE_REGISTRY.register(Sunspot.id("flamefall"),
            () -> EntityType.Builder
                    .of(FlamefallEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .eyeHeight(0.5f)
                    .fireImmune()
                    .noSummon()
                    .canSpawnFarFromPlayer()
                    .clientTrackingRange(10)
                    .build("flamefall")
    );

    static void register() {
        ENTITY_TYPE_REGISTRY.register();
        EntityAttributeRegistry.register(FLAMEFALL, FlamefallEntity::createMobAttributes);
    }

}
