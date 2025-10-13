package studio.abos.mc.sunspot.common.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FourspaceShifterItem extends Item {

    public FourspaceShifterItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        player.getCooldowns().addCooldown(this, 200);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!level.isClientSide()) {

        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
