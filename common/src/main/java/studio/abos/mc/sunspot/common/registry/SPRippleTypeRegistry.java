package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.RippleType;

public interface SPRippleTypeRegistry {

    DeferredRegister<RippleType> RIPPLE_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, SPRegistries.RIPPLE_TYPE_REGISTRY_KEY);

    // TODO: correct tints
    RegistrySupplier<RippleType> BLUE = register(Identifiers.BLUE, 0x2b6f3a);
    RegistrySupplier<RippleType> GREEN = register(Identifiers.GREEN, 0x551b1b);
    RegistrySupplier<RippleType> PINK = register(Identifiers.PINK, 0x1412ae);
    RegistrySupplier<RippleType> ORANGE = register(Identifiers.ORANGE, 0x65b7db);
    RegistrySupplier<RippleType> RED = register(Identifiers.RED, 0xd8f2f1);
    RegistrySupplier<RippleType> SILVER = register(Identifiers.SILVER, 0x58e125);
    RegistrySupplier<RippleType> WHITE = register(Identifiers.WHITE, 0xc30a0a);

    @ApiStatus.Internal
    private static RegistrySupplier<RippleType> register(final @NotNull ResourceLocation id, final int tint) {
        return RIPPLE_TYPE_REGISTRY.register(id.getPath(), () -> new RippleType(id, tint));
    }

    static void register() {
        RIPPLE_TYPE_REGISTRY.register();
    }

}
