package studio.abos.mc.sunspot.platform.fabric;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.common.component.entity.CommonFlamefallFireComponent;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;
import studio.abos.mc.sunspot.fabric.common.component.SPComponents;

public class SPComponentPlatformUtilsImpl {

    public static @Nullable CommonFourspaceShifterOriginComponent getFourspaceShifterOriginData(final Player player) {
        return SPComponents.FOURSPACE_SHIFTER_ORIGIN.maybeGet(player).orElse(null);
    }

    public static @Nullable CommonFlameComponent getFlameData(final Entity entity) {
        return SPComponents.FLAME.maybeGet(entity).orElse(null);
    }

    public static @Nullable CommonFlamefallFireComponent getFlamefallFireData(final Entity entity) {
        return SPComponents.FLAMEFALL_FIRE.maybeGet(entity).orElse(null);
    }

}
