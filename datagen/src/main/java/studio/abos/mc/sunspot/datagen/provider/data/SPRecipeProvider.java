package studio.abos.mc.sunspot.datagen.provider.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;
import studio.abos.mc.sunspot.datagen.builder.ExtractRecipeBuilder;
import studio.abos.mc.sunspot.datagen.builder.SeverRecipeBuilder;
import studio.abos.mc.sunspot.datagen.builder.WorkbenchRecipeBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SPRecipeProvider extends FabricRecipeProvider {

    public SPRecipeProvider(final @NotNull FabricDataOutput output, final @NotNull CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(final @NotNull RecipeOutput out) {
        buildCraftingTableRecipes(out);
        buildSeverRecipes(out);
        buildExtractRecipes(out);
        for (var entry : SPItemRegistry.GLYPH_BLOCK_MAP.entrySet()) {
            final GlyphType glyphType = entry.getKey().get();
            new WorkbenchRecipeBuilder(entry.getValue().get())
                    .requires(glyphType.getSubstrate().get())
                    .intent(glyphType.getIntent().get())
                    .flame(15)
                    .unlockedBy("has_intent", InventoryChangeTrigger.TriggerInstance.hasItems(glyphType.getIntent().get()))
                    .save(out, Sunspot.id("workbench/" + glyphType.getId().getPath()));
        }
    }

    private void buildCraftingTableRecipes(final @NotNull RecipeOutput out) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_2.get(), 4)
                .pattern(" S ")
                .pattern("S S")
                .pattern(" S ")
                .define('S', SPTagRegistry.SUBSTRATE_MATERIALS)
                .unlockedBy("has_material", getItemCriterion(SPTagRegistry.SUBSTRATE_MATERIALS))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_3.get())
                .pattern(" S ")
                .pattern("SDS")
                .pattern(" S ")
                .define('D', Items.DIAMOND)
                .define('S', SPItemPreRegistry.SUBSTRATE_2.get())
                .unlockedBy("has_substrate_2", getItemCriterion(SPItemPreRegistry.SUBSTRATE_2))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_4.get())
                .pattern("ESE")
                .pattern("SSS")
                .pattern("ESE")
                .define('E', Items.ENDER_PEARL)
                .define('S', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_3", getItemCriterion(SPItemPreRegistry.SUBSTRATE_3))
                .save(out);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItemPreRegistry.ASH_RESIDUE_BLOCK.get())
                .requires(SPItemPreRegistry.ASH_RESIDUE.get(), 9)
                .unlockedBy("has_ash_residue", getItemCriterion(SPItemPreRegistry.ASH_RESIDUE))
                .save(out);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItemPreRegistry.ASH_RESIDUE.get(), 9)
                .requires(SPItemPreRegistry.ASH_RESIDUE_BLOCK.get())
                .unlockedBy("has_ash_block", getItemCriterion(SPItemPreRegistry.ASH_RESIDUE_BLOCK))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.LM_WORKBENCH.get())
                .pattern("SS")
                .pattern("PP")
                .pattern("PP")
                .define('P', ItemTags.PLANKS)
                .define('S', SPItemPreRegistry.SUBSTRATE_2.get())
                .unlockedBy("has_substrate_2", getItemCriterion(SPItemPreRegistry.SUBSTRATE_2))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_HELMET.get())
                .pattern("STS")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", getItemCriterion(SPItemPreRegistry.SUBSTRATE_4))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_CHESTPLATE.get())
                .pattern("T T")
                .pattern("SSS")
                .pattern("TST")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", getItemCriterion(SPItemPreRegistry.SUBSTRATE_4))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("T T")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", getItemCriterion(SPItemPreRegistry.SUBSTRATE_4))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_BOOTS.get())
                .pattern("S S")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", getItemCriterion(SPItemPreRegistry.SUBSTRATE_4))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.FOURSPACE_SHIFTER.get())
                .pattern("ETE")
                .pattern("TST")
                .pattern("ETE")
                .define('E', Items.ENDER_PEARL)
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", getItemCriterion(SPItemPreRegistry.SUBSTRATE_4))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.FLAMEFALL_ROD.get())
                .pattern(" F ")
                .pattern("PRP")
                .pattern("PRP")
                .define('F', Items.ENDER_PEARL)
                .define('P', Items.BLAZE_POWDER)
                .define('R', Items.BLAZE_ROD)
                .unlockedBy("has_blaze_rod", getItemCriterion(Items.BLAZE_ROD))
                .save(out);
    }

    private void buildSeverRecipes(final @NotNull RecipeOutput out) {
        severTag(out, SPTagRegistry.SEVERED_INTO_COBBLESTONE, Items.COBBLESTONE, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_COBBLED_DEEPSLATE, Items.COBBLED_DEEPSLATE, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_BLACKSTONE, Items.BLACKSTONE, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_GRAVEL, Items.GRAVEL, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_SAND, Items.SAND, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_RED_SAND, Items.RED_SAND, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_DIRT, Items.DIRT, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_NETHERRACK, Items.NETHERRACK, 1);
        severTag(out, SPTagRegistry.SEVERED_INTO_FOUR_NETHER_BRICK, Items.NETHER_BRICK, 4);
        severTag(out, SPTagRegistry.SEVERED_INTO_FOUR_NETHER_QUARTZ, Items.QUARTZ, 4);
        severTag(out, TagKey.create(Registries.ITEM, Sunspot.c("shulker_boxes")), Items.SHULKER_SHELL, 2);
        severTag(out, ItemTags.ANVIL, Items.IRON_BLOCK, 3);
        severTag(out, ItemTags.TRIM_TEMPLATES, Items.DIAMOND, 6, true);
        severPlanksFromLogs(out, ItemTags.OAK_LOGS, Items.OAK_PLANKS);
        severPlanksFromLogs(out, ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
        severPlanksFromLogs(out, ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
        severPlanksFromLogs(out, ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
        severPlanksFromLogs(out, ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
        severPlanksFromLogs(out, ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
        severPlanksFromLogs(out, ItemTags.CHERRY_LOGS, Items.CHERRY_PLANKS);
        severPlanksFromLogs(out, ItemTags.MANGROVE_LOGS, Items.MANGROVE_PLANKS);
        severPlanksFromLogs(out, ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
        severPlanksFromLogs(out, ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        severPlanksFromLogs(out, ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_PLANKS, 2);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/bone_meal")), Items.BONE_MEAL);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/coal")), Items.COAL);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/copper")), Items.COPPER_INGOT);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/diamond")), Items.DIAMOND);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/dried_kelp")), Items.DRIED_KELP);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/emerald")), Items.EMERALD);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/gold")), Items.GOLD_INGOT);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/iron")), Items.IRON_INGOT);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/lapis")), Items.LAPIS_LAZULI);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/netherite")), Items.NETHERITE_INGOT);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/raw_copper")), Items.RAW_COPPER);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/raw_gold")), Items.RAW_GOLD);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/raw_iron")), Items.RAW_IRON);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/redstone")), Items.REDSTONE);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/slime")), Items.SLIME_BALL);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("storage_blocks/wheat")), Items.WHEAT);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("ingots/iron")), Items.IRON_NUGGET);
        severStorageItem(out, TagKey.create(Registries.ITEM, Sunspot.c("ingots/gold")), Items.GOLD_NUGGET);
        sever(out, Items.BRICKS, Items.BRICK, 4);
        sever(out, Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD, 4);
        sever(out, Items.DRIPSTONE_BLOCK, Items.POINTED_DRIPSTONE, 4);
        sever(out, Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        sever(out, Items.CLAY, Items.CLAY_BALL, 4);
        sever(out, Items.RED_MUSHROOM_BLOCK, Items.RED_MUSHROOM, 4);
        sever(out, Items.BROWN_MUSHROOM_BLOCK, Items.BROWN_MUSHROOM, 4);
        sever(out, Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);
        sever(out, Items.SEA_LANTERN, Items.PRISMARINE_CRYSTALS, 5);
        sever(out, Items.PRISMARINE, Items.PRISMARINE_SHARD, 4, true);
        sever(out, Items.DARK_PRISMARINE, Items.PRISMARINE_SHARD, 8, true);
        sever(out, Items.PRISMARINE_BRICKS, Items.PRISMARINE_SHARD, 9, true);
        sever(out, Items.RED_NETHER_BRICKS, Items.NETHER_BRICK, 2, true);
        sever(out, Items.BLAZE_ROD, Items.BLAZE_POWDER, 4);
        sever(out, Items.BREEZE_ROD, Items.WIND_CHARGE, 4);
        sever(out, Items.SUGAR_CANE, Items.SUGAR, 2);
        sever(out, Items.COBWEB, Items.STRING, 3, true);
        sever(out, Items.NETHER_WART_BLOCK, Items.NETHER_WART, 9);
        sever(out, Items.MELON, Items.MELON_SLICE, 9);
        sever(out, Items.MUSIC_DISC_5, Items.DISC_FRAGMENT_5, 9);
        sever(out, SPItemPreRegistry.ASH_RESIDUE_BLOCK.get(), SPItemPreRegistry.ASH_RESIDUE.get(), 9);
        sever(out, Items.TNT, Items.GUNPOWDER, 3);
        sever(out, Items.BOOK, Items.PAPER, 2);
        sever(out, Items.COMPASS, Items.IRON_INGOT, 3, true);
        sever(out, Items.RECOVERY_COMPASS, Items.ECHO_SHARD, 6);
        sever(out, Items.CHAIN, Items.IRON_NUGGET, 3, true);
        sever(out, Items.CAULDRON, Items.IRON_INGOT, 6, true);
        sever(out, Items.LIGHTNING_ROD, Items.COPPER_INGOT, 2, true);
        sever(out, Items.FLOWER_POT, Items.BRICK, 2, true);
        sever(out, Items.DECORATED_POT, Items.BRICK, 4, true);
        sever(out, Items.BELL, Items.GOLD_INGOT, 3, true);
        sever(out, Items.BOOKSHELF, Items.PAPER, 6, true);
        sever(out, Items.HOPPER, Items.IRON_INGOT, 4, true);
        sever(out, Items.MINECART, Items.IRON_INGOT, 4, true);
        sever(out, Items.BONE, Items.BONE_MEAL, 4, true);
        sever(out, Items.SADDLE, Items.LEATHER, 3, true);
        sever(out, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND, 6, true);
        // wool to carpet
        sever(out, Items.WHITE_WOOL, Items.WHITE_CARPET, 4);
        sever(out, Items.LIGHT_GRAY_WOOL, Items.LIGHT_GRAY_CARPET, 4);
        sever(out, Items.GRAY_WOOL, Items.GRAY_CARPET, 4);
        sever(out, Items.BLACK_WOOL, Items.BLACK_CARPET, 4);
        sever(out, Items.BROWN_WOOL, Items.BROWN_CARPET, 4);
        sever(out, Items.RED_WOOL, Items.RED_CARPET, 4);
        sever(out, Items.ORANGE_WOOL, Items.ORANGE_CARPET, 4);
        sever(out, Items.YELLOW_WOOL, Items.YELLOW_CARPET, 4);
        sever(out, Items.LIME_WOOL, Items.LIME_CARPET, 4);
        sever(out, Items.GREEN_WOOL, Items.GREEN_CARPET, 4);
        sever(out, Items.CYAN_WOOL, Items.CYAN_CARPET, 4);
        sever(out, Items.LIGHT_BLUE_WOOL, Items.LIGHT_BLUE_CARPET, 4);
        sever(out, Items.BLUE_WOOL, Items.BLUE_CARPET, 4);
        sever(out, Items.PURPLE_WOOL, Items.PURPLE_CARPET, 4);
        sever(out, Items.MAGENTA_WOOL, Items.MAGENTA_CARPET, 4);
        sever(out, Items.PINK_WOOL, Items.PINK_CARPET, 4);
        sever(out, Items.MOSS_BLOCK, Items.MOSS_CARPET, 4);
        sever(out, Items.SNOW_BLOCK, Items.SNOW, 4);
        // glass to panes
        sever(out, Items.WHITE_STAINED_GLASS, Items.WHITE_STAINED_GLASS_PANE, 4);
        sever(out, Items.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_STAINED_GLASS_PANE, 4);
        sever(out, Items.GRAY_STAINED_GLASS, Items.GRAY_STAINED_GLASS_PANE, 4);
        sever(out, Items.BLACK_STAINED_GLASS, Items.BLACK_STAINED_GLASS_PANE, 4);
        sever(out, Items.BROWN_STAINED_GLASS, Items.BROWN_STAINED_GLASS_PANE, 4);
        sever(out, Items.RED_STAINED_GLASS, Items.RED_STAINED_GLASS_PANE, 4);
        sever(out, Items.ORANGE_STAINED_GLASS, Items.ORANGE_STAINED_GLASS_PANE, 4);
        sever(out, Items.YELLOW_STAINED_GLASS, Items.YELLOW_STAINED_GLASS_PANE, 4);
        sever(out, Items.LIME_STAINED_GLASS, Items.LIME_STAINED_GLASS_PANE, 4);
        sever(out, Items.GREEN_STAINED_GLASS, Items.GREEN_STAINED_GLASS_PANE, 4);
        sever(out, Items.CYAN_STAINED_GLASS, Items.CYAN_STAINED_GLASS_PANE, 4);
        sever(out, Items.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_STAINED_GLASS_PANE, 4);
        sever(out, Items.BLUE_STAINED_GLASS, Items.BLUE_STAINED_GLASS_PANE, 4);
        sever(out, Items.PURPLE_STAINED_GLASS, Items.PURPLE_STAINED_GLASS_PANE, 4);
        sever(out, Items.MAGENTA_STAINED_GLASS, Items.MAGENTA_STAINED_GLASS_PANE, 4);
        sever(out, Items.PINK_STAINED_GLASS, Items.PINK_STAINED_GLASS_PANE, 4);
        sever(out, Items.GLASS, Items.GLASS_PANE, 4);
        // concrete to powder
        sever(out, Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER, 1);
        sever(out, Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER, 1);
        sever(out, Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER, 1);
        sever(out, Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER, 1);
        sever(out, Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER, 1);
        sever(out, Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER, 1);
        sever(out, Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER, 1);
        sever(out, Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER, 1);
        sever(out, Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER, 1);
        sever(out, Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER, 1);
        sever(out, Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER, 1);
        sever(out, Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER, 1);
        sever(out, Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER, 1);
        sever(out, Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER, 1);
        sever(out, Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER, 1);
        sever(out, Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER, 1);
        // coral block to coral
        sever(out, Items.TUBE_CORAL_BLOCK, Items.TUBE_CORAL, 3);
        sever(out, Items.BRAIN_CORAL_BLOCK, Items.BRAIN_CORAL, 3);
        sever(out, Items.BUBBLE_CORAL_BLOCK, Items.BUBBLE_CORAL, 3);
        sever(out, Items.FIRE_CORAL_BLOCK, Items.FIRE_CORAL, 3);
        sever(out, Items.HORN_CORAL_BLOCK, Items.HORN_CORAL, 3);
        sever(out, Items.DEAD_TUBE_CORAL_BLOCK, Items.DEAD_TUBE_CORAL, 3);
        sever(out, Items.DEAD_BRAIN_CORAL_BLOCK, Items.DEAD_BRAIN_CORAL, 3);
        sever(out, Items.DEAD_BUBBLE_CORAL_BLOCK, Items.DEAD_BUBBLE_CORAL, 3);
        sever(out, Items.DEAD_FIRE_CORAL_BLOCK, Items.DEAD_FIRE_CORAL, 3);
        sever(out, Items.DEAD_HORN_CORAL_BLOCK, Items.DEAD_HORN_CORAL, 3);
        // tools, weapons and armor
        sever(out, Items.FLINT_AND_STEEL, Items.FLINT, 1);
        sever(out, Items.SHEARS, Items.IRON_INGOT, 1, true);
        sever(out, Items.WOLF_ARMOR, Items.ARMADILLO_SCUTE, 5);
        sever(out, Items.LEATHER_BOOTS, Items.LEATHER, 2, true);
        sever(out, Items.LEATHER_HELMET, Items.LEATHER, 3, true);
        sever(out, Items.LEATHER_LEGGINGS, Items.LEATHER, 5, true);
        sever(out, Items.LEATHER_HORSE_ARMOR, Items.LEATHER, 5, true);
        sever(out, Items.LEATHER_CHESTPLATE, Items.LEATHER, 6, true);
        sever(out, Items.IRON_SWORD, Items.IRON_INGOT, 1, true);
        sever(out, Items.IRON_HOE, Items.IRON_INGOT, 1, true);
        sever(out, Items.IRON_AXE, Items.IRON_INGOT, 2, true);
        sever(out, Items.IRON_PICKAXE, Items.IRON_INGOT, 2, true);
        sever(out, Items.IRON_BOOTS, Items.IRON_INGOT, 2, true);
        sever(out, Items.IRON_HELMET, Items.IRON_INGOT, 3, true);
        sever(out, Items.IRON_LEGGINGS, Items.IRON_INGOT, 5, true);
        sever(out, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT, 5, true);
        sever(out, Items.IRON_CHESTPLATE, Items.IRON_INGOT, 6, true);
        sever(out, Items.CHAINMAIL_BOOTS, Items.IRON_NUGGET, 8, true);
        sever(out, Items.CHAINMAIL_HELMET, Items.IRON_NUGGET, 13, true);
        sever(out, Items.CHAINMAIL_LEGGINGS, Items.IRON_NUGGET, 20, true);
        sever(out, Items.CHAINMAIL_CHESTPLATE, Items.IRON_INGOT, 26, true);
        sever(out, Items.GOLDEN_SWORD, Items.GOLD_INGOT, 1, true);
        sever(out, Items.GOLDEN_HOE, Items.GOLD_INGOT, 1, true);
        sever(out, Items.GOLDEN_AXE, Items.GOLD_INGOT, 2, true);
        sever(out, Items.GOLDEN_PICKAXE, Items.GOLD_INGOT, 2, true);
        sever(out, Items.GOLDEN_BOOTS, Items.GOLD_INGOT, 2, true);
        sever(out, Items.GOLDEN_HELMET, Items.GOLD_INGOT, 3, true);
        sever(out, Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT, 5, true);
        sever(out, Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT, 5, true);
        sever(out, Items.GOLDEN_CHESTPLATE, Items.GOLD_INGOT, 6, true);
        sever(out, Items.DIAMOND_SWORD, Items.DIAMOND, 1, true);
        sever(out, Items.DIAMOND_HOE, Items.DIAMOND, 1, true);
        sever(out, Items.DIAMOND_AXE, Items.DIAMOND, 2, true);
        sever(out, Items.DIAMOND_PICKAXE, Items.DIAMOND, 2, true);
        sever(out, Items.DIAMOND_BOOTS, Items.DIAMOND, 2, true);
        sever(out, Items.DIAMOND_HELMET, Items.DIAMOND, 3, true);
        sever(out, Items.DIAMOND_LEGGINGS, Items.DIAMOND, 5, true);
        sever(out, Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND, 5, true);
        sever(out, Items.DIAMOND_CHESTPLATE, Items.DIAMOND, 6, true);
        sever(out, Items.NETHERITE_SWORD, Items.DIAMOND, 1, true);
        sever(out, Items.NETHERITE_HOE, Items.DIAMOND, 1, true);
        sever(out, Items.NETHERITE_AXE, Items.DIAMOND, 2, true);
        sever(out, Items.NETHERITE_PICKAXE, Items.DIAMOND, 2, true);
        sever(out, Items.NETHERITE_BOOTS, Items.DIAMOND, 2, true);
        sever(out, Items.NETHERITE_HELMET, Items.DIAMOND, 3, true);
        sever(out, Items.NETHERITE_LEGGINGS, Items.DIAMOND, 5, true);
        sever(out, Items.NETHERITE_CHESTPLATE, Items.DIAMOND, 6, true);
        sever(out, Items.TURTLE_HELMET, Items.TURTLE_SCUTE, 3);
    }

    private void sever(final @NotNull RecipeOutput out, final @NotNull Item input, final @NotNull Item output, final int amount) {
        sever(out, input, output, amount, false);
    }

    private void sever(final @NotNull RecipeOutput out, final @NotNull Item input, final @NotNull Item output, final int amount, boolean from) {
        final String inputPath = BuiltInRegistries.ITEM.getKey(input).getPath();
        final String outputPath = BuiltInRegistries.ITEM.getKey(output).getPath();
        new SeverRecipeBuilder(output, amount)
                .requires(input)
                .unlockedBy("has_" + inputPath, getItemCriterion(input))
                .save(out, Sunspot.id("sever/" + outputPath + (from ? "_from_" + inputPath : "")));
    }

    private void severStorageItem(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> storage, final @NotNull Item item) {
        severTag(out, storage, item, 9);
    }

    private void severTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount) {
        severTag(out, tag, output, amount, false);
    }

    private void severTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount, boolean from) {
        final String inputPath = tag.location().getPath();
        new SeverRecipeBuilder(output, amount)
                .requires(Ingredient.of(tag))
                .unlockedBy("has_" + inputPath, getItemCriterion(tag))
                .save(out, Sunspot.id("sever/" + BuiltInRegistries.ITEM.getKey(output).getPath() + (from ? "_from_" + inputPath : "")));
    }

    private void severPlanksFromLogs(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> logs, final @NotNull Item planks) {
        severPlanksFromLogs(out, logs, planks, 4);
    }

    private void severPlanksFromLogs(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> logs, final @NotNull Item planks, final int amount) {
        severTag(out, logs, planks, amount, false);
    }

    private void buildExtractRecipes(final @NotNull RecipeOutput out) {
        extractTag(out, ItemTags.CANDLES, Items.STRING, 1, true);
        extractTag(out, ItemTags.WOOL, Items.STRING, 4, true);
        extractTag(out, ItemTags.WOOL_CARPETS, Items.STRING, 1, true);
        extractTag(out, SPTagRegistry.CHEST_BOATS, Items.CHEST, 1, true);
        extract(out, Items.OAK_LOG, Items.STRIPPED_OAK_LOG, 1);
        extract(out, Items.OAK_WOOD, Items.STRIPPED_OAK_WOOD, 1);
        extract(out, Items.OAK_LEAVES, Items.OAK_SAPLING, 1);
        extract(out, Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG, 1);
        extract(out, Items.BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD, 1);
        extract(out, Items.BIRCH_LEAVES, Items.BIRCH_SAPLING, 1);
        extract(out, Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, 1);
        extract(out, Items.SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD, 1);
        extract(out, Items.SPRUCE_LEAVES, Items.SPRUCE_SAPLING, 1);
        extract(out, Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, 1);
        extract(out, Items.DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD, 1);
        extract(out, Items.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING, 1);
        extract(out, Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, 1);
        extract(out, Items.JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD, 1);
        extract(out, Items.JUNGLE_LEAVES, Items.JUNGLE_SAPLING, 1);
        extract(out, Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, 1);
        extract(out, Items.ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD, 1);
        extract(out, Items.ACACIA_LEAVES, Items.ACACIA_SAPLING, 1);
        extract(out, Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, 1);
        extract(out, Items.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD, 1);
        extract(out, Items.CHERRY_LEAVES, Items.CHERRY_SAPLING, 1);
        extract(out, Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, 1);
        extract(out, Items.MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD, 1);
        extract(out, Items.MANGROVE_LEAVES, Items.MANGROVE_PROPAGULE, 1);
        extract(out, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM, 1);
        extract(out, Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE, 1);
        extract(out, Items.NETHER_WART_BLOCK, Items.CRIMSON_FUNGUS, 1, true);
        extract(out, Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM, 1);
        extract(out, Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE, 1);
        extract(out, Items.WARPED_WART_BLOCK, Items.WARPED_FUNGUS, 1, true);
        extract(out, Items.BAMBOO_BLOCK, Items.STRIPPED_BAMBOO_BLOCK, 1);
        extract(out, Items.AZALEA_LEAVES, Items.AZALEA, 1);
        extract(out, Items.FLOWERING_AZALEA_LEAVES, Items.FLOWERING_AZALEA, 1);
        extract(out, Items.GILDED_BLACKSTONE, Items.GOLD_INGOT, 1);
        extract(out, Items.GRAVEL, Items.FLINT, 2, true);
        extract(out, Items.COARSE_DIRT, Items.GRAVEL, 1);
        extract(out, Items.GRASS_BLOCK, Items.WHEAT_SEEDS, 1, true);
        extract(out, Items.SHORT_GRASS, Items.WHEAT_SEEDS, 2, true);
        extract(out, Items.FERN, Items.WHEAT_SEEDS, 2, true);
        extract(out, Items.CRYING_OBSIDIAN, Items.OBSIDIAN, 1);
        extract(out, Items.CRIMSON_NYLIUM, Items.CRIMSON_FUNGUS, 1, true);
        extract(out, Items.WARPED_NYLIUM, Items.WARPED_FUNGUS, 1, true);
        extract(out, Items.RED_MUSHROOM_BLOCK, Items.RED_MUSHROOM, 1);
        extract(out, Items.BROWN_MUSHROOM_BLOCK, Items.BROWN_MUSHROOM, 1);
        extract(out, Items.GLOW_BERRIES, Items.GLOWSTONE_DUST, 1, true);
        extract(out, Items.GLOW_INK_SAC, Items.GLOWSTONE_DUST, 1, true);
        extract(out, Items.WET_SPONGE, Items.SPONGE, 1, true);
        extract(out, Items.MELON, Items.MELON_SEEDS, 9, true);
        extract(out, Items.PUMPKIN, Items.PUMPKIN_SEEDS, 9);
        extract(out, Items.HONEYCOMB_BLOCK, Items.HONEY_BLOCK, 1);
        extract(out, Items.OCHRE_FROGLIGHT, Items.GLOWSTONE_DUST, 3, true);
        extract(out, Items.VERDANT_FROGLIGHT, Items.GLOWSTONE_DUST, 3, true);
        extract(out, Items.PEARLESCENT_FROGLIGHT, Items.GLOWSTONE_DUST, 3, true);
        extract(out, Items.COBWEB, Items.STRING, 5, true);
        extract(out, Items.SCULK_CATALYST, Items.SCULK, 1);
        extract(out, Items.STONECUTTER, Items.IRON_INGOT, 1, true);
        extract(out, Items.CARTOGRAPHY_TABLE, Items.PAPER, 2);
        extract(out, Items.FLETCHING_TABLE, Items.FLINT, 2, true);
        extract(out, Items.SMITHING_TABLE, Items.IRON_INGOT, 2, true);
        extract(out, Items.GRINDSTONE, Items.STONE_SLAB, 1);
        extract(out, Items.LOOM, Items.STRING, 2, true);
        extract(out, Items.BLAST_FURNACE, Items.IRON_INGOT, 4, true);
        extract(out, Items.JUKEBOX, Items.DIAMOND, 1, true);
        extract(out, Items.ENCHANTING_TABLE, Items.BOOK, 1, true);
        extract(out, Items.END_CRYSTAL, Items.GHAST_TEAR, 1);
        extract(out, Items.BREWING_STAND, Items.BLAZE_ROD, 1);
        extract(out, Items.BEACON, Items.NETHER_STAR, 1);
        extract(out, Items.CONDUIT, Items.HEART_OF_THE_SEA, 1);
        extract(out, Items.LODESTONE, Items.NETHERITE_INGOT, 1); // TODO: must be changed in 1.21.5
        extract(out, Items.LADDER, Items.STICK, 2, true);
        extract(out, Items.SCAFFOLDING, Items.BAMBOO, 1);
        extract(out, Items.ARMOR_STAND, Items.SMOOTH_STONE_SLAB, 1);
        extract(out, Items.PAINTING, Items.STICK, 4, true);
        extract(out, Items.ITEM_FRAME, Items.STICK, 4, true);
        extract(out, Items.GLOW_ITEM_FRAME, Items.STICK, 4, true);
        extract(out, Items.BOOKSHELF, Items.BOOK, 3, true);
        extract(out, Items.LECTERN, Items.BOOK, 1, true);
        extract(out, Items.ENDER_EYE, Items.BLAZE_POWDER, 1, true);
        extract(out, Items.TARGET, Items.HAY_BLOCK, 1);
        extract(out, Items.CHEST_MINECART, Items.CHEST, 1, true);
        extract(out, Items.TNT_MINECART, Items.TNT, 1);
        extract(out, Items.FURNACE_MINECART, Items.FURNACE, 1);
        extract(out, Items.HOPPER_MINECART, Items.HOPPER, 1);
        extract(out, Items.BIG_DRIPLEAF, Items.SMALL_DRIPLEAF, 1);
        extract(out, Items.LEAD, Items.STRING, 2, true);
        extract(out, Items.FISHING_ROD, Items.STRING, 2, true);
        extract(out, Items.COMPASS, Items.REDSTONE, 1);
        extract(out, Items.RECOVERY_COMPASS, Items.COMPASS, 1);
        extract(out, Items.WRITABLE_BOOK, Items.BOOK, 1);
        extract(out, Items.CARROT_ON_A_STICK, Items.CARROT, 1);
        extract(out, Items.WARPED_FUNGUS_ON_A_STICK, Items.WARPED_FUNGUS, 1);
        extract(out, Items.MELON_SLICE, Items.MELON_SEEDS, 2, true);
        extract(out, Items.BREAD, Items.WHEAT, 1, true);
        extract(out, Items.GOLDEN_APPLE, Items.GOLD_INGOT, 6, true);
        extract(out, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLD_INGOT, 6, true);
        extract(out, Items.GLISTERING_MELON_SLICE, Items.GOLD_NUGGET, 6, true);
        extract(out, Items.GOLDEN_CARROT, Items.GOLD_NUGGET, 6, true);
        // weapons and armor
        extract(out, Items.MACE, Items.HEAVY_CORE, 1);
        extract(out, Items.NETHERITE_HELMET, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_LEGGINGS, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_BOOTS, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_SWORD, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_PICKAXE, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_AXE, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_HOE, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.NETHERITE_SHOVEL, Items.NETHERITE_INGOT, 1, true);
        extract(out, Items.SPECTRAL_ARROW, Items.ARROW, 1);
    }

    private void extract(final @NotNull RecipeOutput out, final @NotNull Item input, final @NotNull Item output, final int amount) {
        extract(out, input, output, amount, false);
    }

    private void extract(final @NotNull RecipeOutput out, final @NotNull Item input, final @NotNull Item output, final int amount, boolean from) {
        final String inputPath = BuiltInRegistries.ITEM.getKey(input).getPath();
        final String outputPath = BuiltInRegistries.ITEM.getKey(output).getPath();
        new ExtractRecipeBuilder(output, amount)
                .requires(input)
                .unlockedBy("has_" + inputPath, getItemCriterion(input))
                .save(out, Sunspot.id("extract/" + outputPath + (from ? "_from_" + inputPath : "")));
    }

    private void extractTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount) {
        extractTag(out, tag, output, amount, false);
    }

    private void extractTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount, final boolean from) {
        final String inputPath = tag.location().getPath();
        final String outputPath = BuiltInRegistries.ITEM.getKey(output).getPath();
        new ExtractRecipeBuilder(output, amount)
                .requires(Ingredient.of(tag))
                .unlockedBy("has_" + inputPath, getItemCriterion(tag))
                .save(out, Sunspot.id("extract/" + outputPath + (from ? "_from_" + inputPath : "")));
    }

    private static @NotNull Criterion<InventoryChangeTrigger.TriggerInstance> getItemCriterion(final @NotNull Supplier<Item> item) {
        return getItemCriterion(item.get());
    }

    private static @NotNull Criterion<InventoryChangeTrigger.TriggerInstance> getItemCriterion(final @NotNull Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    private static @NotNull Criterion<InventoryChangeTrigger.TriggerInstance> getItemCriterion(final @NotNull TagKey<Item> tag) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag));
    }
}
