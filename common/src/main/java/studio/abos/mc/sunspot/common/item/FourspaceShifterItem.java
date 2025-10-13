package studio.abos.mc.sunspot.common.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.component.player.CommonFourspaceShifterOriginComponent;
import studio.abos.mc.sunspot.common.registry.SPDimensionRegistry;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

import java.util.EnumSet;

public class FourspaceShifterItem extends Item {

    public FourspaceShifterItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        if (!level.isClientSide()) {
            final CommonFourspaceShifterOriginComponent originComponent = SPComponentPlatformUtils.getFourspaceShifterOriginData(player);
            if (originComponent == null) {
                Sunspot.LOGGER.error("No origin component detected, no shift will take place!");
            }
            else {
                ResourceKey<Level> origin = originComponent.getOrigin();
                if ((origin == null || origin == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY) && level.dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY) {
                    Sunspot.LOGGER.warn("In Fourspace without proper origin, overworld will be assumed!");
                    origin = Level.OVERWORLD;
                }
                if (level.dimension() == SPDimensionRegistry.FOURSPACE_DIMENSION_KEY) {
                    final ServerLevel destination = player.getServer().getLevel(origin);
                    if (destination == null) {
                        Sunspot.LOGGER.warn("Original dimension {} vanished! Next attempt will bring you back to overworld!", origin);
                        originComponent.setOrigin(Level.OVERWORLD);
                    }
                    else {
                        // teleport back
                        player.teleportTo(destination, player.position().x(), player.position().y(), player.position().z(), EnumSet.noneOf(RelativeMovement.class), player.getYRot(), player.getXRot());
                        originComponent.setOrigin(null);
                    }
                }
                else {
                    final ServerLevel destination = player.getServer().getLevel(SPDimensionRegistry.FOURSPACE_DIMENSION_KEY);
                    if (destination == null) {
                        Sunspot.LOGGER.warn("Fourspace is missing?! No teleport will take place!");
                    }
                    else {
                        // teleport into Fourspace
                        player.teleportTo(destination, player.position().x(), player.position().y(), player.position().z(), EnumSet.noneOf(RelativeMovement.class), player.getYRot(), player.getXRot());
                        originComponent.setOrigin(level.dimension());
                    }
                }
            }
        }
        player.getCooldowns().addCooldown(this, 200);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
