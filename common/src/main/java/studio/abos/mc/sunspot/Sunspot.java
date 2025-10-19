package studio.abos.mc.sunspot;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import studio.abos.mc.sunspot.common.registry.SPBiomeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPCommandRegistry;
import studio.abos.mc.sunspot.common.registry.SPCreativeMenuTabRegistry;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEventRegistry;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPRegistries;
import studio.abos.mc.sunspot.common.registry.SPRippleTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

public class Sunspot {
    public static final String MOD_ID = "sunspot";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        SPRegistries.init(); // registry of registries always first
        SPRippleTypeRegistry.register();
        SPItemPreRegistry.init(); // anything registered here can be used as intent
        SPGlyphTypeRegistry.register(); // register glyph types with intent
        SPBlockRegistry.register(); // blocks before items
        SPItemRegistry.register();
        SPBlockEntityTypeRegistry.register(); // BEs after blocks
        // order of the rest is relatively arbitrary
        SPEntityTypeRegistry.register();
        SPTagRegistry.init();
        SPCreativeMenuTabRegistry.register();
        SPBiomeRegistry.init();
        SPDimensionRegistry.init();
        SPParticleTypeRegistry.register();
        SPEventRegistry.register();
        CommandRegistrationEvent.EVENT.register(SPCommandRegistry::register);
    }

    public static ResourceLocation id(final String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
