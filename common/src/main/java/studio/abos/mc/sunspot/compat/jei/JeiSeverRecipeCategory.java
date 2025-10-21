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
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.recipe.SeverRecipe;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;

public class JeiSeverRecipeCategory implements IRecipeCategory<SeverRecipe> {

    public static final RecipeType<SeverRecipe> TYPE = RecipeType.create(Sunspot.MOD_ID, SPGlyphTypeRegistry.SEVER.get().getId().getPath(), SeverRecipe.class);
    public static final ResourceLocation PROGRESS_SPRITE = Sunspot.id("container/sever/progress");

    protected final @NotNull IGuiHelper guiHelper;

    public JeiSeverRecipeCategory(final @NotNull IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public @NotNull RecipeType<SeverRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder builder, final @NotNull SeverRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addInputSlot(1, 5).setStandardSlotBackground().addIngredients(recipe.getIngredient());
        builder.addOutputSlot(61, 5).setOutputSlotBackground().addItemStack(recipe.getResult());
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal(SPGlyphTypeRegistry.SEVER.get().getTranslation() + "ing");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return guiHelper.createDrawableItemLike(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SEVER).get());
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
    public void createRecipeExtras(final @NotNull IRecipeExtrasBuilder builder, final @NotNull SeverRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addWidget(new JeiSpriteWidget(PROGRESS_SPRITE, 24, 0, 24, 24));
    }
}
