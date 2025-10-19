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
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.world.item.BlockItem;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.client.SunspotClient;
import studio.abos.mc.sunspot.client.gui.screens.inventory.SeverScreen;
import studio.abos.mc.sunspot.client.renderer.block.GlyphBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.entity.FlamefallRenderer;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPMenuTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;
import studio.abos.mc.sunspot.fabric.client.event.ClientEvents;
import studio.abos.mc.sunspot.fabric.client.event.ClientJumpEvent;

@Environment(EnvType.CLIENT)
public class SunspotFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SunspotClient.init();
        // entity renderer registration
        EntityRendererRegistry.register(SPEntityTypeRegistry.FLAMEFALL, FlamefallRenderer::new);
        // block entity renderer and tint registration
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.AFFIX));
        BlockEntityRendererRegistry.register(Util.getAffixBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.ASH));
        BlockEntityRendererRegistry.register(Util.getAshBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.COMPOSE));
        BlockEntityRendererRegistry.register(Util.getComposeBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
        BlockEntityRendererRegistry.register(SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.DISSIPATE));
        BlockEntityRendererRegistry.register(Util.getDissipateBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.IMPEL));
        BlockEntityRendererRegistry.register(Util.getImpelBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.OFFSET));
        BlockEntityRendererRegistry.register(Util.getOffsetBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.REVITALISE));
        BlockEntityRendererRegistry.register(Util.getRevitaliseBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SEVER));
        BlockEntityRendererRegistry.register(Util.getSeverBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SUSTAIN));
        BlockEntityRendererRegistry.register(Util.getSustainBET(), GlyphBlockEntityRenderer::new);
        registerGlyphBlockTint(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.TRANSPOSE));
        BlockEntityRendererRegistry.register(Util.getTransposeBET(), GlyphBlockEntityRenderer::new);
        // particle registration
        ParticleFactoryRegistry.getInstance().register(SPParticleTypeRegistry.FLAMEFALL_FLAME.get(), FlameParticle.Provider::new);
        // build in the jump activation (in case we need it for more than the {OFFSET} block)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.options.keyJump.isDown()) {
                ClientJumpEvent.EVENT.invoker().jump();
            }
        });
        // activate the {OFFSET} block
        ClientJumpEvent.EVENT.register(ClientEvents::jumpOnOffsetBlock);
        // register the screen
        MenuScreens.register(SPMenuTypeRegistry.SEVER.get(), SeverScreen::new);
    }

    private static void registerGlyphBlockTint(final @NotNull RegistrySupplier<BlockItem> blockItem) {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> ((GlyphBlock)state.getBlock()).getTint(), blockItem.get().getBlock());
        ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> ((GlyphBlock)((BlockItem)itemStack.getItem()).getBlock()).getTint(), blockItem.get());
    }
}
