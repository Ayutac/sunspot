package studio.abos.mc.sunspot;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.FlameBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ImpelBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.RevitaliseBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.SeverBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.SustainBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.TransposeBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

import java.util.SequencedSet;

public final class Util {

    private static final int MAX_NETWORK_DEPTH = 64;

    private Util() {
        /* No instantiation */
    }

    public static boolean isOfDamageType(final @NotNull DamageSource source, final @NotNull ResourceKey<DamageType> type, final @NotNull Level level) {
        return source.type() == level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).get(type);
    }

    public static Holder<DamageType> damageTypeHolder(final @NotNull ResourceKey<DamageType> damageType, final @NotNull Level level) {
        return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType);
    }

    public static boolean isAffix(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.AFFIX));
    }

    public static BlockEntityType<AffixBlockEntity> getAffixBET() {
        return (BlockEntityType<AffixBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.AFFIX).get();
    }

    public static boolean isAsh(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.ASH));
    }

    public static BlockEntityType<AshBlockEntity> getAshBET() {
        return (BlockEntityType<AshBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.ASH).get();
    }

    public static boolean isCompose(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.COMPOSE));
    }

    public static BlockEntityType<ComposeBlockEntity> getComposeBET() {
        return (BlockEntityType<ComposeBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.COMPOSE).get();
    }

    public static boolean isImpel(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.IMPEL));
    }

    public static BlockEntityType<ImpelBlockEntity> getImpelBET() {
        return (BlockEntityType<ImpelBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.IMPEL).get();
    }

    public static boolean isOffset(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.OFFSET));
    }

    public static BlockEntityType<OffsetBlockEntity> getOffsetBET() {
        return (BlockEntityType<OffsetBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.OFFSET).get();
    }

    public static boolean isRevitalise(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.REVITALISE));
    }

    public static BlockEntityType<RevitaliseBlockEntity> getRevitaliseBET() {
        return (BlockEntityType<RevitaliseBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.REVITALISE).get();
    }

    public static boolean isSever(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.SEVER));
    }

    public static BlockEntityType<SeverBlockEntity> getSeverBET() {
        return (BlockEntityType<SeverBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.SEVER).get();
    }

    public static boolean isSustain(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.SUSTAIN));
    }

    public static BlockEntityType<SustainBlockEntity> getSustainBET() {
        return (BlockEntityType<SustainBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.SUSTAIN).get();
    }

    public static boolean isTranspose(final @NotNull BlockState state) {
        return state.is(SPBlockRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.TRANSPOSE));
    }

    public static BlockEntityType<TransposeBlockEntity> getTransposeBET() {
        return (BlockEntityType<TransposeBlockEntity>)SPBlockEntityTypeRegistry.GLYPH_MAP.get(SPGlyphTypeRegistry.TRANSPOSE).get();
    }

    /**
     * Invalidates the network caches of all network blocks around the specified one, but not the block itself.
     */
    public static void invalidateNetworkCacheAround(final @NotNull BlockGetter getter, final @NotNull BlockPos start) {
        for (final Direction direction : Direction.values()) {
            invalidateNetworkCache(getter, start.relative(direction));
        }
    }

    /**
     * Invalidates the network cache of all network blocks belonging to the specified one.
     */
    public static void invalidateNetworkCache(final @NotNull BlockGetter getter, final @NotNull BlockPos start) {
        if (!(getter.getBlockEntity(start) instanceof final FlameBlockEntity startBlockEntity)) {
            return;
        }
        final SequencedSet<BlockPos> cache = startBlockEntity.getNetworkCache();
        if (cache == null) {
            return;
        }
        for (final BlockPos pos : cache) {
            if (getter.getBlockEntity(pos) instanceof final FlameBlockEntity flameBlockEntity) {
                flameBlockEntity.invalidateNetworkCache();
            }
        }
    }

    /**
     * Builds the network caches for the blocks around the specified one, but not the block itself.
     */
    public static void buildNetworkCacheAround(final @NotNull BlockGetter getter, final @NotNull BlockPos start) {
        final SequencedSet<BlockPos> downNetwork = buildNetworkCache(getter, start.below());
        final SequencedSet<BlockPos> upNetwork;
        if (downNetwork.contains(start.above())) {
            upNetwork = downNetwork;
        }
        else {
            upNetwork = buildNetworkCache(getter, start.above());
        }
        final SequencedSet<BlockPos> northNetwork;
        if (downNetwork.contains(start.north())) {
            northNetwork = downNetwork;
        }
        else if (downNetwork != upNetwork && upNetwork.contains(start.north())) {
            northNetwork = upNetwork;
        }
        else {
            northNetwork = buildNetworkCache(getter, start.above());
        }
        final SequencedSet<BlockPos> southNetwork;
        if (downNetwork.contains(start.south())) {
            southNetwork = downNetwork;
        }
        else if (downNetwork != upNetwork && upNetwork.contains(start.south())) {
            southNetwork = upNetwork;
        }
        else if (downNetwork != northNetwork && upNetwork != northNetwork && northNetwork.contains(start.south())) {
            southNetwork = northNetwork;
        }
        else {
            southNetwork = buildNetworkCache(getter, start.south());
        }
        final SequencedSet<BlockPos> westNetwork;
        if (downNetwork.contains(start.west())) {
            westNetwork = downNetwork;
        }
        else if (downNetwork != upNetwork && upNetwork.contains(start.west())) {
            westNetwork = upNetwork;
        }
        else if (downNetwork != northNetwork && upNetwork != northNetwork && northNetwork.contains(start.west())) {
            westNetwork = northNetwork;
        }
        else if (downNetwork != southNetwork && upNetwork != southNetwork && northNetwork != southNetwork && southNetwork.contains(start.west())) {
            westNetwork = southNetwork;
        }
        else {
            westNetwork = buildNetworkCache(getter, start.west());
        }
        // here we go directly into the else branch since we don't need any assignment
        // makes the condition look complicated, I know
        if (!downNetwork.contains(start.east()) && (downNetwork == upNetwork || !upNetwork.contains(start.east())) &&
                (downNetwork == northNetwork || upNetwork == northNetwork || !northNetwork.contains(start.east())) &&
                (downNetwork == southNetwork || upNetwork == southNetwork || northNetwork == southNetwork && !southNetwork.contains(start.east())) &&
                (downNetwork == westNetwork || upNetwork == westNetwork || northNetwork == westNetwork && southNetwork == westNetwork || !westNetwork.contains(start.east()))
        ) {
            buildNetworkCache(getter, start.east());
        }
    }

    /**
     * Builds the network cache for the specified block, and all connected blocks.
     */
    public static SequencedSet<BlockPos> buildNetworkCache(final @NotNull BlockGetter getter, final @NotNull BlockPos start) {
        final SequencedSet<BlockPos> network = new ObjectLinkedOpenHashSet<>();
        buildNetworkCacheRec(getter, start, null, network, 0);
        for (final BlockPos pos : network) {
            if (getter.getBlockEntity(pos) instanceof FlameBlockEntity flameBlockEntity) {
                flameBlockEntity.setNetworkCache(network);
            }
        }
        return network;
    }

    private static void buildNetworkCacheRec(final @NotNull BlockGetter getter, final @NotNull BlockPos pos, final @Nullable Direction from, final @NotNull SequencedSet<BlockPos> cache, final int depth) {
        if (!(getter.getBlockEntity(pos) instanceof FlameBlockEntity)) {
            return;
        }
        cache.add(pos);
        // TODO: make this method non-recursive for more network depth
        if (depth >= MAX_NETWORK_DEPTH) { // to avoid SO
            return;
        }
        for (final Direction direction : Direction.values()) {
            if (direction.getOpposite() == from) {
                continue;
            }
            buildNetworkCacheRec(getter, pos.relative(direction), direction, cache, depth + 1);
        }
    }

    public static int requestEnergyFromNetwork(final @NotNull SequencedSet<BlockPos> network, final @NotNull BlockGetter getter, final @NotNull BlockPos requester, final int requestedAmount) {
        if (requestedAmount <= 0) {
            return 0;
        }
        int amount = 0;
        for (final BlockPos pos : network) {
            if (requester.equals(pos)) {
                continue;
            }
            if (!(getter.getBlockEntity(pos) instanceof final ComposeBlockEntity battery) || !battery.isPowered()) {
                continue;
            }
            final int remainingAmount = requestedAmount - amount;
            final int drainedAmount = Math.min(remainingAmount, battery.getCurrentFlame());
            battery.setCurrentFlame(battery.getCurrentFlame() - drainedAmount);
            amount += drainedAmount;
            if (amount >= requestedAmount) {
                break;
            }
        }
        return amount;
    }
}
