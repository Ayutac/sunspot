package studio.abos.mc.sunspot.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
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
    public static final ResourceLocation BACKGROUND = Sunspot.id("textures/gui/jei/sever.png");
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
        builder.addInputSlot(0, 0).addIngredients(recipe.getIngredient());
        builder.addOutputSlot(16, 16).addItemStack(recipe.getResult());
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal(SPGlyphTypeRegistry.SEVER.get().getTranslation());
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
    public void draw(final @NotNull SeverRecipe recipe, final @NotNull IRecipeSlotsView recipeSlotsView, final @NotNull GuiGraphics guiGraphics, final double mouseX, final double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(BACKGROUND, 0, 0, 0, 0, getWidth(), getHeight());
    }
}
