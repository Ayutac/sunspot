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
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipe;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;

public class JeiWorkbenchRecipeCategory implements IRecipeCategory<WorkbenchRecipe> {

    public static final RecipeType<WorkbenchRecipe> TYPE = RecipeType.create(Sunspot.MOD_ID, Identifiers.WORKBENCH.getPath(), WorkbenchRecipe.class);
    public static final ResourceLocation PROGRESS_SPRITE = Sunspot.id("container/workbench/progress");

    protected final @NotNull IGuiHelper guiHelper;

    public JeiWorkbenchRecipeCategory(final @NotNull IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public @NotNull RecipeType<WorkbenchRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder builder, final @NotNull WorkbenchRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addInputSlot(1, 5).setStandardSlotBackground().addIngredients(recipe.getIngredient());
        builder.addInputSlot(29, 32).setStandardSlotBackground().addIngredients(recipe.getIntent());
        builder.addOutputSlot(61, 5).setOutputSlotBackground().addItemStack(recipe.getResult());
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Weaving");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return guiHelper.createDrawableItemLike(SPItemPreRegistry.LM_WORKBENCH.get());
    }

    @Override
    public int getWidth() {
        return 82;
    }

    @Override
    public int getHeight() {
        return 49;
    }

    @Override
    public void createRecipeExtras(final @NotNull IRecipeExtrasBuilder builder, final @NotNull WorkbenchRecipe recipe, final @NotNull IFocusGroup focuses) {
        builder.addWidget(new JeiSpriteWidget(PROGRESS_SPRITE, 25, 0, 24, 24));
    }
}
