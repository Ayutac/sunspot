package studio.abos.mc.sunspot.mixin;

import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SimpleParticleType.class)
public interface SimpleParticleTypeInvoker {

    @Invoker("<init>")
    static SimpleParticleType ctor(boolean bl) {throw new UnsupportedOperationException();}

}
