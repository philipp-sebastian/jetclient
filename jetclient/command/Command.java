package dev.jetclient.command;

import dev.jetclient.JetClient;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public abstract class Command {
    protected final ModuleManager moduleManager;
    private final String commandName;
    private final String command;
    private final String usage;

    protected Command(ModuleManager moduleManager, String commandName, String command, String usage) {
        this.moduleManager = moduleManager;
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

    public void printUsage() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(JetClient.getClientName() + " >> Usage: " + this.usage));
    }

    public void printString(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(JetClient.getClientName() + " >> " + msg));
    }
    public abstract void executeCommand(String[] args);
}
