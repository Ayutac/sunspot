package studio.abos.mc.sunspot.fabric.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import studio.abos.mc.sunspot.client.SunspotClient;
import studio.abos.mc.sunspot.client.renderer.FlamefallRenderer;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;
import studio.abos.mc.sunspot.fabric.client.event.ClientEvents;
import studio.abos.mc.sunspot.fabric.client.event.ClientJumpEvent;

@Environment(EnvType.CLIENT)
public class SunspotFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SunspotClient.init();
        EntityRendererRegistry.register(SPEntityTypeRegistry.FLAMEFALL, FlamefallRenderer::new);
        ParticleFactoryRegistry.getInstance().register(SPParticleTypeRegistry.FLAMEFALL_FLAME.get(), FlameParticle.Provider::new);
        // build in the jump activation (in case we need it for more than the {OFFSET} block)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.options.keyJump.isDown()) {
                ClientJumpEvent.EVENT.invoker().jump();
            }
        });
        // activate the {OFFSET} block
        ClientJumpEvent.EVENT.register(ClientEvents::jumpOnOffsetBlock);
    }
}
