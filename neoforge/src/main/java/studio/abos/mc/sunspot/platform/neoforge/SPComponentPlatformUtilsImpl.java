package studio.abos.mc.sunspot.platform.neoforge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;
import studio.abos.mc.sunspot.neoforge.common.capability.SPCapabilities;

public class SPComponentPlatformUtilsImpl {

    public static CommonFourspaceShifterOriginComponent getFourspaceShifterOriginData(final Player player) {
        return player.getData(SPCapabilities.FOURSPACE_SHIFTER_ORIGIN);
    }

    public static CommonFlameComponent getFlameData(final Entity entity) {
        return entity.getData(SPCapabilities.FLAME);
    }

}
