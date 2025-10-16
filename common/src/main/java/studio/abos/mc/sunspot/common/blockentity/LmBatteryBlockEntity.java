package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class LmBatteryBlockEntity extends LatticeManifestBlockEntity {

    public LmBatteryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SPBlockEntityTypeRegistry.LM_BATTERY.get(), blockPos, blockState);
        setMaxFlame(20);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull LmBatteryBlockEntity blockEntity) {
        LatticeManifestBlockEntity.tick(level, pos, state, blockEntity);
    }

}
