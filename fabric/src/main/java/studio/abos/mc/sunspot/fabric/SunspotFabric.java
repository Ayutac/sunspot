package studio.abos.mc.sunspot.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.event.SPServerEvents;
import studio.abos.mc.sunspot.fabric.common.net.JumpData;

public class SunspotFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Sunspot.init();
        PayloadTypeRegistry.playC2S().register(JumpData.TYPE, JumpData.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(JumpData.TYPE, (jumpData, context) -> SPServerEvents.sendPlayerUpOnOffsetBlock(context.player(), jumpData.pos()));
    }
}
