package net.architecturymod.fabric;

import net.architecturymod.ArchitecturyMod;
import net.fabricmc.api.ModInitializer;

public class ArchitecturyModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ArchitecturyMod.init();
    }
}
