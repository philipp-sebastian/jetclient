package dev.jetclient.init;

import dev.jetclient.command.Command;
import dev.jetclient.commands.ChangeKeybind;
import dev.jetclient.commands.RemoveKeybinds;
import dev.jetclient.commands.ShowCommands;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CommandInitializer {
    public static List<Command> createCommands(ModuleManager moduleManager) {
        List<Command> commands = new ArrayList<>();

        Supplier<List<Command>> commandSupplier = () -> commands;

        commands.add(new ChangeKeybind(moduleManager, "Change keybind", "bind",  "bind <modulename> <key>"));
        commands.add(new ShowCommands(moduleManager, commandSupplier, "Show commands", "commands", "commands"));
        commands.add(new RemoveKeybinds(moduleManager, "Remove all keybinds", "removekeybinds", "removekeybinds"));

        return commands;
    }
}
