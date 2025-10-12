package studio.abos.mc.sunspot.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import studio.abos.mc.sunspot.Sunspot;

public interface SPItemRegistry {

    DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.ITEM);

    RegistrySupplier<Item> SUBSTRATE_2 = ITEM_REGISTRY.register(Sunspot.id("substrate_2"), () -> new Item(new Item.Properties()));
    RegistrySupplier<Item> SUBSTRATE_3 = ITEM_REGISTRY.register(Sunspot.id("substrate_3"), () -> new BlockItem(SPBlockRegistry.SUBSTRATE_3.get(), new Item.Properties()));
    RegistrySupplier<Item> SUBSTRATE_4 = ITEM_REGISTRY.register(Sunspot.id("substrate_4"), () -> new BlockItem(SPBlockRegistry.SUBSTRATE_4.get(), new Item.Properties()));


    static void init() {
        // intentionally left empty
    }

}
