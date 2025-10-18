package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.SequencedSet;

public interface FlameBlockEntity {

    String CURRENT_FLAME_KEY = "current_flame";

    String MAX_FLAME_KEY = "max_flame";

    int getCurrentFlame();

    default boolean isPowered() {
        return getCurrentFlame() > 0;
    }

    void setCurrentFlame(final int currentFlame);

    int getMaxFlame();

    void setMaxFlame(final int maxFlame);

    @Nullable SequencedSet<BlockPos> getNetworkCache();

    void setNetworkCache(final @Nullable SequencedSet<BlockPos> network);

    default void invalidateNetworkCache() {
        setNetworkCache(null);
    }
}
