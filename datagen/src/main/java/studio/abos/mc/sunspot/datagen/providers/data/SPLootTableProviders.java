package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;

import java.util.concurrent.CompletableFuture;

public class SPLootTableProviders {

    public static class BlockLoot extends FabricBlockLootTableProvider {

        public BlockLoot(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(output, registryLookup);
        }

        @Override
        public void generate() {
            dropSelf(SPBlockRegistry.SUBSTRATE_3.get());
            dropSelf(SPBlockRegistry.SUBSTRATE_4.get());
            dropSelf(SPBlockRegistry.ASH_RESIDUE.get());
            dropSelf(SPBlockRegistry.LM_WORKBENCH.get());
            dropSelf(SPBlockRegistry.LM_BATTERY.get());
            dropSelf(SPBlockRegistry.LM_BATTERY_CREATIVE.get());
            dropSelf(SPBlockRegistry.AFFIX.get());
        }
    }
}
