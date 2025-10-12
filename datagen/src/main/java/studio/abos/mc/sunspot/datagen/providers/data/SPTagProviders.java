package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import studio.abos.mc.sunspot.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPTagProviders {

    public static class JItemTags extends FabricTagProvider.ItemTagProvider {

        public JItemTags(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            getOrCreateTagBuilder(SPTagRegistry.SUBSTRATE_MATERIAL).add(Items.BONE_BLOCK);
        }
    }
}
