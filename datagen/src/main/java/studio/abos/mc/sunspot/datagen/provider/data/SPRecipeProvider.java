package studio.abos.mc.sunspot.datagen.provider.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;
import studio.abos.mc.sunspot.datagen.builder.SeverRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class SPRecipeProvider extends FabricRecipeProvider {

    public SPRecipeProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(final RecipeOutput out) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_2.get(), 4)
                .pattern(" S ")
                .pattern("S S")
                .pattern(" S ")
                .define('S', SPTagRegistry.SUBSTRATE_MATERIAL)
                .unlockedBy("has_material", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SPTagRegistry.SUBSTRATE_MATERIAL)))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_3.get())
                .pattern(" S ")
                .pattern("SDS")
                .pattern(" S ")
                .define('D', Items.DIAMOND)
                .define('S', SPItemPreRegistry.SUBSTRATE_2.get())
                .unlockedBy("has_substrate_2", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_2.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.SUBSTRATE_4.get())
                .pattern("ESE")
                .pattern("SSS")
                .pattern("ESE")
                .define('E', Items.ENDER_PEARL)
                .define('S', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_3", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_3.get()))
                .save(out);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItemPreRegistry.ASH_RESIDUE_BLOCK.get())
                .requires(SPItemPreRegistry.ASH_RESIDUE.get(), 9)
                .unlockedBy("has_ash_residue", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.ASH_RESIDUE.get()))
                .save(out);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SPItemPreRegistry.ASH_RESIDUE.get(), 9)
                .requires(SPItemPreRegistry.ASH_RESIDUE_BLOCK.get())
                .unlockedBy("has_ash_block", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.ASH_RESIDUE_BLOCK.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.LM_WORKBENCH.get())
                .pattern("SS")
                .pattern("PP")
                .pattern("PP")
                .define('P', ItemTags.PLANKS)
                .define('S', SPItemPreRegistry.SUBSTRATE_2.get())
                .unlockedBy("has_substrate_2", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_2.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_HELMET.get())
                .pattern("STS")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_CHESTPLATE.get())
                .pattern("T T")
                .pattern("SSS")
                .pattern("TST")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("T T")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.MANTLE_BASE_BOOTS.get())
                .pattern("S S")
                .pattern("T T")
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.FOURSPACE_SHIFTER.get())
                .pattern("ETE")
                .pattern("TST")
                .pattern("ETE")
                .define('E', Items.ENDER_PEARL)
                .define('S', SPItemPreRegistry.SUBSTRATE_4.get())
                .define('T', SPItemPreRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemPreRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemPreRegistry.FLAMEFALL_ROD.get())
                .pattern(" F ")
                .pattern("PRP")
                .pattern("PRP")
                .define('F', Items.ENDER_PEARL)
                .define('P', Items.BLAZE_POWDER)
                .define('R', Items.BLAZE_ROD)
                .unlockedBy("has_blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
                .save(out);
        new SeverRecipeBuilder(Items.GRAVEL)
                .requires(Ingredient.of(SPTagRegistry.SEVERED_INTO_GRAVEL))
                .unlockedBy("has_material", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SPTagRegistry.SEVERED_INTO_GRAVEL)))
                .save(out, Sunspot.id("sever/gravel"));
    }
}
