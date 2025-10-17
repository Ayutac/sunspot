package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.item.FlamefallRodItem;
import studio.abos.mc.sunspot.common.item.FourspaceShifterItem;

public interface SPItemRegistry {

    DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.ITEM);

    RegistrySupplier<Item> SUBSTRATE_2 = ITEM_REGISTRY.register(Sunspot.id("substrate_2"), () -> new Item(new Item.Properties()));
    RegistrySupplier<Item> SUBSTRATE_3 = ITEM_REGISTRY.register(Sunspot.id("substrate_3"), () -> new BlockItem(SPBlockRegistry.SUBSTRATE_3.get(), new Item.Properties()));
    RegistrySupplier<Item> SUBSTRATE_4 = ITEM_REGISTRY.register(Sunspot.id("substrate_4"), () -> new BlockItem(SPBlockRegistry.SUBSTRATE_4.get(), new Item.Properties()));
    RegistrySupplier<Item> ASH_RESIDUE = ITEM_REGISTRY.register(Sunspot.id("ash_residue"), () -> new Item(new Item.Properties().fireResistant()));
    RegistrySupplier<Item> ASH_RESIDUE_BLOCK = ITEM_REGISTRY.register(Sunspot.id("ash_residue_block"), () -> new BlockItem(SPBlockRegistry.ASH_RESIDUE.get(), new Item.Properties().fireResistant()));

    RegistrySupplier<Item> LM_WORKBENCH = ITEM_REGISTRY.register(Sunspot.id("lm_workbench"), () -> new BlockItem(SPBlockRegistry.LM_WORKBENCH.get(), new Item.Properties()));
    RegistrySupplier<Item> LM_BATTERY = ITEM_REGISTRY.register(Sunspot.id("lm_battery"), () -> new BlockItem(SPBlockRegistry.LM_BATTERY.get(), new Item.Properties()));
    RegistrySupplier<Item> LM_BATTERY_CREATIVE = ITEM_REGISTRY.register(Sunspot.id("lm_battery_creative"), () -> new BlockItem(SPBlockRegistry.LM_BATTERY_CREATIVE.get(), new Item.Properties()));
    RegistrySupplier<Item> AFFIX_BLOCK = ITEM_REGISTRY.register(Sunspot.id("affix_block"), () -> new BlockItem(SPBlockRegistry.AFFIX.get(), new Item.Properties()));
    RegistrySupplier<Item> ASH_BLOCK = ITEM_REGISTRY.register(Sunspot.id("ash_block"), () -> new BlockItem(SPBlockRegistry.ASH.get(), new Item.Properties()));

    RegistrySupplier<Item> MANTLE_BASE_HELMET = ITEM_REGISTRY.register(Sunspot.id("mantle_base_helmet"), () -> new Item(new Item.Properties()));
    RegistrySupplier<Item> MANTLE_BASE_CHESTPLATE = ITEM_REGISTRY.register(Sunspot.id("mantle_base_chestplate"), () -> new Item(new Item.Properties()));
    RegistrySupplier<Item> MANTLE_BASE_LEGGINGS = ITEM_REGISTRY.register(Sunspot.id("mantle_base_leggings"), () -> new Item(new Item.Properties()));
    RegistrySupplier<Item> MANTLE_BASE_BOOTS = ITEM_REGISTRY.register(Sunspot.id("mantle_base_boots"), () -> new Item(new Item.Properties()));

    RegistrySupplier<Item> FOURSPACE_SHIFTER = ITEM_REGISTRY.register(Sunspot.id("fourspace_shifter"), () -> new FourspaceShifterItem(new Item.Properties()));

    RegistrySupplier<Item> FLAMEFALL_ROD = ITEM_REGISTRY.register(Sunspot.id("flamefall_rod"), () -> new FlamefallRodItem(new Item.Properties()));

    static void register() {
        ITEM_REGISTRY.register();
    }

}
