package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.block.SubstrateBlock;

public interface SPBlockRegistry {

    DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK);

    RegistrySupplier<Block> SUBSTRATE_3 = BLOCK_REGISTRY.register(Sunspot.id("substrate_3"), () -> new SubstrateBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> SUBSTRATE_4 = BLOCK_REGISTRY.register(Sunspot.id("substrate_4"), () -> new SubstrateBlock(BlockBehaviour.Properties.of()));

}
