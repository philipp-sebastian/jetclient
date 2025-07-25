package dev.jetclient.init;

import dev.jetclient.command.Command;
import dev.jetclient.command.commands.ChangeKeycode;
import dev.jetclient.command.commands.RemoveKeycode;
import dev.jetclient.command.commands.ShowCommands;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CommandInitializer {
    public static List<Command> createCommands(ModuleManager moduleManager) {
        List<Command> commands = new ArrayList<>();
        commands.add(new ChangeKeycode(moduleManager));
        commands.add(new RemoveKeycode(moduleManager));
        /* Add more commands here */

        Supplier<List<Command>> commandSupplier = () -> commands;
        commands.add(new ShowCommands(moduleManager, commandSupplier));

        return commands;
    }
}
