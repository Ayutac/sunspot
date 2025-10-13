package studio.abos.mc.sunspot.neoforge.common.capability;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.neoforge.common.capability.entity.FlameCapability;
import studio.abos.mc.sunspot.neoforge.common.capability.player.FourspaceShifterOriginCapability;

import java.util.function.Supplier;

public interface SPCapabilities {

    DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Sunspot.MOD_ID);

    Supplier<AttachmentType<FourspaceShifterOriginCapability>> FOURSPACE_SHIFTER_ORIGIN = ATTACHMENT_TYPES.register(
            "fourspace_shifter_origin", () -> AttachmentType.serializable(FourspaceShifterOriginCapability::new).build()
    );
    Supplier<AttachmentType<FlameCapability>> FLAME = ATTACHMENT_TYPES.register(
            "flame", () -> AttachmentType.serializable(FlameCapability::new).copyOnDeath().build()
    );
}
