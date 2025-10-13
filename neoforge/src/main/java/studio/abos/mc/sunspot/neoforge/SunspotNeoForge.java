package studio.abos.mc.sunspot.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.abos.mc.sunspot.Sunspot;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import studio.abos.mc.sunspot.neoforge.common.capability.SPCapabilities;
import studio.abos.mc.sunspot.neoforge.common.net.ClientPayloadHandler;
import studio.abos.mc.sunspot.neoforge.common.net.FlameData;

@Mod(Sunspot.MOD_ID)
public class SunspotNeoForge {
    public SunspotNeoForge(ModContainer container, IEventBus bus) {
        Sunspot.init();
        SPCapabilities.ATTACHMENT_TYPES.register(bus);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                FlameData.TYPE,
                FlameData.STREAM_CODEC,
                ClientPayloadHandler::handleFlameDataOnMain
        );
    }
}
