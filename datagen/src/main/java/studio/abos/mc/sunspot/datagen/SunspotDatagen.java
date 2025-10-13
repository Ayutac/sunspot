package studio.abos.mc.sunspot.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.datagen.providers.assets.SPLangProvider;
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
        pack.addProvider(SPLangProvider::new);
        pack.addProvider((o, r) -> new FabricDynamicRegistryProvider(o, r) {
            @Override
            public @NotNull String getName() {
                return "Biome Provider";
            }

            @Override
            protected void configure(HolderLookup.Provider registries, Entries entries) {
                entries.addAll(registries.lookupOrThrow(Registries.BIOME));
            }
        });
    }

    @Override
    public void buildRegistry(final RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, SPBiomeProvider::bootstrap);
    }
}
