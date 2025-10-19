package studio.abos.mc.sunspot.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Util;
import studio.abos.mc.sunspot.common.inventory.SeverMenu;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

public class SeverBlockEntity extends GlyphBlockEntity implements MenuProvider {

    public SeverBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(Util.getSeverBET(), blockPos, blockState);
        setMaxFlame(20);
    }

    protected SeverBlockEntity(final @NotNull BlockEntityType<? extends SeverBlockEntity> blockEntityType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(SPGlyphTypeRegistry.SEVER.get().getTranslation());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(final int index, final @NotNull Inventory inventory, final @NotNull Player player) {
        return new SeverMenu(index, inventory, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    public static void tick(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull SeverBlockEntity blockEntity) {
        GlyphBlockEntity.tick(level, pos, state, blockEntity);
    }
}
