package studio.abos.mc.sunspot.platform.fabric;

import net.minecraft.world.entity.player.Player;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;
import studio.abos.mc.sunspot.fabric.common.component.SPComponents;

public class SPComponentPlatformUtilsImpl {

    public static CommonFourspaceShifterOriginComponent getFourspaceShifterOriginData(Player player) {
        return SPComponents.FOURSPACE_SHIFTER_ORIGIN.get(player);
    }

}
