package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.blockentity.SeverBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

public class SeverBlock extends GlyphBlock {

    public SeverBlock(final Properties properties) {
        super(SPGlyphTypeRegistry.SEVER, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new SeverBlockEntity(blockPos, blockState);
    }

    @Override
    public void stepOn(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState state, final @NotNull Entity entity) {
        if (state.getValue(GlyphBlock.POWERED) && !entity.getType().is(SPTagRegistry.UNAFFECTED_BY_SEVER)) {
            entity.hurt(new DamageSource(Util.damageTypeHolder(SPDamageTypeRegistry.SEVER, level)), 2f);
        }
        super.stepOn(level, blockPos, state, entity);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(level, blockPos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player) {
        final BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof SeverBlockEntity) {
            player.openMenu((MenuProvider)blockEntity);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof final SeverBlockEntity severBlockEntity) {
                Containers.dropContents(level, pos, severBlockEntity);
                // update comparators TODO issue #9
                // level.updateNeighbourForOutputSignal(pos,this);
            }
            super.onRemove(state, level, pos, newState, moved);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == Util.getSeverBET() ? (l, p, s, e) ->  SeverBlockEntity.tick(l, p, s, (SeverBlockEntity) e) : null;
    }

}
