package studio.abos.mc.sunspot.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

public class WorkbenchRecipe implements Recipe<WorkbenchRecipeInput> {

    protected @NotNull final Ingredient ingredient;
    protected @NotNull final Ingredient intent;
    protected final int flame;
    private @NotNull final ItemStack result;

    public WorkbenchRecipe(final @NotNull Ingredient ingredient, final @NotNull Ingredient intent, final int flame, final @NotNull ItemStack result) {
        this.ingredient = ingredient;
        this.intent = intent;
        if (flame < 0) {
            throw new IllegalArgumentException("Flame cannot be negative!");
        }
        this.flame = flame;
        this.result = result;
    }

    @Override
    public boolean matches(final @NotNull WorkbenchRecipeInput recipeInput, final Level level) {
        return ingredient.test(recipeInput.ingredient()) && intent.test(recipeInput.intent());
    }

    @Override
    public @NotNull ItemStack assemble(final @NotNull WorkbenchRecipeInput recipeInput, final @NotNull HolderLookup.Provider lookup) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider lookup) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SPRecipeRegistry.WORKBENCH_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SPRecipeRegistry.WORKBENCH_TYPE.get();
    }

    public @NotNull Ingredient getIngredient() {
        return ingredient;
    }

    public @NotNull Ingredient getIntent() {
        return intent;
    }

    public int getFlame() {
        return flame;
    }

    public @NotNull ItemStack getResult() {
        return result;
    }

    public static class Serializer implements RecipeSerializer<WorkbenchRecipe> {
        private static final MapCodec<WorkbenchRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.CODEC.fieldOf("ingredient").forGetter(WorkbenchRecipe::getIngredient), Ingredient.CODEC.fieldOf("intent").forGetter(WorkbenchRecipe::getIntent), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("flame").forGetter(WorkbenchRecipe::getFlame), ItemStack.CODEC.fieldOf("result").forGetter(WorkbenchRecipe::getResult)).apply(instance, WorkbenchRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, WorkbenchRecipe> STREAM_CODEC = StreamCodec.of(WorkbenchRecipe.Serializer::toNetwork, WorkbenchRecipe.Serializer::fromNetwork);

        public @NotNull MapCodec<WorkbenchRecipe> codec() {
            return CODEC;
        }

        public @NotNull StreamCodec<RegistryFriendlyByteBuf, WorkbenchRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static WorkbenchRecipe fromNetwork(RegistryFriendlyByteBuf byteBuf) {
            return new WorkbenchRecipe(Ingredient.CONTENTS_STREAM_CODEC.decode(byteBuf), Ingredient.CONTENTS_STREAM_CODEC.decode(byteBuf), byteBuf.readInt(), ItemStack.STREAM_CODEC.decode(byteBuf));
        }

        private static void toNetwork(RegistryFriendlyByteBuf byteBuf, WorkbenchRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(byteBuf, recipe.getIngredient());
            Ingredient.CONTENTS_STREAM_CODEC.encode(byteBuf, recipe.getIntent());
            byteBuf.writeInt(recipe.getFlame());
            ItemStack.STREAM_CODEC.encode(byteBuf, recipe.getResult());
        }
    }
}
