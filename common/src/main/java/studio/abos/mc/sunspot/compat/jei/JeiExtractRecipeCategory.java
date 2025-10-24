package studio.abos.mc.sunspot.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.recipe.ExtractRecipe;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;

public class JeiExtractRecipeCategory implements IRecipeCategory<ExtractRecipe> {

    public static final RecipeType<ExtractRecipe> TYPE = RecipeType.create(Sunspot.MOD_ID, Identifiers.EXTRACT.getPath(), ExtractRecipe.class);
    public static final ResourceLocation PROGRESS_SPRITE = Sunspot.id("container/extract/progress");

    protected final @NotNull IGuiHelper guiHelper;

    public JeiExtractRecipeCategory(final @NotNull IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public @NotNull RecipeType<ExtractRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder builder, final @NotNull ExtractRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addInputSlot(1, 5).setStandardSlotBackground().addIngredients(recipe.getIngredient());
        builder.addOutputSlot(61, 5).setOutputSlotBackground().addItemStack(recipe.getResult());
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal(SPGlyphTypeRegistry.EXTRACT.get().getTranslation() + "ing");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return guiHelper.createDrawableItemLike(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.EXTRACT).get());
    }

    @Override
    public int getWidth() {
        return 82;
    }

    @Override
    public int getHeight() {
        return 26;
    }

    @Override
    public void createRecipeExtras(final @NotNull IRecipeExtrasBuilder builder, final @NotNull ExtractRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addWidget(new JeiSpriteWidget(PROGRESS_SPRITE, 23, 5, 32, 16));
    }
}
