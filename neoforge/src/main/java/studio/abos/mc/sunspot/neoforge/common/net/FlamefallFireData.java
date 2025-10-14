package studio.abos.mc.sunspot.neoforge.common.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;

public record FlamefallFireData(int remainingFireTicks, int entity) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FlamefallFireData> TYPE = new CustomPacketPayload.Type<>(Sunspot.id("ft"));

    public FlamefallFireData {
        if (remainingFireTicks < 0) {
            throw new IllegalArgumentException("remainingFireTicks cannot be negative!");
        }
    }

    public static final StreamCodec<ByteBuf, FlamefallFireData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            FlamefallFireData::remainingFireTicks,
            ByteBufCodecs.VAR_INT,
            FlamefallFireData::entity,
            FlamefallFireData::new
    );

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
