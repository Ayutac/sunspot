package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPTagProviders {

    public static class SPItemTags extends FabricTagProvider.ItemTagProvider {

        public SPItemTags(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            getOrCreateTagBuilder(SPTagRegistry.SUBSTRATE_MATERIAL).add(Items.BONE_BLOCK);
        }
    }

    public static class SPEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {


        public SPEntityTypeTags(final @NotNull FabricDataOutput output, final @NotNull CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_AFFIX).add(SPEntityTypeRegistry.FLAMEFALL.getId());
        }
    }
}
