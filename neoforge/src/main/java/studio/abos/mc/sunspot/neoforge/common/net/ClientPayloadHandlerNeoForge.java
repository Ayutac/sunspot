package studio.abos.mc.sunspot.neoforge.common.net;

import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.platform.neoforge.SPComponentPlatformUtilsImpl;

public final class ClientPayloadHandlerNeoForge {

    private ClientPayloadHandlerNeoForge() {
        /* No instantiation */
    }

    public static void handleFlameDataOnMain(final FlameData data, final IPayloadContext context) {
        final CommonFlameComponent flame = SPComponentPlatformUtilsImpl.getFlameData(context.player());
        flame.setFlametouched(data.flametouched());
        flame.setFlame(CommonFlameComponent.clamp(data.flame()));
    }

    public static void handleFlamefallFireDataOnMain(final FlamefallFireData data, final IPayloadContext context) {
        final Entity entity = context.player().level().getEntity(data.entity());
        if (entity == null) {
            return;
        }
        final CommonFlamefallFireComponent fire = SPComponentPlatformUtilsImpl.getFlamefallFireData(entity);
        fire.setRemainingFireTicks(data.remainingFireTicks());
    }

}
