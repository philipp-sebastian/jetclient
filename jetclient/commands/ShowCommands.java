package dev.jetclient.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.MessagePrinter;

import java.util.List;
import java.util.function.Supplier;

public class ShowCommands extends Command {
    private final Supplier<List<Command>> commandSupplier;

    public ShowCommands(ModuleManager moduleManager, MessagePrinter messagePrinter, Supplier<List<Command>> commandSupplier) {
        super(moduleManager, messagePrinter, "Show commands", "commands", "commands");

        this.commandSupplier = commandSupplier;
    }

    @Override
    public void executeCommand(String[] args) {
        for (Command c : commandSupplier.get()) {
            this.printMessage(c.getCommandName() + ": " + c.getUsage());
        }
    }
}
