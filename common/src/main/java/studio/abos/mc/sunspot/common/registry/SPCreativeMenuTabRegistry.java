package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import studio.abos.mc.sunspot.Sunspot;

public interface SPCreativeMenuTabRegistry {

    DeferredRegister<CreativeModeTab> CREATIVE_TAB_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.CREATIVE_MODE_TAB);

    String GENERAL_TAB_KEY = "itemGroup.sunspot.general";

    static void register() {
        CREATIVE_TAB_REGISTRY.register("general", SPCreativeMenuTabRegistry::createSunspotItemGroup);
        CREATIVE_TAB_REGISTRY.register();
    }

    static CreativeModeTab createSunspotItemGroup() {
        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable(GENERAL_TAB_KEY))
                .icon(() -> SPItemPreRegistry.SUBSTRATE_2.get().getDefaultInstance())
                .displayItems((displayContext, entries) -> {
                    entries.accept(SPItemPreRegistry.FLAMEFALL_ROD.get());
                    entries.accept(SPItemPreRegistry.SUBSTRATE_2.get());
                    entries.accept(SPItemPreRegistry.SUBSTRATE_3.get());
                    entries.accept(SPItemPreRegistry.SUBSTRATE_4.get());
                    entries.accept(SPItemPreRegistry.ASH_RESIDUE.get());
                    entries.accept(SPItemPreRegistry.ASH_RESIDUE_BLOCK.get());
                    entries.accept(SPItemPreRegistry.LM_WORKBENCH.get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.AFFIX).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.ASH).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.COMPOSE).get());
                    entries.accept(SPItemRegistry.COMPOSE_CREATIVE_BLOCK.get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.DISSIPATE).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.EXTRACT).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.IMPEL).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.OFFSET).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.REVITALISE).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SEVER).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SUSTAIN).get());
                    entries.accept(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.TRANSPOSE).get());
                    entries.accept(SPItemPreRegistry.FOURSPACE_SHIFTER.get());
                    entries.accept(SPItemPreRegistry.MANTLE_BASE_HELMET.get());
                    entries.accept(SPItemPreRegistry.MANTLE_BASE_CHESTPLATE.get());
                    entries.accept(SPItemPreRegistry.MANTLE_BASE_LEGGINGS.get());
                    entries.accept(SPItemPreRegistry.MANTLE_BASE_BOOTS.get());
                })
                .build();
    }

}
