package studio.abos.mc.sunspot.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.GlyphBlockEntity;

public abstract class GlyphBlockEntityRenderer<T extends GlyphBlockEntity> implements BlockEntityRenderer<T> {

    final ItemStack topDisplay;

    protected GlyphBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context, final @Nullable ItemStack topDisplay) {
        this.topDisplay = topDisplay;
    }

    @Override
    public void render(final @NotNull T blockEntity, final float partialTick, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight, final int packedOverlay) {
        if (topDisplay == null) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.98f, 0.5f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90f));
        poseStack.scale(0.5f, 0.5f, 1f);
        Minecraft.getInstance().getItemRenderer().renderStatic(topDisplay, ItemDisplayContext.FIXED,
                LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().above()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 0
        );
        poseStack.popPose();
    }
}
