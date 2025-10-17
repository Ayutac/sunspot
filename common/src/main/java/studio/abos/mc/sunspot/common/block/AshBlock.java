package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class AshBlock extends LatticeManifestBlock {

    public AshBlock(final Properties properties) {
        super(properties);
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
