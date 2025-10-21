package studio.abos.mc.sunspot.datagen.provider.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPCreativeMenuTabRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPLangProvider extends FabricLanguageProvider {
    public SPLangProvider(final FabricDataOutput dataOutput, final CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(final HolderLookup.Provider registryLookup, final TranslationBuilder builder) {
        builder.add(SPItemPreRegistry.SUBSTRATE_2.get(), "2-dimensional Substrate");
        builder.add(SPBlockRegistry.SUBSTRATE_3.get(), "3-dimensional Substrate");
        builder.add(SPBlockRegistry.SUBSTRATE_4.get(), "4-dimensional Substrate");
        builder.add(SPItemPreRegistry.ASH_RESIDUE.get(), "{ASH} Residue");
        builder.add(SPItemPreRegistry.ASH_RESIDUE_BLOCK.get(), "{ASH} Residue Block");
        builder.add(SPBlockRegistry.WORKBENCH.get(), "Workbench");
        for (final var entry : SPBlockRegistry.GLYPH_MAP.entrySet()) {
            builder.add(entry.getValue().get(), entry.getKey().get().getTranslation() + " Block");
        }
        builder.add(SPBlockRegistry.COMPOSE_CREATIVE.get(), "Creative {COMPOSE} Block");
        builder.add(SPItemPreRegistry.MANTLE_BASE_HELMET.get(), "Mantle Base Helmet");
        builder.add(SPItemPreRegistry.MANTLE_BASE_CHESTPLATE.get(), "Mantle Base Chestplate");
        builder.add(SPItemPreRegistry.MANTLE_BASE_LEGGINGS.get(), "Mantle Base Leggings");
        builder.add(SPItemPreRegistry.MANTLE_BASE_BOOTS.get(), "Mantle Base Boots");
        builder.add(SPItemPreRegistry.FOURSPACE_SHIFTER.get(), "Fourspace Shifter");
        builder.add(SPItemPreRegistry.FLAMEFALL_ROD.get(), "Flamefall Rod");

        builder.add(SPEntityTypeRegistry.FLAMEFALL.get(), "Flamefall");

        builder.add(SPTagRegistry.SUBSTRATE_MATERIAL, "Substrate Material");
        builder.add(SPTagRegistry.UNAFFECTED_BY_ASH_TRANSFORMATION, "Unaffected by {ASH}");
        builder.add(SPTagRegistry.SEVERED_INTO_COBBLESTONE, "{SEVER}ed into Cobblestone");
        builder.add(SPTagRegistry.SEVERED_INTO_COBBLED_DEEPSLATE, "{SEVER}ed into Cobbled Deepslate");
        builder.add(SPTagRegistry.SEVERED_INTO_BLACKSTONE, "{SEVER}ed into Blackstone");
        builder.add(SPTagRegistry.SEVERED_INTO_GRAVEL, "{SEVER}ed into Gravel");
        builder.add(SPTagRegistry.SEVERED_INTO_SAND, "{SEVER}ed into Sand");
        builder.add(SPTagRegistry.SEVERED_INTO_RED_SAND, "{SEVER}ed into Red Sand");
        builder.add(SPTagRegistry.SEVERED_INTO_DIRT, "{SEVER}ed into Dirt");
        builder.add(SPTagRegistry.SEVERED_INTO_NETHERRACK, "{SEVER}ed into Netherrack");
        builder.add(SPTagRegistry.SEVERED_INTO_FOUR_NETHER_BRICK, "{SEVER}ed into four Nether Brick");
        builder.add(SPTagRegistry.SEVERED_INTO_FOUR_NETHER_QUARTZ, "{SEVER}ed into four Nether Quartz");
        builder.add(SPTagRegistry.UNAFFECTED_BY_AFFIX, "Unaffected by {AFFIX}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_ASH, "Unaffected by {ASH}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_IMPEL, "Unaffected by {IMPEL}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_OFFSET, "Unaffected by {OFFSET}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_REVITALISE, "Unaffected by {REVITALISE}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_SEVER, "Unaffected by {SEVER}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_SUSTAIN, "Unaffected by {SUSTAIN}");

        builder.add(SPCreativeMenuTabRegistry.GENERAL_TAB_KEY, "Sunspot");

        builder.add("death.attack.ash.item", "%1$s intimately discovered the effects of {ASH} while fighting %2$s using %3$s");
        builder.add("death.attack.ash.player", "%1$s intimately discovered the effects of {ASH} while fighting %2$s");
        builder.add("death.attack.ash", "%1$s intimately discovered the effects of {ASH}");
        builder.add("death.attack.severed.item", "%1$s was {SEVER}ed while fighting %2$s using %3$s");
        builder.add("death.attack.severed.player", "%1$s was {SEVER}ed while fighting %2$s");
        builder.add("death.attack.severed", "%1$s was {SEVER}ed");
        builder.add("death.attack.flamefall.item", "%1$s went inferno while fighting %2$s using %3$s");
        builder.add("death.attack.flamefall.player", "%1$s went inferno while fighting %2$s");
        builder.add("death.attack.flamefall", "%1$s went inferno");
    }
}
