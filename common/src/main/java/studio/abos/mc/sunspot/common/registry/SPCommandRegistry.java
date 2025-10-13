package studio.abos.mc.sunspot.common.registry;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import studio.abos.mc.sunspot.common.command.SunspotCommand;

public interface SPCommandRegistry {

    static void register(final CommandDispatcher<CommandSourceStack> dispatcher, final CommandBuildContext registryAccess, final Commands.CommandSelection environment) {
        SunspotCommand.register(dispatcher);
    }

}
