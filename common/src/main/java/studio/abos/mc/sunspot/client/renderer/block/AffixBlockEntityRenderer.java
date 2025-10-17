package studio.abos.mc.sunspot.client.renderer.block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;

public class AffixBlockEntityRenderer extends GlyphBlockEntityRenderer<AffixBlockEntity> {

    public AffixBlockEntityRenderer(final @NotNull BlockEntityRendererProvider.Context context) {
        super(context, new ItemStack(Items.SLIME_BALL));
    }

}
