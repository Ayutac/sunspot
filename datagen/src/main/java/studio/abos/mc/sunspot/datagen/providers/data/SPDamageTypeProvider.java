package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPDamageTypeRegistry;

import java.util.concurrent.CompletableFuture;

public class SPDamageTypeProvider extends FabricDynamicRegistryProvider {

    public SPDamageTypeProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void bootstrap(final BootstrapContext<DamageType> bootstrapContext) {
        bootstrapContext.register(SPDamageTypeRegistry.FLAMEFALL_FIRE, new DamageType("flamefall", 0.1F, DamageEffects.BURNING));
    }

    @Override
    protected void configure(final HolderLookup.Provider registries, final Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));
    }

    @Override
    public @NotNull String getName() {
        return "Damage Type Provider";
    }
}
