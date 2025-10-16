package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.block.LatticeManifestBlock;
import studio.abos.mc.sunspot.common.block.SmallSubstrateBlock;

public interface SPBlockRegistry {

    DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK);

    RegistrySupplier<Block> SUBSTRATE_3 = BLOCK_REGISTRY.register(Sunspot.id("substrate_3"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> SUBSTRATE_4 = BLOCK_REGISTRY.register(Sunspot.id("substrate_4"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));

    RegistrySupplier<Block> LM_BATTERY = BLOCK_REGISTRY.register(Sunspot.id("lm_battery"), () -> new LatticeManifestBlock(BlockBehaviour.Properties.of()));

    static void register() {
        BLOCK_REGISTRY.register();
    }

}
