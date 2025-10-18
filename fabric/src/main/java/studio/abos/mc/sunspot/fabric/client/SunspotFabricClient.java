package studio.abos.mc.sunspot.fabric.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.world.item.BlockItem;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.client.SunspotClient;
import studio.abos.mc.sunspot.client.renderer.block.AffixBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.AshBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ImpelBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ComposeBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ComposeCreativeBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.OffsetBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.RevitaliseBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.SustainBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.entity.FlamefallRenderer;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;
import studio.abos.mc.sunspot.fabric.client.event.ClientEvents;
import studio.abos.mc.sunspot.fabric.client.event.ClientJumpEvent;

@Environment(EnvType.CLIENT)
public class SunspotFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SunspotClient.init();
        EntityRendererRegistry.register(SPEntityTypeRegistry.FLAMEFALL, FlamefallRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.AFFIX_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.AFFIX.get(), AffixBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.ASH_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.ASH.get(), AshBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.COMPOSE_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.COMPOSE.get(), ComposeBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get(), ComposeCreativeBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.IMPEL_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.IMPEL.get(), ImpelBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.OFFSET_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.OFFSET.get(), OffsetBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.REVITALISE_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.REVITALISE.get(), RevitaliseBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.SUSTAIN_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.SUSTAIN.get(), SustainBlockEntityRenderer::new);
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

    private static void registerGlyphBlockTint(final @NotNull RegistrySupplier<BlockItem> blockItem) {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> ((GlyphBlock)state.getBlock()).getTint(), blockItem.get().getBlock());
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> ((GlyphBlock)((BlockItem)itemStack.getItem()).getBlock()).getTint(), blockItem.get());
    }
}
