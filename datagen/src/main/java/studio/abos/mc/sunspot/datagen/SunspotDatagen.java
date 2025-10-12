package studio.abos.mc.sunspot.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import studio.abos.mc.sunspot.datagen.providers.assets.SPModelProvider;
import studio.abos.mc.sunspot.datagen.providers.data.SPAdvancementProvider;
import studio.abos.mc.sunspot.datagen.providers.data.SPBiomeProvider;
import studio.abos.mc.sunspot.datagen.providers.data.SPLootTableProviders;
import studio.abos.mc.sunspot.datagen.providers.data.SPRecipeProvider;
import studio.abos.mc.sunspot.datagen.providers.data.SPTagProviders;

public class SunspotDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(final FabricDataGenerator generator) {
        final FabricDataGenerator.Pack pack = generator.createPack();
        // tags always first
        pack.addProvider(SPTagProviders.SPItemTags::new);
        pack.addProvider(SPModelProvider::new);
        pack.addProvider(SPAdvancementProvider::new);
        pack.addProvider(SPLootTableProviders.BlockLoot::new);
        pack.addProvider(SPRecipeProvider::new);
    }

    @Override
    public void buildRegistry(final RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, SPBiomeProvider::bootstrap);
    }
}
