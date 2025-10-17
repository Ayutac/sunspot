package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.ComposeCreativeBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class ComposeCreativeBlock extends ComposeBlock {

    public ComposeCreativeBlock(final Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(POWERED, true));
    }

    @Override
    public int getTint() {
        return 0xa02493;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        return new ComposeCreativeBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState state,  BlockEntityType<T> type) {
        return !level.isClientSide() && type == SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get() ? (l, p, s, e) ->  ComposeCreativeBlockEntity.tick(l, p, s, (ComposeCreativeBlockEntity) e) : null;
    }

}
