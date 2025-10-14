package studio.abos.mc.sunspot.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.client.registry.SPMaterialRegistry;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Shadow
    private Quaternionf cameraOrientation;

    @Shadow
    private static void fireVertex(PoseStack.Pose arg, VertexConsumer arg2, float f, float g, float h, float i, float j) {
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/Entity;DDDFFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    <E extends Entity> void sunspot$renderFlamefallFire(final E entity, final double d, final double e, final double f, final float g, final float h, final PoseStack poseStack, final MultiBufferSource multiBufferSource, final int i, final CallbackInfo ci) {
        final CommonFlamefallFireComponent fire = SPComponentPlatformUtils.getFlamefallFireData(entity);
        // FIXME the <= 1 is a dirty solution, but I dunno why it doesn't get synchronized to 0 ???
        if (fire == null || fire.getRemainingFireTicks() <= 1 || entity.isSpectator()) {
            return;
        }
        final EntityRenderer<? super E> entityRenderer = ((EntityRenderDispatcher)(Object)this).getRenderer(entity);
        try {
            Vec3 vec3 = entityRenderer.getRenderOffset(entity, h);
            double j = d + vec3.x();
            double k = e + vec3.y();
            double l = f + vec3.z();
            poseStack.pushPose();
            poseStack.translate(j, k, l);
            sunspot$renderFlamefallFire(poseStack, multiBufferSource, entity, Mth.rotationAroundAxis(Mth.Y_AXIS, cameraOrientation, new Quaternionf()));
            poseStack.popPose();
        }
        catch (final Throwable t) {
            Sunspot.LOGGER.warn("Flamefall Fire couldn't be rendered!", t);
        }
    }

    @Unique
    private <E extends Entity> void sunspot$renderFlamefallFire(final PoseStack poseStack, final MultiBufferSource multiBufferSource, final E entity, final Quaternionf quaternionf) {
        TextureAtlasSprite textureAtlasSprite = SPMaterialRegistry.FLAMEFALL_FIRE_0.sprite();
        TextureAtlasSprite textureAtlasSprite2 = SPMaterialRegistry.FLAMEFALL_FIRE_1.sprite();
        poseStack.pushPose();
        float f = entity.getBbWidth() * 1.4F;
        poseStack.scale(f, f, f);
        float g = 0.5F;
        float h = 0.0F;
        float i = entity.getBbHeight() / f;
        float j = 0.0F;
        poseStack.mulPose(quaternionf);
        poseStack.translate(0.0F, 0.0F, 0.3F - (int)i * 0.02F);
        float k = 0.0F;
        int l = 0;
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(Sheets.cutoutBlockSheet());

        for (PoseStack.Pose pose = poseStack.last(); i > 0.0F; l++) {
            TextureAtlasSprite textureAtlasSprite3 = l % 2 == 0 ? textureAtlasSprite : textureAtlasSprite2;
            float m = textureAtlasSprite3.getU0();
            float n = textureAtlasSprite3.getV0();
            float o = textureAtlasSprite3.getU1();
            float p = textureAtlasSprite3.getV1();
            if (l / 2 % 2 == 0) {
                float q = o;
                o = m;
                m = q;
            }

            fireVertex(pose, vertexConsumer, -g - 0.0F, 0.0F - j, k, o, p);
            fireVertex(pose, vertexConsumer, g - 0.0F, 0.0F - j, k, m, p);
            fireVertex(pose, vertexConsumer, g - 0.0F, 1.4F - j, k, m, n);
            fireVertex(pose, vertexConsumer, -g - 0.0F, 1.4F - j, k, o, n);
            i -= 0.45F;
            j -= 0.45F;
            g *= 0.9F;
            k -= 0.03F;
        }

        poseStack.popPose();
    }

}
