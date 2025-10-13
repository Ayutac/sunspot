package studio.abos.mc.sunspot.datagen.providers.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.sunspot.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.registry.SPCreativeMenuTabRegistry;
import studio.abos.mc.sunspot.registry.SPItemRegistry;
import studio.abos.mc.sunspot.registry.SPTagRegistry;

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
        builder.add(SPTagRegistry.SUBSTRATE_MATERIAL, "Substrate Material");
        builder.add(SPCreativeMenuTabRegistry.GENERAL_TAB_KEY, "Sunspot");
    }
}
