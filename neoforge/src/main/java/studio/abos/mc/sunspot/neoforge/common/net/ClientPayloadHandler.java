package studio.abos.mc.sunspot.neoforge.common.net;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.abos.mc.sunspot.common.component.player.CommonFlameComponent;
import studio.abos.mc.sunspot.platform.neoforge.SPComponentPlatformUtilsImpl;

public class ClientPayloadHandler {

    public static void handleFlameDataOnMain(final FlameData data, final IPayloadContext context) {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        final CommonFlameComponent flame = SPComponentPlatformUtilsImpl.getFlameData(player);
        flame.setFlametouched(data.flametouched());
        flame.setFlame(Mth.clamp(data.flame(), 0, CommonFlameComponent.FLAME_MAX));
    }

}
