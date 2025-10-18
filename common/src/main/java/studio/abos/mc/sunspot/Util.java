package studio.abos.mc.sunspot;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.blockentity.AffixBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.AshBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ComposeBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.ImpelBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.RevitaliseBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.SeverBlockEntity;
import studio.abos.mc.sunspot.common.blockentity.SustainBlockEntity;
import studio.abos.mc.sunspot.common.registry.SPBlockEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;

public final class Util {

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
}
