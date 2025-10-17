package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class RevitaliseBlockEntity extends EffectGlyphBlockEntity {

    public RevitaliseBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(SPBlockEntityTypeRegistry.REVITALISE.get(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected RevitaliseBlockEntity(final @NotNull BlockEntityType<? extends RevitaliseBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
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
        return MobEffects.REGENERATION;
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull RevitaliseBlockEntity blockEntity) {
        EffectGlyphBlockEntity.tick(level, pos, state, blockEntity);
    }

}
