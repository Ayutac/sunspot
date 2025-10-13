package studio.abos.mc.sunspot.neoforge;

import studio.abos.mc.sunspot.Sunspot;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import studio.abos.mc.sunspot.neoforge.common.capability.SPCapabilities;
import studio.abos.mc.sunspot.neoforge.common.event.SetupEvents;

@Mod(Sunspot.MOD_ID)
public class SunspotNeoForge {
    public SunspotNeoForge(ModContainer container, IEventBus bus) {
        Sunspot.init();
        SPCapabilities.ATTACHMENT_TYPES.register(bus);
        bus.register(SetupEvents.class);
    }
}
