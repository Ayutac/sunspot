package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.blockentity.LmBatteryBlockEntity;

public interface SPBlockEntityTypeRegistry {

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    RegistrySupplier<BlockEntityType<LmBatteryBlockEntity>> LM_BATTERY = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "lm_battery", () -> BlockEntityType.Builder.of(LmBatteryBlockEntity::new, SPBlockRegistry.LM_BATTERY.get()).build(null)
    );

    static void register() {
        BLOCK_ENTITY_TYPE_REGISTRY.register();
    }
}
