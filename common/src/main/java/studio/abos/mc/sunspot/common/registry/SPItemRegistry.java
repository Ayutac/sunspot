package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;

import java.util.HashMap;
import java.util.Map;

public final class SPItemRegistry {

    private static final DeferredRegister<Item> ITEM_REGISTRY = SPItemPreRegistry.ITEM_REGISTRY;

    public static final RegistrySupplier<BlockItem> COMPOSE_CREATIVE_BLOCK = ITEM_REGISTRY.register(Sunspot.id("compose_creative_block"), () -> new BlockItem(SPBlockRegistry.COMPOSE_CREATIVE.get(), new Item.Properties()));

    public static final Map<RegistrySupplier<GlyphType>, RegistrySupplier<BlockItem>> GLYPH_BLOCK_MAP = new HashMap<>();

    static {
        registerGlyphItemBlock(Identifiers.AFFIX_BLOCK, SPGlyphTypeRegistry.AFFIX);
        registerGlyphItemBlock(Identifiers.ASH_BLOCK, SPGlyphTypeRegistry.ASH);
        registerGlyphItemBlock(Identifiers.COMPOSE_BLOCK, SPGlyphTypeRegistry.COMPOSE);
        registerGlyphItemBlock(Identifiers.IMPEL_BLOCK, SPGlyphTypeRegistry.IMPEL);
        registerGlyphItemBlock(Identifiers.OFFSET_BLOCK, SPGlyphTypeRegistry.OFFSET);
        registerGlyphItemBlock(Identifiers.REVITALISE_BLOCK, SPGlyphTypeRegistry.REVITALISE);
        registerGlyphItemBlock(Identifiers.SUSTAIN_BLOCK, SPGlyphTypeRegistry.SUSTAIN);
    }

    private static void registerGlyphItemBlock(final @NotNull ResourceLocation id, final @NotNull RegistrySupplier<GlyphType> glyphType) {
        GLYPH_BLOCK_MAP.put(glyphType,
            ITEM_REGISTRY.register(
                id,
                () -> new BlockItem(SPBlockRegistry.GLYPH_MAP.get(glyphType).get(), new Item.Properties())
            )
        );
    }

    public static void register() {
        ITEM_REGISTRY.register();
    }

    private SPItemRegistry() {
        /* No Instantiation */
    }

}
