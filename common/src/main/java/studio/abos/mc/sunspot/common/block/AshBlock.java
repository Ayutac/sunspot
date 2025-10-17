package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

public class AshBlock extends GlyphBlock {

    public AshBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState state, final @NotNull Entity entity) {
        if (state.getValue(GlyphBlock.POWERED) && !entity.getType().is(SPTagRegistry.UNAFFECTED_BY_ASH)) {
            entity.hurt(new DamageSource(Util.damageTypeHolder(SPDamageTypeRegistry.ASH, level)), 10f);
        }
        super.stepOn(level, blockPos, state, entity);
    }

    @Override
    public int getTint() {
        return 0x551b1b;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        return new AshBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == SPBlockEntityTypeRegistry.ASH.get() ? (l, p, s, e) ->  AshBlockEntity.tick(l, p, s, (AshBlockEntity) e) : null;
    }

}
