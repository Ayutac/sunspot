package studio.abos.mc.sunspot.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.abos.mc.sunspot.client.registry.SPMaterialRegistry;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @Inject(method = "renderScreenEffect(Lnet/minecraft/client/Minecraft;Lcom/mojang/blaze3d/vertex/PoseStack;)V", at = @At("TAIL"))
    private static void sunspot$renderFlamefallFire(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        final CommonFlamefallFireComponent fire = SPComponentPlatformUtils.getFlamefallFireData(minecraft.player);
        // FIXME see comment in the EntityRenderDispatcherMixin
        if (fire == null || fire.getRemainingFireTicks() <= 1) {
            return;
        }
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        TextureAtlasSprite textureAtlasSprite = SPMaterialRegistry.FLAMEFALL_FIRE_1.sprite();
        RenderSystem.setShaderTexture(0, textureAtlasSprite.atlasLocation());
        float f = textureAtlasSprite.getU0();
        float g = textureAtlasSprite.getU1();
        float h = (f + g) / 2.0F;
        float i = textureAtlasSprite.getV0();
        float j = textureAtlasSprite.getV1();
        float k = (i + j) / 2.0F;
        float l = textureAtlasSprite.uvShrinkRatio();
        float m = Mth.lerp(l, f, h);
        float n = Mth.lerp(l, g, h);
        float o = Mth.lerp(l, i, k);
        float p = Mth.lerp(l, j, k);
        float q = 1.0F;

        for (int r = 0; r < 2; r++) {
            poseStack.pushPose();
            float s = -0.5F;
            float t = 0.5F;
            float u = -0.5F;
            float v = 0.5F;
            float w = -0.5F;
            poseStack.translate(-(r * 2 - 1) * 0.24F, -0.3F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees((r * 2 - 1) * 10.0F));
            Matrix4f matrix4f = poseStack.last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferBuilder.addVertex(matrix4f, -0.5F, -0.5F, -0.5F).setUv(n, p).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferBuilder.addVertex(matrix4f, 0.5F, -0.5F, -0.5F).setUv(m, p).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferBuilder.addVertex(matrix4f, 0.5F, 0.5F, -0.5F).setUv(m, o).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            bufferBuilder.addVertex(matrix4f, -0.5F, 0.5F, -0.5F).setUv(n, o).setColor(1.0F, 1.0F, 1.0F, 0.9F);
            BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
            poseStack.popPose();
        }
    }

}
