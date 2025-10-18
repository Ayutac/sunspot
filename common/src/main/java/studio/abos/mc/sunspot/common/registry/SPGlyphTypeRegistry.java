package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.GlyphType;

import java.util.function.Supplier;

public interface SPGlyphTypeRegistry {

    DeferredRegister<GlyphType> GLYPH_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, SPRegistries.GLYPH_TYPE_REGISTRY_KEY);

    RegistrySupplier<GlyphType> AFFIX = register(Identifiers.AFFIX, () -> Items.SLIME_BALL, 0x2b6f3a);
    RegistrySupplier<GlyphType> ASH = register(Identifiers.ASH, () -> Items.SOUL_CAMPFIRE, 0x551b1b);
    RegistrySupplier<GlyphType> COMPOSE = register(Identifiers.COMPOSE, () -> Items.BUNDLE, 0x1412ae);
    RegistrySupplier<GlyphType> IMPEL = register(Identifiers.IMPEL, () -> Items.PISTON, 0x65b7db);
    RegistrySupplier<GlyphType> OFFSET = register(Identifiers.OFFSET, () -> Items.ENDER_PEARL, 0xd8f2f1);
    RegistrySupplier<GlyphType> REVITALISE = register(Identifiers.REVITALISE, () -> Items.GLISTERING_MELON_SLICE, 0x58e125);
    RegistrySupplier<GlyphType> SEVER = register(Identifiers.SEVER, () -> Items.IRON_SWORD, 0xc30a0a);
    RegistrySupplier<GlyphType> SUSTAIN = register(Identifiers.SUSTAIN, () -> Items.SHIELD, 0x8d9589);

    @ApiStatus.Internal
    private static RegistrySupplier<GlyphType> register(final @NotNull ResourceLocation id, final @NotNull Supplier<Item> intent, final int tint) {
        return GLYPH_TYPE_REGISTRY.register(id.getPath(), () -> new GlyphType(id, intent, tint));
    }

    static void register() {
        GLYPH_TYPE_REGISTRY.register();
    }

}
