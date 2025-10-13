package studio.abos.mc.sunspot.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.player.Player;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;

public class SPComponentPlatformUtils {

    @ExpectPlatform
    public static CommonFourspaceShifterOriginComponent getFourspaceShifterOriginData(Player player) {
        throw new UnsupportedOperationException("Must be called from a platform!");
    }


}
