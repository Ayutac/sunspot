package studio.abos.mc.sunspot.neoforge.client.event;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.world.item.BlockItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.client.renderer.block.AffixBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.AshBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ImpelBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ComposeBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.ComposeCreativeBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.OffsetBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.block.RevitaliseBlockEntityRenderer;
import studio.abos.mc.sunspot.client.renderer.entity.FlamefallRenderer;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPParticleTypeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void onInitializeClient(final @NotNull EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SPEntityTypeRegistry.FLAMEFALL.get(), FlamefallRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.AFFIX.get(), AffixBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.ASH.get(), AshBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.COMPOSE.get(), ComposeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get(), ComposeCreativeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.IMPEL.get(), ImpelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.OFFSET.get(), OffsetBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(SPBlockEntityTypeRegistry.REVITALISE.get(), RevitaliseBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onBlockColorHandlerRegistration(final @NotNull RegisterColorHandlersEvent.Block event) {
        registerGlyphBlockTint(event, SPItemRegistry.AFFIX_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.ASH_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.COMPOSE_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.IMPEL_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.OFFSET_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.REVITALISE_BLOCK);
        registerGlyphBlockTint(event, SPItemRegistry.SUSTAIN_BLOCK);
    }

    private static void registerGlyphBlockTint(final @NotNull RegisterColorHandlersEvent.Block event, final @NotNull RegistrySupplier<BlockItem> blockItem) {
        event.register((state, view, pos, tintIndex) -> ((GlyphBlock)state.getBlock()).getTint(), blockItem.get().getBlock());
    }

    @SubscribeEvent
    public static void onBlockItemColorHandlerRegistration(final @NotNull RegisterColorHandlersEvent.Item event) {
        registerGlyphBlockItemTint(event, SPItemRegistry.AFFIX_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.ASH_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.COMPOSE_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.COMPOSE_CREATIVE_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.IMPEL_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.OFFSET_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.REVITALISE_BLOCK);
        registerGlyphBlockItemTint(event, SPItemRegistry.SUSTAIN_BLOCK);
    }

    private static void registerGlyphBlockItemTint(final @NotNull RegisterColorHandlersEvent.Item event, final @NotNull RegistrySupplier<BlockItem> blockItem) {
        event.register((itemStack, tintIndex) -> ((GlyphBlock)((BlockItem)itemStack.getItem()).getBlock()).getTint(), blockItem.get());
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(final @NotNull RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SPParticleTypeRegistry.FLAMEFALL_FLAME.get(), FlameParticle.Provider::new);
    }
}
