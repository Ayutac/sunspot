package studio.abos.mc.sunspot.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.GlyphType;

import java.util.function.Supplier;

public abstract class GlyphBlock extends Block implements EntityBlock {

    // this is not a shortcut, but a way to ensure we can always change the property and don't have to replace it everywhere
    public static BooleanProperty POWERED = BlockStateProperties.POWERED;
    protected final Supplier<GlyphType> type;

    public GlyphBlock(final @NotNull Supplier<GlyphType> type, final @NotNull Properties properties) {
        super(properties.lightLevel(state -> state.getValue(POWERED) ? 1 : 0));
        this.type = type;
        registerDefaultState(getStateDefinition().any().setValue(POWERED, false));
    }

    public int getTint() {
        return type.get().getTint();
    }

    public @NotNull GlyphType getType() {
        return type.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }
}
