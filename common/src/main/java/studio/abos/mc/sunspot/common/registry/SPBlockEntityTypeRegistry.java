package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ImpelBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeCreativeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.RevitaliseBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.SustainBlockEntity;

import java.util.HashMap;
import java.util.Map;

public final class SPBlockEntityTypeRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<ComposeCreativeBlockEntity>> COMPOSE_CREATIVE = BLOCK_ENTITY_TYPE_REGISTRY.register(
            "compose_creative", () -> BlockEntityType.Builder.of(ComposeCreativeBlockEntity::new, SPBlockRegistry.COMPOSE_CREATIVE.get()).build(null)
    );

    public static final Map<RegistrySupplier<GlyphType>, RegistrySupplier<BlockEntityType<?>>> GLYPH_MAP = new HashMap<>();

    static {
        registerGlyphBlockEntity(Identifiers.AFFIX, SPGlyphTypeRegistry.AFFIX, AffixBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.ASH, SPGlyphTypeRegistry.ASH, AshBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.COMPOSE, SPGlyphTypeRegistry.COMPOSE, ComposeBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.IMPEL, SPGlyphTypeRegistry.IMPEL, ImpelBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.OFFSET, SPGlyphTypeRegistry.OFFSET, OffsetBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.REVITALISE, SPGlyphTypeRegistry.REVITALISE, RevitaliseBlockEntity::new);
        registerGlyphBlockEntity(Identifiers.SUSTAIN, SPGlyphTypeRegistry.SUSTAIN, SustainBlockEntity::new);
    }

    private static <T extends BlockEntity> void registerGlyphBlockEntity(final @NotNull ResourceLocation id, final @NotNull RegistrySupplier<GlyphType> glyphType, final @NotNull BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier) {
        GLYPH_MAP.put(glyphType,
            BLOCK_ENTITY_TYPE_REGISTRY.register(
                    id,
                    () -> BlockEntityType.Builder.of(blockEntitySupplier, SPBlockRegistry.GLYPH_MAP.get(glyphType).get()).build(null)
            )
        );
    }

    public static void register() {
        BLOCK_ENTITY_TYPE_REGISTRY.register();
    }

    private SPBlockEntityTypeRegistry() {
        /* No instantiation */
    }
}
