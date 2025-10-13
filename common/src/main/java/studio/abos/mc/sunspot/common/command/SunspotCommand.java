package studio.abos.mc.sunspot.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.sunspot.common.component.entity.CommonFlameComponent;
import studio.abos.mc.sunspot.platform.SPComponentPlatformUtils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class SunspotCommand {
    public static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("sunspot")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("flame")
                                .then(Commands.argument("entities", EntityArgument.entities())
                                        .then(Commands.literal("bestow")
                                                .executes(SunspotCommand::runBestow)
                                        )
                                        .then(Commands.literal("revoke")
                                                .executes(SunspotCommand::runRevoke)
                                        )
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("amount", IntegerArgumentType.integer(0, CommonFlameComponent.FLAME_MAX))
                                                        .executes(SunspotCommand::runSet)
                                                )
                                        )
                                        .then(Commands.literal("measure")
                                                .executes(SunspotCommand::runMeasure)
                                        )
                                )
                        )
        );
    }

    public static int run(final CommandContext<CommandSourceStack> ctx, final @NotNull Predicate<Mob> mobAction, final @NotNull Predicate<ServerPlayer> playerAction, final @Nullable Function<Integer, Component> result) {
        try {
            final Collection<? extends Entity> targets = EntityArgument.getEntities(ctx, "entities");
            int count = 0;
            for (final Entity entity : targets) {
                if (entity instanceof Mob mob && mobAction.test(mob)) {
                    count++;
                }
                else if (entity instanceof ServerPlayer player && playerAction.test(player)) {
                    count++;
                }
            }
            if (result != null && ctx.getSource().isPlayer()) {
                ctx.getSource().getPlayerOrException().sendSystemMessage(result.apply(count));
            }
            return 1;
        } catch (final Exception e) {
            return 0;
        }
    }

    public static int runBestow(final CommandContext<CommandSourceStack> ctx) {
        return run(ctx, mob -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(mob);
                    if (flame.isFlametouched()) {
                        return false;
                    }
                    flame.setFlametouched(true);
                    return true;
                },
                player -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(player);
                    if (flame.isFlametouched()) {
                        return false;
                    }
                    flame.setFlametouched(true, player);
                    return true;
                },
                count -> {
                    if (count == 1) {
                        return Component.literal("Bestowed Flame upon 1 entity");
                    }
                    return Component.literal("Bestowed Flame upon %d entities".formatted(count));
                });
    }

    public static int runRevoke(final CommandContext<CommandSourceStack> ctx) {
        return run(ctx, mob -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(mob);
                    if (!flame.isFlametouched()) {
                        return false;
                    }
                    flame.setFlametouched(false);
                    return true;
                },
                player -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(player);
                    if (!flame.isFlametouched()) {
                        return false;
                    }
                    flame.setFlametouched(false, player);
                    return true;
                },
                count -> {
                    if (count == 1) {
                        return Component.literal("Revoked Flame from 1 entity");
                    }
                    return Component.literal("Revoked Flame from %d entities".formatted(count));
                });
    }

    public static int runSet(final CommandContext<CommandSourceStack> ctx) {
        // this clamp is unneeded because the command already takes care of it
        final int amount = CommonFlameComponent.clamp(ctx.getArgument("amount", Integer.class));
        return run(ctx, mob -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(mob);
                    if (flame.getFlame() == amount) {
                        return false;
                    }
                    flame.setFlame(amount);
                    return true;
                },
                player -> {
                    final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(player);
                    if (flame.getFlame() == amount) {
                        return false;
                    }
                    flame.setFlame(amount, player);
                    return true;
                },
                count -> {
                    if (count == 1) {
                        return Component.literal("Set Flame to %d mVn for 1 entity".formatted(amount));
                    }
                    return Component.literal("Set Flame to %d mVn for %d entities".formatted(amount, count));
                });
    }

    public static int runMeasure(final CommandContext<CommandSourceStack> ctx) {
        final int[] amount = new int[1];
        final Predicate<Entity> measure = entity -> {
            final CommonFlameComponent flame = SPComponentPlatformUtils.getFlameData(entity);
            if (!flame.isFlametouched()) {
                return false;
            }
            amount[0] += flame.getFlame();
            return true;
        };
        return run(ctx, measure::test, measure::test,
                count -> {
                    if (count == 1) {
                        return Component.literal("Measured Flame of 1 entity: %d mVn".formatted(amount[0]));
                    }
                    return Component.literal("Measured Flame of %d entities: %d mVn".formatted(count, amount[0]));
                });
    }
}
