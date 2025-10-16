package studio.abos.mc.sunspot.neoforge.client.event;

import net.minecraft.client.particle.FlameParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import studio.abos.mc.sunspot.client.renderer.FlamefallRenderer;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void onInitializeClient(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypeRegistry.FLAMEFALL.get(), FlamefallRenderer::new);
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SPParticleTypeRegistry.FLAMEFALL_FLAME.get(), FlameParticle.Provider::new);
    }
}
