package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.blockentity.SeverBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.TransposeBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

public class TransposeBlock extends GlyphBlock {

    public TransposeBlock(final Properties properties) {
        super(SPGlyphTypeRegistry.TRANSPOSE, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        return new TransposeBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == Util.getTransposeBET() ? (l, p, s, e) -> TransposeBlockEntity.tick(l, p, s, (SeverBlockEntity) e) : null;
    }

}
