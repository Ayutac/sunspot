package studio.abos.mc.sunspot.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

public class SeverRecipe implements Recipe<SingleRecipeInput> {

    protected @NotNull final Ingredient ingredient;
    private @NotNull final ItemStack result;

    public SeverRecipe(final @NotNull Ingredient ingredient, final @NotNull ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(final @NotNull SingleRecipeInput recipeInput, final Level level) {
        return ingredient.test(recipeInput.item());
    }

    @Override
    public @NotNull ItemStack assemble(final SingleRecipeInput recipeInput, final HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SPRecipeRegistry.SEVER_TYPE.get();
    }

    public @NotNull Ingredient getIngredient() {
        return ingredient;
    }

    public @NotNull ItemStack getResult() {
        return result;
    }

    public static class Serializer implements RecipeSerializer<SeverRecipe> {
        private static final MapCodec<SeverRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.CODEC.fieldOf("ingredient").forGetter(SeverRecipe::getIngredient), ItemStack.CODEC.fieldOf("result").forGetter(SeverRecipe::getResult)).apply(instance, SeverRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, SeverRecipe> STREAM_CODEC = StreamCodec.of(SeverRecipe.Serializer::toNetwork, SeverRecipe.Serializer::fromNetwork);

        public @NotNull MapCodec<SeverRecipe> codec() {
            return CODEC;
        }

        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SeverRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SeverRecipe fromNetwork(RegistryFriendlyByteBuf byteBuf) {
            return new SeverRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(byteBuf), ItemStack.STREAM_CODEC.decode(byteBuf));
        }

        private static void toNetwork(RegistryFriendlyByteBuf byteBuf, SeverRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(byteBuf, recipe.getIngredient());
            ItemStack.STREAM_CODEC.encode(byteBuf, recipe.getResult());
        }
    }
}
