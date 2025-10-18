package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Util;

public class ComposeBlockEntity extends GlyphBlockEntity {

    public ComposeBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(Util.getComposeBET(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected ComposeBlockEntity(final @NotNull BlockEntityType<? extends ComposeBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull ComposeBlockEntity blockEntity) {
        GlyphBlockEntity.tick(level, pos, state, blockEntity);
    }

}
