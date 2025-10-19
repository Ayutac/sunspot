package studio.abos.mc.sunspot.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.blockentity.SeverBlockEntity;
import studio.abos.mc.sunspot.common.inventory.SeverMenu;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

@Environment(EnvType.CLIENT)
public class SeverBlockEntityRenderer<T extends SeverBlockEntity> extends GlyphBlockEntityRenderer<T> {

    public SeverBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(final @NotNull T blockEntity, final float partialTick, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight, final int packedOverlay) {
        super.render(blockEntity, partialTick, poseStack, multiBufferSource, packedLight, packedOverlay);
        final ItemStack input = blockEntity.getItem(SeverMenu.INPUT_SLOT);
        if (!input.isEmpty() && blockEntity.getLevel().getBlockState(blockEntity.getBlockPos().above()).is(SPTagRegistry.GLASS_BLOCKS)) {
            poseStack.pushPose();
            poseStack.translate(0.5f, 1.15f, 0.5f);
            poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getSeverTicks() % 360));
            poseStack.scale(0.5f, 0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(input, ItemDisplayContext.FIXED,
                    LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().above()),
                    OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 0
            );
            poseStack.popPose();
        }
    }
}
