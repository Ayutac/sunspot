package studio.abos.mc.sunspot.neoforge.common.event;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import studio.abos.mc.sunspot.common.event.SPServerEvents;

public final class ServerEvents {

    private ServerEvents() {
        /* No instantiation */
    }

    public static void onPlayerJump(final LivingEvent.LivingJumpEvent jumpEvent) {
        if (jumpEvent.getEntity() instanceof final ServerPlayer player) {
            SPServerEvents.sendPlayerUpOnOffsetBlock(player, player.blockPosition().below());
        }
    }

}
