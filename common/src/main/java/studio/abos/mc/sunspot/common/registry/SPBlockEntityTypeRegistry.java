package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.LmBatteryBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.LmBatteryCreativeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;

public interface SPBlockEntityTypeRegistry {

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    RegistrySupplier<BlockEntityType<LmBatteryBlockEntity>> LM_BATTERY = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "lm_battery", () -> BlockEntityType.Builder.of(LmBatteryBlockEntity::new, SPBlockRegistry.LM_BATTERY.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<LmBatteryCreativeBlockEntity>> LM_BATTERY_CREATIVE = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "lm_battery_creative", () -> BlockEntityType.Builder.of(LmBatteryCreativeBlockEntity::new, SPBlockRegistry.LM_BATTERY_CREATIVE.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<AffixBlockEntity>> AFFIX = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "affix", () -> BlockEntityType.Builder.of(AffixBlockEntity::new, SPBlockRegistry.AFFIX.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<AshBlockEntity>> ASH = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "ash", () -> BlockEntityType.Builder.of(AshBlockEntity::new, SPBlockRegistry.ASH.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<OffsetBlockEntity>> OFFSET = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "offset", () -> BlockEntityType.Builder.of(OffsetBlockEntity::new, SPBlockRegistry.OFFSET.get()).build(null)
    );

    static void register() {
        BLOCK_ENTITY_TYPE_REGISTRY.register();
    }
}
