package studio.abos.mc.sunspot.fabric.client.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface ClientJumpEvent {
    Event<ClientJumpEvent> EVENT = EventFactory.createArrayBacked(ClientJumpEvent.class, (listeners) -> () -> {
        for (ClientJumpEvent callback : listeners) {
            callback.jump();
        }
    });

    void jump();
}
