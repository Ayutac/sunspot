package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public abstract class EffectGlyphBlockEntity extends GlyphBlockEntity {

    protected EffectGlyphBlockEntity(final @NotNull BlockEntityType<? extends EffectGlyphBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public abstract int getInterval();

    public abstract int getRadius();

    public abstract Holder<MobEffect> getEffect();

    public abstract Optional<TagKey<EntityType<?>>> getUnaffectedTag();

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull EffectGlyphBlockEntity blockEntity) {
        GlyphBlockEntity.tick(level, pos, state, blockEntity);
        if (level.getGameTime() % blockEntity.getInterval() == 0) {
            final List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(blockEntity.getRadius()), entity -> !(entity instanceof final Player player && player.isSpectator()));
            Optional<TagKey<EntityType<?>>> unaffectedTag = blockEntity.getUnaffectedTag();
            for (final LivingEntity entity : entities) {
                if (unaffectedTag.isEmpty() || !entity.getType().is(unaffectedTag.get())) {
                    entity.addEffect(new MobEffectInstance(blockEntity.getEffect(), blockEntity.getInterval()));
                }
            }
        }
    }

}
