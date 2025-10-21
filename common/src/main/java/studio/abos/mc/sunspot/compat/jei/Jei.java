package studio.abos.mc.sunspot.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.registry.SPGlyphTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
import studio.abos.mc.sunspot.common.registry.SPRecipeRegistry;

@JeiPlugin
public class Jei implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return Sunspot.id(Sunspot.MOD_ID);
    }

    @Override
    public void registerCategories(final @NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new JeiSeverRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(final @NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(SPItemRegistry.GLYPH_BLOCK_MAP.get(SPGlyphTypeRegistry.SEVER).get(), JeiSeverRecipeCategory.TYPE);
    }

    @Override
    public void registerRecipes(final @NotNull IRecipeRegistration registration) {
        registration.addRecipes(JeiSeverRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(SPRecipeRegistry.SEVER_TYPE.get()).stream().map(RecipeHolder::value).toList());
    }
}
