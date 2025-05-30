package dev.jetclient.command;

import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.MessagePrinter;

public abstract class Command {
    protected final ModuleManager moduleManager;
    protected final MessagePrinter messagePrinter;
    private final String commandName;
    private final String command;
    private final String usage;

    protected Command(ModuleManager moduleManager, MessagePrinter messagePrinter, String commandName, String command, String usage) {
        this.moduleManager = moduleManager;
        this.messagePrinter = messagePrinter;
        this.commandName = commandName;
        this.command = command;
        this.usage = usage;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getCommand() {
        return this.command;
    }

    public String getUsage() {
        return "#" + this.usage;
    }

    protected void printUsage() {
        messagePrinter.printMessage(this.usage);
    }

    protected void printMessage(String msg) {
        messagePrinter.printMessage(msg);
    }

    public abstract void executeCommand(String[] args);
}
