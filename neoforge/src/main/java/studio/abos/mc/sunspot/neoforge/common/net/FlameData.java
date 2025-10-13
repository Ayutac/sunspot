package studio.abos.mc.sunspot.neoforge.common.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;

public record FlameData(boolean flametouched, int flame) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FlameData> TYPE = new CustomPacketPayload.Type<>(Sunspot.id("fl"));

    public FlameData {
        if (flame < 0) {
            throw new IllegalArgumentException("Flame amount cannot be negative!");
        }
        if (flame > CommonFlameComponent.FLAME_MAX) {
            throw new IllegalArgumentException("Flame amount cannot be that big!");
        }
    }

    public static final StreamCodec<ByteBuf, FlameData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            FlameData::flametouched,
            ByteBufCodecs.VAR_INT,
            FlameData::flame,
            FlameData::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
