package studio.abos.mc.sunspot.neoforge.common.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.abos.mc.sunspot.neoforge.common.net.ClientPayloadHandler;
import studio.abos.mc.sunspot.neoforge.common.net.FlameData;

public class SetupEvents {

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
