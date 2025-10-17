package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;

import java.util.List;
import java.util.Optional;

public class OffsetBlockEntity extends LatticeManifestBlockEntity {

    public OffsetBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(SPBlockEntityTypeRegistry.OFFSET.get(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected OffsetBlockEntity(final @NotNull BlockEntityType<? extends OffsetBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setMaxFlame(100);
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull OffsetBlockEntity blockEntity) {
        LatticeManifestBlockEntity.tick(level, pos, state, blockEntity);
        final List<Player> players = level.getEntitiesOfClass(Player.class, AABB.ofSize(pos.getCenter(), 1d, 2d, 1d), player -> !player.isSpectator());
        if (players.isEmpty()) {
            return;
        }
        final Optional<BlockPos> downTarget = nextDownElevator(pos, level);
        if (downTarget.isEmpty()) {
            return;
        }
        for (final Player player : players) {
            if (player.isShiftKeyDown()) {
                if (teleport(player, downTarget.get().above())) {
                    player.setShiftKeyDown(false);
                }
            }
        }
    }

    private static boolean isValidTarget(final @NotNull BlockPos pos, final @NotNull Level level) {
        final BlockState target = level.getBlockState(pos);
        return level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir() &&
                target.is(SPBlockRegistry.OFFSET);// && target.getValue(LatticeManifestBlock.POWERED);
    }

    public static @NotNull Optional<BlockPos> nextUpElevator(final @NotNull BlockPos pos, final @NotNull Level level) {
        BlockPos currentPos = pos.above(2);
        do {
            currentPos = currentPos.above();
        }
        while (currentPos.getY() <= level.getMaxBuildHeight() && !isValidTarget(currentPos, level));
        if (currentPos.getY() < level.getMaxBuildHeight() && isValidTarget(currentPos, level)) {
            return Optional.of(currentPos);
        }
        return Optional.empty();
    }

    public static @NotNull Optional<BlockPos> nextDownElevator(final @NotNull BlockPos pos, final @NotNull Level level) {
        BlockPos currentPos = pos.below(2);
        do {
            currentPos = currentPos.below();
        }
        while (currentPos.getY() >= level.getMinBuildHeight() && !isValidTarget(currentPos, level));
        if (currentPos.getY() > level.getMinBuildHeight() && isValidTarget(currentPos, level)) {
            return Optional.of(currentPos);
        }
        return Optional.empty();
    }

    private static boolean teleport(final @NotNull Player player, final @NotNull BlockPos target) {
        // TODO: actually use energy
        final Vec3 centeredPos = Vec3.atCenterOf(new Vec3i(target.getX(), target.getY(), target.getZ()));
        player.teleportTo(centeredPos.x(), target.getY(), centeredPos.z());
        return true;
    }

}
