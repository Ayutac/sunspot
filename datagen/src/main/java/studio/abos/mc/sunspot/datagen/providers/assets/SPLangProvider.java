package studio.abos.mc.sunspot.datagen.providers.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPCreativeMenuTabRegistry;
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
        builder.add(SPItemRegistry.MANTLE_BASE_HELMET.get(), "Mantle Base Helmet");
        builder.add(SPItemRegistry.MANTLE_BASE_CHESTPLATE.get(), "Mantle Base Chestplate");
        builder.add(SPItemRegistry.MANTLE_BASE_LEGGINGS.get(), "Mantle Base Leggings");
        builder.add(SPItemRegistry.MANTLE_BASE_BOOTS.get(), "Mantle Base Boots");
        builder.add(SPItemRegistry.FOURSPACE_SHIFTER.get(), "Fourspace Shifter");

        builder.add(SPTagRegistry.SUBSTRATE_MATERIAL, "Substrate Material");

        builder.add(SPCreativeMenuTabRegistry.GENERAL_TAB_KEY, "Sunspot");
    }
}
