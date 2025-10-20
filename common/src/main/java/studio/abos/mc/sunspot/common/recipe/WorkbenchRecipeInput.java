package studio.abos.mc.sunspot.common.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record WorkbenchRecipeInput(ItemStack ingredient, ItemStack intent) implements RecipeInput {

    @Override
    public @NotNull ItemStack getItem(int i) {
        return switch (i) {
            case 0 -> ingredient();
            case 1 -> intent();
            default -> throw new IllegalArgumentException("Invalid item index " + i + "!");
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
