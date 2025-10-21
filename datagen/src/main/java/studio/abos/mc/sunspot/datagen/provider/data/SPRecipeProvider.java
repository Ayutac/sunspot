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
                .define('S', SPTagRegistry.SUBSTRATE_MATERIAL)
                .unlockedBy("has_material",getItemCriterion(SPTagRegistry.SUBSTRATE_MATERIAL))
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
                .unlockedBy("has_substrate_2",getItemCriterion(SPItemPreRegistry.SUBSTRATE_2))
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
        severTag(out, SPTagRegistry.SEVERED_INTO_COBBLESTONE, Items.COBBLESTONE);
        severTag(out, SPTagRegistry.SEVERED_INTO_COBBLED_DEEPSLATE, Items.COBBLED_DEEPSLATE);
        severTag(out, SPTagRegistry.SEVERED_INTO_BLACKSTONE, Items.BLACKSTONE);
        severTag(out, SPTagRegistry.SEVERED_INTO_GRAVEL, Items.GRAVEL);
        severTag(out, SPTagRegistry.SEVERED_INTO_SAND, Items.SAND);
        severTag(out, SPTagRegistry.SEVERED_INTO_RED_SAND, Items.RED_SAND);
        severTag(out, SPTagRegistry.SEVERED_INTO_DIRT, Items.DIRT);
        severTag(out, SPTagRegistry.SEVERED_INTO_NETHERRACK, Items.NETHERRACK);
        severTag(out, SPTagRegistry.SEVERED_INTO_FOUR_NETHER_BRICK, Items.NETHER_BRICK, 4);
        severTag(out, SPTagRegistry.SEVERED_INTO_FOUR_NETHER_QUARTZ, Items.QUARTZ, 4);
        severTag(out, SPTagRegistry.CANDLES, Items.STRING);
        severTag(out, TagKey.create(Registries.ITEM, Sunspot.c("shulker_boxes")), Items.SHULKER_SHELL, 2);
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
                .save(out, Sunspot.id("sever/" + outputPath  + (from ? "_from_" + inputPath : "")));
    }

    private void severStorageItem(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> storage, final @NotNull Item item) {
        severTag(out, storage, item, 9);
    }

    private void severTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output) {
        severTag(out, tag, output, 1);
    }

    private void severTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount) {
        severTag(out, tag, output, amount, "material");
    }

    private void severTag(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> tag, final @NotNull Item output, final int amount, String has) {
        new SeverRecipeBuilder(output, amount)
                .requires(Ingredient.of(tag))
                .unlockedBy("has_" + has, getItemCriterion(tag))
                .save(out, Sunspot.id("sever/" + BuiltInRegistries.ITEM.getKey(output).getPath()));
    }

    private void severPlanksFromLogs(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> logs, final @NotNull Item planks) {
        severPlanksFromLogs(out, logs, planks, 4);
    }

    private void severPlanksFromLogs(final @NotNull RecipeOutput out, final @NotNull TagKey<Item> logs, final @NotNull Item planks, final int amount) {
        severTag(out, logs, planks, amount, "logs");
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
