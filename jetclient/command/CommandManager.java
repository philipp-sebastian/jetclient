package dev.jetclient.command;

import java.util.List;

public class CommandManager {
    private final List<Command> commands;
    private final String prefix = "#";

    public CommandManager(List<Command> commands) {
        this.commands = commands;
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

    public List<Command> getCommands() {
        return commands;
    }
}
