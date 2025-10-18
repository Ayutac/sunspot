package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.Optional;

public class SustainBlockEntity extends EffectGlyphBlockEntity {

    public SustainBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(SPBlockEntityTypeRegistry.SUSTAIN.get(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected SustainBlockEntity(final @NotNull BlockEntityType<? extends SustainBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public int getInterval() {
        return 80;
    }

    @Override
    public int getRadius() {
        return 5;
    }

    @Override
    public Holder<MobEffect> getEffect() {
        return MobEffects.DAMAGE_RESISTANCE;
    }

    @Override
    public Optional<TagKey<EntityType<?>>> getUnaffectedTag() {
        return Optional.of(SPTagRegistry.UNAFFECTED_BY_SUSTAIN);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull SustainBlockEntity blockEntity) {
        EffectGlyphBlockEntity.tick(level, pos, state, blockEntity);
    }

}
