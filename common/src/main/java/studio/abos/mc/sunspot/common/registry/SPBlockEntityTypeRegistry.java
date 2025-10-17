package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ImpelBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeCreativeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.RevitaliseBlockEntity;

public interface SPBlockEntityTypeRegistry {

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    RegistrySupplier<BlockEntityType<AffixBlockEntity>> AFFIX = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "affix", () -> BlockEntityType.Builder.of(AffixBlockEntity::new, SPBlockRegistry.AFFIX.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<AshBlockEntity>> ASH = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "ash", () -> BlockEntityType.Builder.of(AshBlockEntity::new, SPBlockRegistry.ASH.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<ComposeBlockEntity>> COMPOSE = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "compose_battery", () -> BlockEntityType.Builder.of(ComposeBlockEntity::new, SPBlockRegistry.COMPOSE.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<ComposeCreativeBlockEntity>> COMPOSE_CREATIVE = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "compose_creative", () -> BlockEntityType.Builder.of(ComposeCreativeBlockEntity::new, SPBlockRegistry.COMPOSE_CREATIVE.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<ImpelBlockEntity>> IMPEL = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "impel", () -> BlockEntityType.Builder.of(ImpelBlockEntity::new, SPBlockRegistry.IMPEL.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<OffsetBlockEntity>> OFFSET = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "offset", () -> BlockEntityType.Builder.of(OffsetBlockEntity::new, SPBlockRegistry.OFFSET.get()).build(null)
    );
    RegistrySupplier<BlockEntityType<RevitaliseBlockEntity>> REVITALISE = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "revitalise", () -> BlockEntityType.Builder.of(RevitaliseBlockEntity::new, SPBlockRegistry.REVITALISE.get()).build(null)
    );

    static void register() {
        BLOCK_ENTITY_TYPE_REGISTRY.register();
    }
}
