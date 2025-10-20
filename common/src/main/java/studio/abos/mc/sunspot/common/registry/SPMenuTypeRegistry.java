package studio.abos.mc.sunspot.common.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import studio.abos.mc.sunspot.Identifiers;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.inventory.SeverMenu;
import studio.abos.mc.sunspot.common.inventory.WorkbenchMenu;

public interface SPMenuTypeRegistry {

    DeferredRegister<MenuType<?>> MENU_TYPE_REGISTRY = DeferredRegister.create(Sunspot.MOD_ID, Registries.MENU);

    RegistrySupplier<MenuType<WorkbenchMenu>> WORKBENCH = MENU_TYPE_REGISTRY.register(Identifiers.WORKBENCH, () -> new MenuType<>(WorkbenchMenu::new, FeatureFlags.DEFAULT_FLAGS));
    RegistrySupplier<MenuType<SeverMenu>> SEVER = MENU_TYPE_REGISTRY.register(Identifiers.SEVER, () -> new MenuType<>(SeverMenu::new, FeatureFlags.DEFAULT_FLAGS));

    static void register() {
        MENU_TYPE_REGISTRY.register();
    }

}
