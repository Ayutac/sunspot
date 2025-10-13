package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import studio.abos.mc.sunspot.Sunspot;

public interface SPDimensionRegistry {
    ResourceKey<Level> FOURSPACE_DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Sunspot.id("fourspace"));
    ResourceKey<DimensionType> FOURSPACE_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE, FOURSPACE_DIMENSION_KEY.location());

    static void init() {
        // intentionally left empty
    }
}
