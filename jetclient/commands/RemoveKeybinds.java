package dev.jetclient.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import org.lwjgl.input.Keyboard;

public class RemoveKeybinds extends Command {
    public RemoveKeybinds(ModuleManager moduleManager, String commandName, String command, String usage) {
        super(moduleManager, commandName, command, usage);
    }

    @Override
    public void executeCommand(String[] args) {
        for (Module m : moduleManager.getModules()) {
            m.setKeyBind(Keyboard.KEY_NONE);
        }

        printString("Successfully removed all keybinds");
    }
}
