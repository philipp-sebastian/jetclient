package dev.jetclient.commands;

import dev.jetclient.command.Command;
import dev.jetclient.command.CommandManager;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ShowCommands extends Command {
    private final Supplier<List<Command>> commandSupplier;

    public ShowCommands(ModuleManager moduleManager, Supplier<List<Command>> commandSupplier, String commandName, String command, String usage) {
        super(moduleManager, commandName, command, usage);

        this.commandSupplier = commandSupplier;
    }

    @Override
    public void executeCommand(String[] args) {
        for (Command c : commandSupplier.get()) {
            this.printString(c.getCommandName() + ": " + c.getUsage());
        }
    }
}
