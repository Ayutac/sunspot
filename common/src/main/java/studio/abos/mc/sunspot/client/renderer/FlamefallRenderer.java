package studio.abos.mc.sunspot.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.client.model.FlamefallModel;
import studio.abos.mc.sunspot.common.entity.FlamefallEntity;

@Environment(EnvType.CLIENT)
public class FlamefallRenderer extends MobRenderer<FlamefallEntity, FlamefallModel<FlamefallEntity>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/slime.png");

    public FlamefallRenderer(final @NotNull EntityRendererProvider.Context context) {
        super(context, new FlamefallModel<>(context.bakeLayer(ModelLayers.SLIME_OUTER)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(FlamefallEntity entity) {
        return TEXTURE;
    }
}
