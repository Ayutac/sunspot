package studio.abos.mc.sunspot.client.renderer.block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;

public class OffsetBlockEntityRenderer extends GlyphBlockEntityRenderer<OffsetBlockEntity> {

    public OffsetBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context) {
        super(context, new ItemStack(Items.ENDER_PEARL));
    }

}
