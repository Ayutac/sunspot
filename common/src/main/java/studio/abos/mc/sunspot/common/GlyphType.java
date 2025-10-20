package studio.abos.mc.sunspot.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

public class GlyphType {

    protected final @NotNull ResourceLocation id;
    protected final @NotNull ResourceLocation blockItemId;
    protected final @NotNull String translation;
    protected final @NotNull Supplier<Item> intent;
    protected final @NotNull Supplier<Item> substrate;
    protected final int tint;

    public GlyphType(final @NotNull ResourceLocation id, final @NotNull Supplier<Item> intent, final @NotNull Supplier<Item> substrate, final int tint) {
        this.id = id;
        blockItemId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + "_block");
        translation = "{" + id.getPath().toUpperCase(Locale.ROOT) + "}";
        this.intent = intent;
        this.substrate = substrate;
        this.tint = tint;
    }

    public @NotNull ResourceLocation getId() {
        return id;
    }

    public @NotNull ResourceLocation getBlockItemId() {
        return blockItemId;
    }

    public @NotNull String getTranslation() {
        return translation;
    }

    public @NotNull Supplier<Item> getIntent() {
        return intent;
    }

    public @NotNull Supplier<Item> getSubstrate() {
        return substrate;
    }

    public int getTint() {
        return tint;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
