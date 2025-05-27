package dev.jetclient.command;

import dev.jetclient.commands.ChangeKeybind;
import dev.jetclient.commands.ShowCommands;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CommandManager {
    private final ArrayList<Command> commands = new ArrayList<>();
    private final String prefix = "#";

    public CommandManager(ModuleManager moduleManager) {
        Supplier<List<Command>> commandSupplier = () -> commands;

        commands.add(new ChangeKeybind(moduleManager, "Change keybind", "bind",  "bind <modulename> <key>"));
        commands.add(new ShowCommands(moduleManager, commandSupplier, "Show commands", "commands", "commands"));
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void decodeCommand(String command) {
        String[] commandParts = command.split(" ");
        for (Command c : commands) {
            if (c.getCommand().equals(commandParts[0])) {
                c.executeCommand(commandParts);
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
