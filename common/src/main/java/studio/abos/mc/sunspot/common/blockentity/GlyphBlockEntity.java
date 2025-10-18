package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.block.ComposeBlock;

import java.util.SequencedSet;

public abstract class GlyphBlockEntity extends BlockEntity implements FlameBlockEntity {

    protected int currentFlame;

    protected int maxFlame;

    protected @Nullable SequencedSet<BlockPos> networkCache;

    public GlyphBlockEntity(final @NotNull BlockEntityType<? extends GlyphBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public int getCurrentFlame() {
        return currentFlame;
    }

    @Override
    public void setCurrentFlame(final int currentFlame) {
        if (currentFlame < 0) {
            throw new IllegalArgumentException("Current Flame cannot be negative!");
        }
        if (currentFlame > maxFlame) {
            this.currentFlame = maxFlame;
        }
        this.currentFlame = currentFlame;
        setChanged();
    }

    @Override
    public int getMaxFlame() {
        return maxFlame;
    }

    @Override
    public void setMaxFlame(final int maxFlame) {
        if (maxFlame < 0) {
            throw new IllegalArgumentException("Max Flame cannot be negative!");
        }
        this.maxFlame = maxFlame;
        if (currentFlame > maxFlame) {
            currentFlame = maxFlame;
        }
        setChanged();
    }

    @Override
    public @Nullable SequencedSet<BlockPos> getNetworkCache() {
        return networkCache;
    }

    @Override
    public void setNetworkCache(@Nullable SequencedSet<BlockPos> networkCache) {
        this.networkCache = networkCache;
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull GlyphBlockEntity blockEntity) {
        if (blockEntity.getNetworkCache() == null) {
            Util.invalidateNetworkCacheAround(level, pos);
            Util.buildNetworkCache(level, pos);
        }
        if (blockEntity instanceof ComposeCreativeBlockEntity) {
            return;
        }
        final int networkFlame = Util.requestEnergyFromNetwork(blockEntity.getNetworkCache(), level, pos, blockEntity.getMaxFlame() - blockEntity.getCurrentFlame());
        blockEntity.setCurrentFlame(blockEntity.getCurrentFlame() + networkFlame);
        final boolean oldState = state.getValue(GlyphBlock.POWERED);
        final boolean newState = blockEntity.currentFlame != 0;
        if (oldState != newState) {
            level.setBlockAndUpdate(pos, state.setValue(ComposeBlock.POWERED, newState));
        }
    }

    @Override
    protected void loadAdditional(final CompoundTag tag, final HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);
        setMaxFlame(Math.max(0, tag.getInt(FlameBlockEntity.MAX_FLAME_KEY)));
        setCurrentFlame(Math.max(0, tag.getInt(FlameBlockEntity.CURRENT_FLAME_KEY)));
    }

    @Override
    protected void saveAdditional(final CompoundTag tag, final HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        tag.putInt(FlameBlockEntity.MAX_FLAME_KEY, getMaxFlame());
        tag.putInt(FlameBlockEntity.CURRENT_FLAME_KEY, getCurrentFlame());
    }
}
