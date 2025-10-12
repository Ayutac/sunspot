package studio.abos.mc.sunspot.fabric;

import net.fabricmc.api.ModInitializer;
import studio.abos.mc.sunspot.Sunspot;

public class SunspotFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Sunspot.init();
    }
}
