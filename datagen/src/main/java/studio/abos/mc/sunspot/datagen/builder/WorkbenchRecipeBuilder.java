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
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class WorkbenchRecipeBuilder implements RecipeBuilder {

    private Ingredient ingredient;
    private Ingredient intent;
    private int flame;
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public WorkbenchRecipeBuilder(final @NotNull ItemLike result) {
        this(new ItemStack(result));
    }

    public WorkbenchRecipeBuilder(final @NotNull ItemStack result) {
        this.result = result;
    }

    @Override
    public @NotNull WorkbenchRecipeBuilder unlockedBy(final @NotNull String string, final @NotNull Criterion<?> criterion) {
        criteria.put(string, criterion);
        return this;
    }

    @Override
    public @NotNull WorkbenchRecipeBuilder group(final @Nullable String string) {
        // group is ignored for now
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result.getItem();
    }

    public WorkbenchRecipeBuilder requires(final @NotNull ItemLike ingredient) {
        return requires(Ingredient.of(ingredient));
    }

    public WorkbenchRecipeBuilder requires(final @NotNull Ingredient ingredient) {
        if (this.ingredient != null) {
            throw new IllegalStateException("Ingredient already defined!");
        }
        this.ingredient = ingredient;
        return this;
    }

    public WorkbenchRecipeBuilder intent(final @NotNull ItemLike ingredient) {
        return intent(Ingredient.of(ingredient));
    }

    public WorkbenchRecipeBuilder intent(final @NotNull Ingredient ingredient) {
        if (this.intent != null) {
            throw new IllegalStateException("Ingredient already defined!");
        }
        this.intent = ingredient;
        return this;
    }

    public WorkbenchRecipeBuilder flame(final int flame) {
        if (flame < 0) {
            throw new IllegalArgumentException("Flame cannot be negative!");
        }
        this.flame = flame;
        return this;
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation resourceLocation) {
        ensureValid(resourceLocation);
        final Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        final WorkbenchRecipe workbenchRecipe = new WorkbenchRecipe(ingredient, intent, flame, result);
        recipeOutput.accept(resourceLocation, workbenchRecipe, builder.build(resourceLocation.withPrefix("recipes/sever/")));
    }

    private void ensureValid(final @NotNull ResourceLocation resourceLocation) {
        if (criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceLocation + "!");
        }
        if (ingredient == null) {
            throw new IllegalStateException("No ingredient specified for recipe " + resourceLocation + "!");
        }
        if (intent == null) {
            throw new IllegalStateException("No intent specified for recipe " + resourceLocation + "!");
        }
    }
}
