package studio.abos.mc.sunspot.fabric.client.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import studio.abos.mc.sunspot.common.blockentity.OffsetBlockEntity;
import studio.abos.mc.sunspot.fabric.common.net.JumpData;

public final class ClientEvents {

    private ClientEvents() {
        /* No instantiation */
    }

    public static void jumpOnOffsetBlock() {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        final BlockEntity blockEntity = player.level().getBlockEntity(player.getOnPos().below());
        if (blockEntity instanceof OffsetBlockEntity) {
            ClientPlayNetworking.send(new JumpData(blockEntity.getBlockPos()));
        }
    }

}
