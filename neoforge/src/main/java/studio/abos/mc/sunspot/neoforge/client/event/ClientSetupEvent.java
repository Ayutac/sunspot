package studio.abos.mc.sunspot.neoforge.client.event;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.world.item.BlockItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.client.gui.screens.inventory.SeverScreen;
import studio.abos.mc.sunspot.client.gui.screens.inventory.WorkbenchScreen;
import studio.abos.mc.sunspot.client.renderer.block.GlyphBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.SeverBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.entity.FlamefallRenderer;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPMenuTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void onInitializeClient(final @NotNull EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypeRegistry.FLAMEFALL.get(), FlamefallRenderer::new);
        event.registerBlockEntityRenderer(Util.getAffixBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getAshBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getComposeBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getDissipateBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getImpelBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getOffsetBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getRevitaliseBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getSeverBET(), SeverBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getSustainBET(), GlyphBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(Util.getTransposeBET(), GlyphBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onBlockColorHandlerRegistration(final @NotNull RegisterColorHandlersEvent.Block event) {
        for (final var value : SPItemRegistry.GLYPH_BLOCK_MAP.values()) {
            registerGlyphBlockTint(event, value);
        }
        registerGlyphBlockTint(event, SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
    }

    private static void registerGlyphBlockTint(final @NotNull RegisterColorHandlersEvent.Block event, final @NotNull RegistrySupplier<BlockItem> blockItem) {
        event.register((state, view, pos, tintIndex) -> ((GlyphBlock)state.getBlock()).getTint(), blockItem.get().getBlock());
    }

    @SubscribeEvent
    public static void onBlockItemColorHandlerRegistration(final @NotNull RegisterColorHandlersEvent.Item event) {
        for (final var value : SPItemRegistry.GLYPH_BLOCK_MAP.values()) {
            registerGlyphBlockItemTint(event, value);
        }
        registerGlyphBlockItemTint(event, SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
    }

    private static void registerGlyphBlockItemTint(final @NotNull RegisterColorHandlersEvent.Item event, final @NotNull RegistrySupplier<BlockItem> blockItem) {
        event.register((itemStack, tintIndex) -> ((GlyphBlock)((BlockItem)itemStack.getItem()).getBlock()).getTint(), blockItem.get());
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(final @NotNull RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SPParticleTypeRegistry.FLAMEFALL_FLAME.get(), FlameParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerScreens(final @NotNull RegisterMenuScreensEvent event) {
        event.register(SPMenuTypeRegistry.SEVER.get(), SeverScreen::new);
        event.register(SPMenuTypeRegistry.WORKBENCH.get(), WorkbenchScreen::new);
    }
}
