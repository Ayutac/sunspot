package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.block.AffixBlock;
import studio.abos.mc.sunspot.common.block.AshBlock;
import studio.abos.mc.sunspot.common.block.LmBatteryBlock;
import studio.abos.mc.sunspot.common.block.LmBatteryCreativeBlock;
import studio.abos.mc.sunspot.common.block.SmallSubstrateBlock;

public interface SPBlockRegistry {

    DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.BLOCK);

    RegistrySupplier<Block> SUBSTRATE_3 = BLOCK_REGISTRY.register(Sunspot.id("substrate_3"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> SUBSTRATE_4 = BLOCK_REGISTRY.register(Sunspot.id("substrate_4"), () -> new SmallSubstrateBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> ASH_RESIDUE = BLOCK_REGISTRY.register(Sunspot.id("ash_residue"), () -> new ColoredFallingBlock(new ColorRGBA(-8356741), BlockBehaviour.Properties.of()));

    RegistrySupplier<Block> LM_WORKBENCH = BLOCK_REGISTRY.register(Sunspot.id("lm_workbench"), () -> new Block(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> LM_BATTERY = BLOCK_REGISTRY.register(Sunspot.id("lm_battery"), () -> new LmBatteryBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> LM_BATTERY_CREATIVE = BLOCK_REGISTRY.register(Sunspot.id("lm_battery_creative"), () -> new LmBatteryCreativeBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> AFFIX = BLOCK_REGISTRY.register(Sunspot.id("affix"), () -> new AffixBlock(BlockBehaviour.Properties.of()));
    RegistrySupplier<Block> ASH = BLOCK_REGISTRY.register(Sunspot.id("ash"), () -> new AshBlock(BlockBehaviour.Properties.of()));

    static void register() {
        BLOCK_REGISTRY.register();
    }

}
