package studio.abos.mc.sunspot.fabric.common.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;

public record JumpData(BlockPos pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<JumpData> TYPE = new CustomPacketPayload.Type<>(Sunspot.id("jp"));

    public static final StreamCodec<ByteBuf, JumpData> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            JumpData::pos,
            JumpData::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
