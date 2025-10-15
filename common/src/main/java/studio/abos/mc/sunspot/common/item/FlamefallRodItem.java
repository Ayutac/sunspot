package studio.abos.mc.sunspot.common.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.event.SPServerEvents;

public class FlamefallRodItem extends Item {

    public FlamefallRodItem(final Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand interactionHand) {
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        player.getCooldowns().addCooldown(this, 600);
        if (!level.isClientSide) {
            SPServerEvents.summonFlamefall((ServerPlayer)player);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, player);
        return super.use(level, player, interactionHand);
    }
}
