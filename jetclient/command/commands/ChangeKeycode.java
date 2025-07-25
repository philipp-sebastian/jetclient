package dev.jetclient.command.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import org.lwjgl.input.Keyboard;

public class ChangeKeycode extends Command {

    public ChangeKeycode(ModuleManager moduleManager) {
        super(moduleManager, "Change keycode", "bind", "bind <modulename> <key>");
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 3) {
            this.printUsage();
            return;
        }

        int keyCode = Keyboard.getKeyIndex(args[2].toUpperCase());
        if (keyCode == Keyboard.KEY_NONE) {
            this.printMessage("Invalid key");
            return;
        }

        Module module = moduleManager.getModule(args[1]);
        if (module != null) {
            moduleManager.setKeyCode(module, keyCode);
            this.printMessage("Module: " + module.getName() + " bound to key: " + Keyboard.getKeyName(module.getKeyCode()));
        }
        this.printMessage("Invalid module");
    }
}
