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
import studio.abos.mc.sunspot.common.GlyphType;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.blockentity.GlyphBlockEntity;

public class GlyphBlockEntityRenderer<T extends GlyphBlockEntity> implements BlockEntityRenderer<T> {

    public GlyphBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context) {
        /* Intentionally left empty */
    }

    @Override
    public void render(final @NotNull T blockEntity, final float partialTick, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight, final int packedOverlay) {
        final GlyphType type = ((GlyphBlock)blockEntity.getBlockState().getBlock()).getType();
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.98f, 0.5f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90f));
        poseStack.scale(0.5f, 0.5f, 1f);
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(type.getIntent().get()), ItemDisplayContext.FIXED,
                LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().above()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 0
        );
        poseStack.popPose();
    }
}
