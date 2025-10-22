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
import studio.abos.mc.sunspot.common.blockentity.SustainBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

public class SustainBlock extends GlyphBlock {

    public SustainBlock(final @NotNull Properties properties) {
        super(SPGlyphTypeRegistry.SUSTAIN, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new SustainBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == Util.getSustainBET() ? (l, p, s, e) ->  SustainBlockEntity.tick(l, p, s, (SustainBlockEntity) e) : null;
    }

}
