package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import studio.abos.mc.sunspot.Sunspot;

public interface SPCreativeMenuTabRegistry {

    DeferredRegister<CreativeModeTab> CREATIVE_TAB_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.CREATIVE_MODE_TAB);

    String GENERAL_TAB_KEY = "itemGroup.sunspot.general";

    static void register() {
        CREATIVE_TAB_REGISTRY.register("general", SPCreativeMenuTabRegistry::createSunspotItemGroup);
        CREATIVE_TAB_REGISTRY.register();
    }

    static CreativeModeTab createSunspotItemGroup() {
        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable(GENERAL_TAB_KEY))
                .icon(() -> SPItemRegistry.SUBSTRATE_2.get().getDefaultInstance())
                .displayItems((displayContext, entries) -> {
                    entries.accept(SPItemRegistry.FLAMEFALL_ROD.get());
                    entries.accept(SPItemRegistry.SUBSTRATE_2.get());
                    entries.accept(SPItemRegistry.SUBSTRATE_3.get());
                    entries.accept(SPItemRegistry.SUBSTRATE_4.get());
                    entries.accept(SPItemRegistry.LM_BATTERY.get());
                    entries.accept(SPItemRegistry.LM_BATTERY_CREATIVE.get());
                    entries.accept(SPItemRegistry.FOURSPACE_SHIFTER.get());
                    entries.accept(SPItemRegistry.MANTLE_BASE_HELMET.get());
                    entries.accept(SPItemRegistry.MANTLE_BASE_CHESTPLATE.get());
                    entries.accept(SPItemRegistry.MANTLE_BASE_LEGGINGS.get());
                    entries.accept(SPItemRegistry.MANTLE_BASE_BOOTS.get());
                })
                .build();
    }

}
