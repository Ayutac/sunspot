package net.architecturymod.neoforge;

import net.architecturymod.ArchitecturyMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ArchitecturyMod.MOD_ID)
public class ArchitecturyModNeoForge {
    public ArchitecturyModNeoForge(ModContainer container, IEventBus bus) {
        ArchitecturyMod.init();
    }
}
