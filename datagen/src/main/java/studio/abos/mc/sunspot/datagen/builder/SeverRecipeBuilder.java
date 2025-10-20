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
import studio.abos.mc.sunspot.common.recipe.SeverRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class SeverRecipeBuilder implements RecipeBuilder {

    private Ingredient ingredient;
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SeverRecipeBuilder(final ItemLike result) {
        this(new ItemStack(result));
    }

    public SeverRecipeBuilder(final ItemStack result) {
        this.result = result;
    }

    @Override
    public @NotNull SeverRecipeBuilder unlockedBy(final String string, final Criterion<?> criterion) {
        criteria.put(string, criterion);
        return this;
    }

    @Override
    public @NotNull SeverRecipeBuilder group(final @Nullable String string) {
        // group is ignored for now
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result.getItem();
    }

    public SeverRecipeBuilder requires(final @NotNull ItemLike ingredient) {
        return requires(Ingredient.of(ingredient));
    }

    public SeverRecipeBuilder requires(final @NotNull Ingredient ingredient) {
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
        final SeverRecipe severRecipe = new SeverRecipe(ingredient, result);
        recipeOutput.accept(resourceLocation, severRecipe, builder.build(resourceLocation.withPrefix("recipes/sever/")));
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
