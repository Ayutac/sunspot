package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;

public class ComposeCreativeBlockEntity extends ComposeBlockEntity {

    public ComposeCreativeBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(SPBlockEntityTypeRegistry.COMPOSE_CREATIVE.get(), blockPos, blockState);
        currentFlame = maxFlame = Integer.MAX_VALUE;
    }

    @Override
    public void setCurrentFlame(int currentFlame) {
        /* Empty on purpose */
    }

    @Override
    public void setMaxFlame(int maxFlame) {
        /* Empty on purpose */
    }
}
