package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.recipe.SeverRecipe;
import studio.abos.mc.sunspot.common.recipe.WorkbenchRecipe;

public interface SPRecipeRegistry {

    DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.RECIPE_SERIALIZER);

    RegistrySupplier<RecipeSerializer<WorkbenchRecipe>> WORKBENCH_SERIALIZER = SERIALIZER_REGISTRY.register(Sunspot.id("workbench"), WorkbenchRecipe.Serializer::new);
    RegistrySupplier<RecipeSerializer<SeverRecipe>> SEVER_SERIALIZER = SERIALIZER_REGISTRY.register(Identifiers.SEVER, SeverRecipe.Serializer::new);

    DeferredRegister<RecipeType<?>> TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.RECIPE_TYPE);

    RegistrySupplier<RecipeType<SeverRecipe>> WORKBENCH_TYPE = TYPE_REGISTRY.register(Sunspot.id("workbench"), () -> new RecipeType<>() {
        @Override
        public String toString() {
            return "workbench";
        }
    });
    RegistrySupplier<RecipeType<SeverRecipe>> SEVER_TYPE = TYPE_REGISTRY.register(Identifiers.SEVER, () -> new RecipeType<>() {
        @Override
        public String toString() {
            return Identifiers.SEVER.getPath();
        }
    });

    static void register() {
        SERIALIZER_REGISTRY.register();
        TYPE_REGISTRY.register();
    }
}
