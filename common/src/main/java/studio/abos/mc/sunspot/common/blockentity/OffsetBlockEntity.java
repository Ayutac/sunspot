package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.block.LatticeManifestBlock;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;

import java.util.List;
import java.util.Optional;

public class OffsetBlockEntity extends LatticeManifestBlockEntity {

    public static int TELEPORT_USAGE = 5;

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
        final List<Entity> entities = level.getEntitiesOfClass(Entity.class, AABB.ofSize(pos.getCenter(), 1d, 2d, 1d), entity -> !entity.isSpectator());
        if (entities.isEmpty()) {
            return;
        }
        final Optional<BlockPos> upTarget = nextUpElevator(pos, level).map(BlockPos::above);
        final Optional<BlockPos> downTarget = nextDownElevator(pos, level).map(BlockPos::above);
        for (final Entity entity : entities) {
            if (entity.isShiftKeyDown() && downTarget.isPresent()) {
                if (teleport(entity, downTarget.get(), blockEntity)) {
                    entity.setShiftKeyDown(false);
                }
            }
            // we move non players always up for now
            else if (!(entity instanceof Player) && upTarget.isPresent()) {
                teleport(entity, upTarget.get(), blockEntity);
            }
        }
    }

    private static boolean isValidTarget(final @NotNull BlockPos pos, final @NotNull Level level) {
        final BlockState target = level.getBlockState(pos);
        return level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir() &&
                target.is(SPBlockRegistry.OFFSET) && target.getValue(LatticeManifestBlock.POWERED);
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

    public static boolean teleport(final @NotNull Entity entity, final @NotNull BlockPos target, final @NotNull OffsetBlockEntity blockEntity) {
        final int flame = blockEntity.getCurrentFlame();
        if (flame < TELEPORT_USAGE) {
            return false;
        }
        if (!(entity instanceof Player player && player.isCreative())) {
            blockEntity.setCurrentFlame(flame - TELEPORT_USAGE);
        }
        final Vec3 centeredPos = Vec3.atCenterOf(new Vec3i(target.getX(), target.getY(), target.getZ()));
        entity.teleportTo(centeredPos.x(), target.getY(), centeredPos.z());
        entity.playSound(SoundEvents.ENDERMAN_TELEPORT);
        return true;
    }

}
