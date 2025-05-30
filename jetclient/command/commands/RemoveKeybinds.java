package dev.jetclient.command.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.MessagePrinter;
import org.lwjgl.input.Keyboard;

public class RemoveKeybinds extends Command {
    public RemoveKeybinds(ModuleManager moduleManager, MessagePrinter messagePrinter) {
        super(moduleManager, messagePrinter, "Remove all keybinds", "removekeybinds", "removekeybinds");
    }

    @Override
    public void executeCommand(String[] args) {
        for (Module m : moduleManager.getModules()) {
            m.setKeyBind(Keyboard.KEY_NONE);
        }

        printMessage("Successfully removed all keybinds");
    }
}
