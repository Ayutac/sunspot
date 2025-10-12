package studio.abos.mc.sunspot.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import studio.abos.mc.sunspot.Sunspot;

public interface SPBiomeRegistry {
    ResourceKey<Biome> FOURSPACE = ResourceKey.create(Registries.BIOME, Sunspot.id("fourspace"));

    static void init() {
        // intentionally left empty
    }
}
