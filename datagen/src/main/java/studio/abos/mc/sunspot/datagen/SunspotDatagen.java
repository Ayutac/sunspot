package studio.abos.mc.sunspot.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import studio.abos.mc.sunspot.datagen.providers.data.SPBiomeProvider;
import studio.abos.mc.sunspot.datagen.providers.data.SPTagProviders;

public class SunspotDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(final FabricDataGenerator generator) {
        final FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(SPTagProviders.JItemTags::new);
    }

    @Override
    public void buildRegistry(final RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, SPBiomeProvider::bootstrap);
    }
}
