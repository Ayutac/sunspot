package studio.abos.mc.sunspot.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;

public class SPComponentPlatformUtils {

    public static final String FROM_A_PLATFORM = "Must be called from a platform!";

    @ExpectPlatform
    public static @Nullable CommonFourspaceShifterOriginComponent getFourspaceShifterOriginData(final Player player) {
        throw new UnsupportedOperationException(FROM_A_PLATFORM);
    }

    @ExpectPlatform
    public static @Nullable CommonFlameComponent getFlameData(final Entity entity) {
        throw new UnsupportedOperationException(FROM_A_PLATFORM);
    }

    @ExpectPlatform
    public static @Nullable CommonFlamefallFireComponent getFlamefallFireData(final Entity entity) {
        throw new UnsupportedOperationException(FROM_A_PLATFORM);
    }

}
