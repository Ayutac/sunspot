package studio.abos.mc.sunspot;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import studio.abos.mc.sunspot.common.registry.SPBiomeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPCommandRegistry;
import studio.abos.mc.sunspot.common.registry.SPCreativeMenuTabRegistry;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

public class Sunspot {
    public static final String MOD_ID = "sunspot";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        SPBlockRegistry.BLOCK_REGISTRY.register();
        SPItemRegistry.ITEM_REGISTRY.register();
        SPTagRegistry.init();
        SPCreativeMenuTabRegistry.register();
        SPBiomeRegistry.init();
        SPDimensionRegistry.init();
        CommandRegistrationEvent.EVENT.register(SPCommandRegistry::register);
    }

    public static ResourceLocation id(final String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
