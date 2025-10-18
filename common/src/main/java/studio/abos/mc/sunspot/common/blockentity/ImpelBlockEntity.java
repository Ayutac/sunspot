package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Util;

public class ImpelBlockEntity extends GlyphBlockEntity {

    public static int VELOCITY = 5;

    public ImpelBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(Util.getImpelBET(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected ImpelBlockEntity(final @NotNull BlockEntityType<? extends ImpelBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setMaxFlame(100);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull ImpelBlockEntity blockEntity) {
        GlyphBlockEntity.tick(level, pos, state, blockEntity);
    }

}
