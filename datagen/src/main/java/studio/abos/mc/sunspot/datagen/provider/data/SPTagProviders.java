package studio.abos.mc.sunspot.datagen.provider.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPTagProviders {

    public static class SPItemTags extends FabricTagProvider.ItemTagProvider {

        public SPItemTags(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            getOrCreateTagBuilder(SPTagRegistry.CANDLES).add(
                    Items.CANDLE,
                    Items.WHITE_CANDLE,
                    Items.LIGHT_GRAY_CANDLE,
                    Items.GRAY_CANDLE,
                    Items.BLACK_CANDLE,
                    Items.BROWN_CANDLE,
                    Items.RED_CANDLE,
                    Items.ORANGE_CANDLE,
                    Items.YELLOW_CANDLE,
                    Items.LIME_CANDLE,
                    Items.GREEN_CANDLE,
                    Items.CYAN_CANDLE,
                    Items.LIGHT_BLUE_CANDLE,
                    Items.BLUE_CANDLE,
                    Items.PURPLE_CANDLE,
                    Items.MAGENTA_CANDLE,
                    Items.PINK_CANDLE
            );
            getOrCreateTagBuilder(SPTagRegistry.SUBSTRATE_MATERIAL).add(Items.BONE_BLOCK);
            getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_ASH_TRANSFORMATION).add(
                    SPItemPreRegistry.ASH_RESIDUE.getId(),
                    SPItemPreRegistry.ASH_RESIDUE_BLOCK.getId()
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_COBBLESTONE).add(
                    Items.STONE,
                    Items.STONE_BRICKS,
                    Items.CHISELED_STONE_BRICKS,
                    Items.SMOOTH_STONE,
                    Items.CRACKED_STONE_BRICKS,
                    Items.MOSSY_COBBLESTONE,
                    Items.MOSSY_STONE_BRICKS
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_COBBLED_DEEPSLATE).add(
                    Items.DEEPSLATE,
                    Items.DEEPSLATE_BRICKS,
                    Items.CHISELED_DEEPSLATE,
                    Items.DEEPSLATE_TILES,
                    Items.POLISHED_DEEPSLATE,
                    Items.CRACKED_DEEPSLATE_BRICKS,
                    Items.CRACKED_DEEPSLATE_TILES
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_BLACKSTONE).add(
                    Items.CHISELED_POLISHED_BLACKSTONE,
                    Items.CRACKED_POLISHED_BLACKSTONE_BRICKS,
                    Items.POLISHED_BLACKSTONE
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_GRAVEL).add(
                    Items.COBBLESTONE,
                    Items.COBBLED_DEEPSLATE,
                    Items.ANDESITE,
                    Items.DIORITE,
                    Items.GRANITE,
                    Items.TUFF,
                    Items.BASALT,
                    Items.CALCITE,
                    Items.POLISHED_ANDESITE,
                    Items.POLISHED_DIORITE,
                    Items.POLISHED_GRANITE,
                    Items.SMOOTH_BASALT,
                    Items.POLISHED_BASALT,
                    Items.POLISHED_TUFF,
                    Items.TUFF_BRICKS,
                    Items.CHISELED_TUFF,
                    Items.CHISELED_TUFF_BRICKS
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_SAND).add(
                    Items.GRAVEL,
                    Items.SANDSTONE,
                    Items.SMOOTH_SANDSTONE,
                    Items.CUT_SANDSTONE,
                    Items.CHISELED_SANDSTONE
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_RED_SAND).add(
                    Items.RED_SANDSTONE,
                    Items.SMOOTH_RED_SANDSTONE,
                    Items.CUT_RED_SANDSTONE,
                    Items.CHISELED_RED_SANDSTONE
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_DIRT).add(
                    Items.PACKED_MUD,
                    Items.MUD_BRICKS,
                    Items.MUD,
                    Items.PODZOL,
                    Items.GRASS_BLOCK,
                    Items.MYCELIUM,
                    Items.ROOTED_DIRT,
                    Items.COARSE_DIRT,
                    Items.MUDDY_MANGROVE_ROOTS
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_NETHERRACK).add(
                    Items.CRIMSON_NYLIUM,
                    Items.WARPED_NYLIUM
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_FOUR_NETHER_BRICK).add(
                    Items.NETHER_BRICKS,
                    Items.CRACKED_NETHER_BRICKS,
                    Items.CHISELED_NETHER_BRICKS
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_FOUR_NETHER_QUARTZ).add(
                    Items.QUARTZ_BLOCK,
                    Items.QUARTZ_BRICKS,
                    Items.QUARTZ_PILLAR,
                    Items.CHISELED_QUARTZ_BLOCK,
                    Items.SMOOTH_QUARTZ
            );
        }
    }

    public static class SPEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {

        public SPEntityTypeTags(final @NotNull FabricDataOutput output, final @NotNull CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            var unaffectedByAffix = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_AFFIX);
            unaffectedByAffix.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByAffix.add(EntityType.VEX);

            var unaffectedByAsh = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_ASH);
            unaffectedByAsh.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByAsh.add(EntityType.VEX);

            var unaffectedByImpel = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_IMPEL);
            unaffectedByImpel.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByImpel.add(EntityType.VEX);

            var unaffectedByOffset = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_OFFSET);
            unaffectedByOffset.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByOffset.add(EntityType.VEX);

            var unaffectedByRevitalise = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_REVITALISE);
            unaffectedByRevitalise.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByRevitalise.add(EntityType.VEX);
            unaffectedByRevitalise.addOptionalTag(EntityTypeTags.UNDEAD);

            var unaffectedBySever = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_SEVER);
            unaffectedBySever.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedBySever.add(EntityType.VEX);

            var unaffectedBySustain = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_SUSTAIN);
            unaffectedBySustain.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedBySustain.add(EntityType.VEX);
        }
    }
}
