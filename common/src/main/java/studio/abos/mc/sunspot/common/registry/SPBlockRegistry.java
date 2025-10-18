package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;
import studio.abos.mc.sunspot.common.block.AffixBlock;
import studio.abos.mc.sunspot.common.block.AshBlock;
import studio.abos.mc.sunspot.common.block.ComposeCreativeBlock;
import studio.abos.mc.sunspot.common.block.ImpelBlock;
import studio.abos.mc.sunspot.common.block.ComposeBlock;
import studio.abos.mc.sunspot.common.block.OffsetBlock;
import studio.abos.mc.sunspot.common.block.RevitaliseBlock;
import studio.abos.mc.sunspot.common.block.SeverBlock;
import studio.abos.mc.sunspot.common.block.SmallSubstrateBlock;
import studio.abos.mc.sunspot.common.block.SustainBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class SPBlockRegistry {

    private static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> SUBSTRATE_3 = BLOCK_REGISTRY.register(Sunspot.id("substrate_3"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> SUBSTRATE_4 = BLOCK_REGISTRY.register(Sunspot.id("substrate_4"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> ASH_RESIDUE = BLOCK_REGISTRY.register(Sunspot.id("ash_residue"), () -> new ColoredFallingBlock(new ColorRGBA(-8356741), BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> LM_WORKBENCH = BLOCK_REGISTRY.register(Sunspot.id("lm_workbench"), () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> COMPOSE_CREATIVE = BLOCK_REGISTRY.register(Sunspot.id("compose_creative"), () -> new ComposeCreativeBlock(BlockBehaviour.Properties.of()));

    public static final Map<RegistrySupplier<GlyphType>, RegistrySupplier<Block>> GLYPH_MAP = new HashMap<>();

    static {
        registerGlyphBlock(Identifiers.AFFIX, SPGlyphTypeRegistry.AFFIX, AffixBlock::new);
        registerGlyphBlock(Identifiers.ASH, SPGlyphTypeRegistry.ASH, AshBlock::new);
        registerGlyphBlock(Identifiers.COMPOSE, SPGlyphTypeRegistry.COMPOSE, ComposeBlock::new);
        registerGlyphBlock(Identifiers.IMPEL, SPGlyphTypeRegistry.IMPEL, ImpelBlock::new);
        registerGlyphBlock(Identifiers.OFFSET, SPGlyphTypeRegistry.OFFSET, OffsetBlock::new);
        registerGlyphBlock(Identifiers.REVITALISE, SPGlyphTypeRegistry.REVITALISE, RevitaliseBlock::new);
        registerGlyphBlock(Identifiers.SEVER, SPGlyphTypeRegistry.SEVER, SeverBlock::new);
        registerGlyphBlock(Identifiers.SUSTAIN, SPGlyphTypeRegistry.SUSTAIN, SustainBlock::new);
    }

    private static void registerGlyphBlock(final @NotNull ResourceLocation id, final @NotNull RegistrySupplier<GlyphType> glyphType, final @NotNull Function<BlockBehaviour.Properties, Block> ctor) {
        GLYPH_MAP.put(glyphType,
            BLOCK_REGISTRY.register(
                    id,
                    // all glyph blocks have similar block properties
                    () -> ctor.apply(BlockBehaviour.Properties.of())
            )
        );
    }

    public static void register() {
        BLOCK_REGISTRY.register();
    }

    private SPBlockRegistry() {
        /* No instantiation */
    }

}
