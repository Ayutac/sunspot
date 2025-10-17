package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.LmBatteryBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class LmBatteryBlock extends GlyphBlock {

    public LmBatteryBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        return new LmBatteryBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == SPBlockEntityTypeRegistry.LM_BATTERY.get() ? (l, p, s, e) ->  LmBatteryBlockEntity.tick(l, p, s, (LmBatteryBlockEntity) e) : null;
    }

}
