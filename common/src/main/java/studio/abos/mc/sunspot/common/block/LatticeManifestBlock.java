package studio.abos.mc.sunspot.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class LatticeManifestBlock extends Block implements EntityBlock {

    // this is not a shortcut, but a way to ensure we can always change the property and don't have to replace it everywhere
    public static BooleanProperty POWERED = BlockStateProperties.POWERED;

    public LatticeManifestBlock(Properties properties) {
        super(properties.lightLevel(state -> state.getValue(POWERED) ? 1 : 0));
        registerDefaultState(getStateDefinition().any().setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }
}
