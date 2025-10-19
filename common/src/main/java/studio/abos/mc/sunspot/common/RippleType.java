package studio.abos.mc.sunspot.common;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class RippleType {

    protected final @NotNull ResourceLocation id;
    protected final @NotNull String translation;
    protected final int tint;

    public RippleType(final @NotNull ResourceLocation id, final int tint) {
        this.id = id;
        translation = id.getPath().toUpperCase(Locale.ROOT).charAt(0) + id.getPath().substring(1);
        this.tint = tint;
    }

    public @NotNull ResourceLocation getId() {
        return id;
    }

    public @NotNull String getTranslation() {
        return translation;
    }

    public int getTint() {
        return tint;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
