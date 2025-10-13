package studio.abos.mc.sunspot.common.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;

public final class SPServerEvents {

    private SPServerEvents() {
        /* No instantiation */
    }

    public static void serverPostTick(final MinecraftServer server) {
        // hurt ignited entities
        final var damageTypes = server.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
        for (final ServerLevel level : server.getAllLevels()) {
            for (final Entity entity : level.getAllEntities()) {
                CommonFlamefallFireComponent.serverTick(entity, damageTypes);
            }
        }
    }

}
