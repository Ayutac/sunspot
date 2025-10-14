package studio.abos.mc.sunspot.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FlamefallModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart root;

    public FlamefallModel(final ModelPart modelPart) {
        root = modelPart;
    }

    @Override
    public @NotNull ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }
}
