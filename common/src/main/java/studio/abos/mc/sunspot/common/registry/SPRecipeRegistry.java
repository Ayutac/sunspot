package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.recipe.ExtractRecipe;
import studio.abos.mc.sunspot.common.recipe.SeverRecipe;
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipe;

public interface SPRecipeRegistry {

    DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.RECIPE_SERIALIZER);

    RegistrySupplier<RecipeSerializer<WorkbenchRecipe>> WORKBENCH_SERIALIZER = SERIALIZER_REGISTRY.register(Identifiers.WORKBENCH, WorkbenchRecipe.Serializer::new);
    RegistrySupplier<RecipeSerializer<SeverRecipe>> SEVER_SERIALIZER = SERIALIZER_REGISTRY.register(Identifiers.SEVER, SeverRecipe.Serializer::new);
    RegistrySupplier<RecipeSerializer<ExtractRecipe>> EXTRACT_SERIALIZER = SERIALIZER_REGISTRY.register(Identifiers.EXTRACT, ExtractRecipe.Serializer::new);

    DeferredRegister<RecipeType<?>> TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.RECIPE_TYPE);

    RegistrySupplier<RecipeType<WorkbenchRecipe>> WORKBENCH_TYPE = registerType(Identifiers.WORKBENCH);
    RegistrySupplier<RecipeType<SeverRecipe>> SEVER_TYPE = registerType(Identifiers.SEVER);
    RegistrySupplier<RecipeType<ExtractRecipe>> EXTRACT_TYPE = registerType(Identifiers.EXTRACT);

    static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> registerType(final @NotNull ResourceLocation id) {
        return TYPE_REGISTRY.register(id, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return id.getPath();
            }
        });
    }

    static void register() {
        SERIALIZER_REGISTRY.register();
        TYPE_REGISTRY.register();
    }
}
