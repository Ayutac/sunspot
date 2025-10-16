package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class LmBatteryBlockEntity extends LatticeManifestBlockEntity {

    public LmBatteryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SPBlockEntityTypeRegistry.LM_BATTERY.get(), blockPos, blockState);
    }
}
