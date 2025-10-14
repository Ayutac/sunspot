package studio.abos.mc.sunspot.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class FlamefallEntity extends PathfinderMob {

    public FlamefallEntity(EntityType<? extends FlamefallEntity> entityType, Level level) {
        super(entityType, level);
        setNoGravity(true);
    }

//    public static @NotNull AttributeSupplier.Builder createMobAttributes() {
//        return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0);
//    }

}
