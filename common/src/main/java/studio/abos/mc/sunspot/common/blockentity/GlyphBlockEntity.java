package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.block.GlyphBlock;
import studio.abos.mc.sunspot.common.block.LmBatteryBlock;

public abstract class GlyphBlockEntity extends BlockEntity {

    protected static String CURRENT_FLAME_KEY = "current_flame";

    protected static String MAX_FLAME_KEY = "max_flame";

    protected int currentFlame;

    protected int maxFlame;

    public GlyphBlockEntity(final @NotNull BlockEntityType<? extends GlyphBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public int getCurrentFlame() {
        return currentFlame;
    }

    public boolean isPowered() {
        return getCurrentFlame() > 0;
    }

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

    public int getMaxFlame() {
        return maxFlame;
    }

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

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull GlyphBlockEntity blockEntity) {
        final boolean oldState = state.getValue(GlyphBlock.POWERED);
        final boolean newState = blockEntity.currentFlame != 0;
        if (oldState != newState) {
            level.setBlockAndUpdate(pos, state.setValue(LmBatteryBlock.POWERED, newState));
        }
    }

    @Override
    protected void loadAdditional(final CompoundTag tag, final HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);
        setMaxFlame(Math.max(0, tag.getInt(MAX_FLAME_KEY)));
        setCurrentFlame(Math.max(0, tag.getInt(CURRENT_FLAME_KEY)));
    }

    @Override
    protected void saveAdditional(final CompoundTag tag, final HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        tag.putInt(MAX_FLAME_KEY, getMaxFlame());
        tag.putInt(CURRENT_FLAME_KEY, getCurrentFlame());
    }
}
