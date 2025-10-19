package studio.abos.mc.sunspot.common.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;
import studio.abos.mc.sunspot.common.RippleType;

public interface SPRegistries {

    RegistrarManager MANAGER = RegistrarManager.get(Sunspot.MOD_ID);

    // Registries
    Registrar<GlyphType> GLYPH_TYPE_REGISTRY = MANAGER.<GlyphType>builder(Sunspot.id("glyph_type"))
            .syncToClients()
            .build();
    Registrar<RippleType> RIPPLE_TYPE_REGISTRY = MANAGER.<RippleType>builder(Sunspot.id("ripple_type"))
            .syncToClients()
            .build();

    // Registry keys
    ResourceKey<Registry<GlyphType>> GLYPH_TYPE_REGISTRY_KEY = createKey(GLYPH_TYPE_REGISTRY);
    ResourceKey<Registry<RippleType>> RIPPLE_TYPE_REGISTRY_KEY = createKey(RIPPLE_TYPE_REGISTRY);

    // Registry codecs
    Codec<GlyphType> GLYPH_TYPE_CODEC = createCodec(GLYPH_TYPE_REGISTRY);
    Codec<RippleType> RIPPLE_TYPE_CODEC = createCodec(RIPPLE_TYPE_REGISTRY);

    private static <T> ResourceKey<Registry<T>> createKey(final @NotNull Registrar<T> registrar) {
        return ResourceKey.createRegistryKey(registrar.key().location());
    }

    private static <T> Codec<T> createCodec(final @NotNull Registrar<T> registrar) {
        return ResourceLocation.CODEC.flatXmap(rl -> {
            final T t = registrar.get(rl);
            if (t == null) {
                return DataResult.error(() -> "Could not find " + registrar.key().location() + " with ID: " + rl);
            }

            return DataResult.success(t);
        }, t -> {
            final ResourceLocation rl = registrar.getId(t);
            if (rl == null) {
                return DataResult.error(() -> "Could not find ID for " + registrar.key().location() + ": " + t);
            }
            return DataResult.success(rl);
        });
    }

    static void init() {
        // Intentionally left empty
    }
}
