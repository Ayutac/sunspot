package studio.abos.mc.sunspot.fabric.common.component;

import net.minecraft.world.entity.Mob;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.fabric.common.component.entity.FlameComponent;
import studio.abos.mc.sunspot.fabric.common.component.player.FourspaceShifterOriginComponent;

public class SPComponents implements EntityComponentInitializer {

    public static final ComponentKey<FourspaceShifterOriginComponent> FOURSPACE_SHIFTER_ORIGIN =
            ComponentRegistry.getOrCreate(Sunspot.id("fourspace_shifter_origin"), FourspaceShifterOriginComponent.class);
    public static final ComponentKey<FlameComponent> FLAME =
            ComponentRegistry.getOrCreate(Sunspot.id("flame"), FlameComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(FOURSPACE_SHIFTER_ORIGIN, FourspaceShifterOriginComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(FLAME, FlameComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerFor(Mob.class, FLAME, FlameComponent::new);
    }
}
