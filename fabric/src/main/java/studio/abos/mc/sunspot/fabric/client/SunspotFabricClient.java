package studio.abos.mc.sunspot.fabric.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import studio.abos.mc.sunspot.client.renderer.FlamefallRenderer;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;

@Environment(EnvType.CLIENT)
public class SunspotFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SPEntityTypeRegistry.FLAMEFALL, FlamefallRenderer::new);
    }
}
