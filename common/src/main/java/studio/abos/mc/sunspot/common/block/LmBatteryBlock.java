package studio.abos.mc.sunspot.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.LmBatteryBlockEntity;

public class LmBatteryBlock extends LatticeManifestBlock {

    public LmBatteryBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        return new LmBatteryBlockEntity(blockPos, blockState);
    }
}
