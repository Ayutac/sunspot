package studio.abos.mc.sunspot.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;

public class AshBlockEntityRenderer extends GlyphBlockEntityRenderer<AshBlockEntity> {

    public AshBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context) {
        super(context, new ItemStack(Items.SOUL_CAMPFIRE));
    }

    @Override
    public void render(final @NotNull AshBlockEntity blockEntity, final float partialTick, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight, final int packedOverlay) {
        super.render(blockEntity, partialTick, poseStack, multiBufferSource, packedLight, packedOverlay);
    }
}
