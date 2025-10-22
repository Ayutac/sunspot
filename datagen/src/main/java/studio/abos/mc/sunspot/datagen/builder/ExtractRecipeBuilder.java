package studio.abos.mc.sunspot.datagen.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.recipe.ExtractRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExtractRecipeBuilder implements RecipeBuilder {

    private Ingredient ingredient;
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public ExtractRecipeBuilder(final ItemLike result) {
        this(new ItemStack(result));
    }

    public ExtractRecipeBuilder(final ItemLike result, final int amount) {
        this(new ItemStack(result, amount));
    }

    public ExtractRecipeBuilder(final ItemStack result) {
        this.result = result;
    }

    @Override
    public @NotNull ExtractRecipeBuilder unlockedBy(final String string, final Criterion<?> criterion) {
        criteria.put(string, criterion);
        return this;
    }

    @Override
    public @NotNull ExtractRecipeBuilder group(final @Nullable String string) {
        // group is ignored for now
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result.getItem();
    }

    public ExtractRecipeBuilder requires(final @NotNull ItemLike ingredient) {
        return requires(Ingredient.of(ingredient));
    }

    public ExtractRecipeBuilder requires(final @NotNull Ingredient ingredient) {
        if (this.ingredient != null) {
            throw new IllegalStateException("Ingredient already defined!");
        }
        this.ingredient = ingredient;
        return this;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation resourceLocation) {
        ensureValid(resourceLocation);
        final Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        final ExtractRecipe extractRecipe = new ExtractRecipe(ingredient, result);
        recipeOutput.accept(resourceLocation, extractRecipe, builder.build(resourceLocation.withPrefix("recipes/extract/")));
    }

    private void ensureValid(ResourceLocation resourceLocation) {
        if (criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceLocation + "!");
        }
        if (ingredient == null) {
            throw new IllegalStateException("No ingredient specified for recipe " + resourceLocation + "!");
        }
    }
}
