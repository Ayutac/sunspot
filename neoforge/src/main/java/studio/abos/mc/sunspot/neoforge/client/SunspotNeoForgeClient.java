package studio.abos.mc.sunspot.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.client.SunspotClient;
import studio.abos.mc.sunspot.neoforge.client.event.ClientSetupEvent;

@Mod(dist = Dist.CLIENT, value = Sunspot.MOD_ID)
public class SunspotNeoForgeClient {

    public SunspotNeoForgeClient(ModContainer container, IEventBus bus) {
        SunspotClient.init();
        bus.register(ClientSetupEvent.class);
    }
}
