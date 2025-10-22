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
import studio.abos.mc.sunspot.common.blockentity.RevitaliseBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

public class RevitaliseBlock extends GlyphBlock {

    public RevitaliseBlock(final @NotNull Properties properties) {
        super(SPGlyphTypeRegistry.REVITALISE, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new RevitaliseBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == Util.getRevitaliseBET() ? (l, p, s, e) ->  RevitaliseBlockEntity.tick(l, p, s, (RevitaliseBlockEntity) e) : null;
    }

}
