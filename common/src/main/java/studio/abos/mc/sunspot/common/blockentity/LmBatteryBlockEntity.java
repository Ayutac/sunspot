package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class LmBatteryBlockEntity extends GlyphBlockEntity {

    public LmBatteryBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(SPBlockEntityTypeRegistry.LM_BATTERY.get(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected LmBatteryBlockEntity(final @NotNull BlockEntityType<? extends LmBatteryBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setMaxFlame(20);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull LmBatteryBlockEntity blockEntity) {
        GlyphBlockEntity.tick(level, pos, state, blockEntity);
    }

}
