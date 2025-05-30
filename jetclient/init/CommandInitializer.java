package dev.jetclient.init;

import dev.jetclient.command.Command;
import dev.jetclient.commands.ChangeKeybind;
import dev.jetclient.commands.RemoveKeybinds;
import dev.jetclient.commands.ShowCommands;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.MessagePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CommandInitializer {
    public static List<Command> createCommands(ModuleManager moduleManager, MessagePrinter messagePrinter) {
        List<Command> commands = new ArrayList<>();
        commands.add(new ChangeKeybind(moduleManager, messagePrinter));
        commands.add(new RemoveKeybinds(moduleManager, messagePrinter));
        /* Add more commands here */

        Supplier<List<Command>> commandSupplier = () -> commands;
        commands.add(new ShowCommands(moduleManager, messagePrinter, commandSupplier));

        return commands;
    }
}
