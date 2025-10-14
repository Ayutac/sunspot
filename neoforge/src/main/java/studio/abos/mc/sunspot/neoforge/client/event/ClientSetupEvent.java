package studio.abos.mc.sunspot.neoforge.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import studio.abos.mc.sunspot.client.renderer.FlamefallRenderer;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void onInitializeClient(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypeRegistry.FLAMEFALL.get(), FlamefallRenderer::new);
    }
}
