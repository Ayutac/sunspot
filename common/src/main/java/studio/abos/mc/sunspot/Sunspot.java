package studio.abos.mc.sunspot;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import studio.abos.mc.sunspot.registry.SPBiomeRegistry;
import studio.abos.mc.sunspot.registry.SPDimensionRegistry;
import studio.abos.mc.sunspot.registry.SPTagRegistry;

public class Sunspot {
    public static final String MOD_ID = "sunspot";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        SPTagRegistry.init();
        SPBiomeRegistry.init();
        SPDimensionRegistry.init();
    }

    public static ResourceLocation id(final String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
