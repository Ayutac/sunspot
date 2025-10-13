package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPRecipeProvider extends FabricRecipeProvider {

    public SPRecipeProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(final RecipeOutput out) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.SUBSTRATE_2.get(), 4)
                .pattern(" S ")
                .pattern("S S")
                .pattern(" S ")
                .define('S', SPTagRegistry.SUBSTRATE_MATERIAL)
                .unlockedBy("has_material", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SPTagRegistry.SUBSTRATE_MATERIAL)))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.SUBSTRATE_3.get())
                .pattern(" S ")
                .pattern("SDS")
                .pattern(" S ")
                .define('D', Items.DIAMOND)
                .define('S', SPItemRegistry.SUBSTRATE_2.get())
                .unlockedBy("has_substrate_2", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_2.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.SUBSTRATE_4.get())
                .pattern("ESE")
                .pattern("SSS")
                .pattern("ESE")
                .define('E', Items.ENDER_PEARL)
                .define('S', SPItemRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_3", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_3.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.MANTLE_BASE_HELMET.get())
                .pattern("STS")
                .pattern("T T")
                .define('S', SPItemRegistry.SUBSTRATE_4.get())
                .define('T', SPItemRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.MANTLE_BASE_CHESTPLATE.get())
                .pattern("T T")
                .pattern("SSS")
                .pattern("TST")
                .define('S', SPItemRegistry.SUBSTRATE_4.get())
                .define('T', SPItemRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.MANTLE_BASE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("T T")
                .pattern("T T")
                .define('S', SPItemRegistry.SUBSTRATE_4.get())
                .define('T', SPItemRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_4.get()))
                .save(out);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPItemRegistry.MANTLE_BASE_BOOTS.get())
                .pattern("S S")
                .pattern("T T")
                .define('S', SPItemRegistry.SUBSTRATE_4.get())
                .define('T', SPItemRegistry.SUBSTRATE_3.get())
                .unlockedBy("has_substrate_4", InventoryChangeTrigger.TriggerInstance.hasItems(SPItemRegistry.SUBSTRATE_4.get()))
                .save(out);
    }
}
