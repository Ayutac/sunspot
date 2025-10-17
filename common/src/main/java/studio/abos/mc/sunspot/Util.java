package studio.abos.mc.sunspot;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public final class Util {

    private Util() {
        /* No instantiation */
    }

    public static boolean isOfDamageType(final @NotNull DamageSource source, final @NotNull ResourceKey<DamageType> type, final @NotNull Level level) {
        return source.type() == level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).get(type);
    }

    public static Holder<DamageType> damageTypeHolder(final @NotNull ResourceKey<DamageType> damageType, final @NotNull Level level) {
        return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType);
    }
}
