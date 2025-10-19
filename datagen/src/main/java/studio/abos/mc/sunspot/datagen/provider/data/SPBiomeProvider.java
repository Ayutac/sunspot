package studio.abos.mc.sunspot.datagen.provider.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBiomeRegistry;

import java.util.concurrent.CompletableFuture;

public class SPBiomeProvider extends FabricDynamicRegistryProvider {

    public SPBiomeProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void bootstrap(final BootstrapContext<Biome> context) {
        context.register(SPBiomeRegistry.FOURSPACE, fourspace(context));
    }

    @Override
    protected void configure(final HolderLookup.Provider registries, final Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
    }

    @Override
    public @NotNull String getName() {
        return "Biome Provider";
    }

    private static Biome fourspace(final BootstrapContext<Biome> context) {
        final MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        final BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0f)
                .temperature(0f)
                .generationSettings(generationBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0)
                        .waterFogColor(0)
                        .skyColor(0)
                        .fogColor(0)
                        .build())
                .build();
    }
}
