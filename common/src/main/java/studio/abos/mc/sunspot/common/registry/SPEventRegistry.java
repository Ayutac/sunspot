package studio.abos.mc.sunspot.common.registry;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.TickEvent;
import studio.abos.mc.sunspot.common.event.SPServerEvents;

public interface SPEventRegistry {

    static void register() {
        TickEvent.SERVER_POST.register(SPServerEvents::serverPostTick);
        EntityEvent.LIVING_DEATH.register(SPServerEvents::inferno);
    }

}
