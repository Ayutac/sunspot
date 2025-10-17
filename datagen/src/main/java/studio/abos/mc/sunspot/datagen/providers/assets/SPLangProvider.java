package studio.abos.mc.sunspot.datagen.providers.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPCreativeMenuTabRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPLangProvider extends FabricLanguageProvider {
    public SPLangProvider(final FabricDataOutput dataOutput, final CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(final HolderLookup.Provider registryLookup, final TranslationBuilder builder) {
        builder.add(SPItemRegistry.SUBSTRATE_2.get(), "2-dimensional Substrate");
        builder.add(SPBlockRegistry.SUBSTRATE_3.get(), "3-dimensional Substrate");
        builder.add(SPBlockRegistry.SUBSTRATE_4.get(), "4-dimensional Substrate");
        builder.add(SPItemRegistry.ASH_RESIDUE.get(), "{ASH} Residue");
        builder.add(SPItemRegistry.ASH_RESIDUE_BLOCK.get(), "{ASH} Residue Block");
        builder.add(SPBlockRegistry.LM_WORKBENCH.get(), "LM Workbench");
        builder.add(SPBlockRegistry.LM_BATTERY.get(), "LM Battery");
        builder.add(SPBlockRegistry.LM_BATTERY_CREATIVE.get(), "Creative LM Battery");
        builder.add(SPBlockRegistry.AFFIX.get(), "{AFFIX} Block");
        builder.add(SPBlockRegistry.ASH.get(), "{ASH} Block");
        builder.add(SPItemRegistry.MANTLE_BASE_HELMET.get(), "Mantle Base Helmet");
        builder.add(SPItemRegistry.MANTLE_BASE_CHESTPLATE.get(), "Mantle Base Chestplate");
        builder.add(SPItemRegistry.MANTLE_BASE_LEGGINGS.get(), "Mantle Base Leggings");
        builder.add(SPItemRegistry.MANTLE_BASE_BOOTS.get(), "Mantle Base Boots");
        builder.add(SPItemRegistry.FOURSPACE_SHIFTER.get(), "Fourspace Shifter");
        builder.add(SPItemRegistry.FLAMEFALL_ROD.get(), "Flamefall Rod");

        builder.add(SPEntityTypeRegistry.FLAMEFALL.get(), "Flamefall");

        builder.add(SPTagRegistry.SUBSTRATE_MATERIAL, "Substrate Material");
        builder.add(SPTagRegistry.UNAFFECTED_BY_AFFIX, "Unaffected by {AFFIX}");
        builder.add(SPTagRegistry.UNAFFECTED_BY_AFFIX, "Unaffected by {ASH}");

        builder.add(SPCreativeMenuTabRegistry.GENERAL_TAB_KEY, "Sunspot");

        builder.add("death.attack.flamefall.item", "%1$s went inferno while fighting %2$s using %3$s");
        builder.add("death.attack.flamefall.player", "%1$s went inferno while fighting %2$s");
        builder.add("death.attack.flamefall", "%1$s went inferno");
    }
}
