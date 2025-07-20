package dev.jetclient.command.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.MessagePrinter;
import org.lwjgl.input.Keyboard;

public class ChangeKeybind extends Command {

    public ChangeKeybind(ModuleManager moduleManager, MessagePrinter messagePrinter) {
        super(moduleManager, messagePrinter, "Change keybind", "bind", "bind <modulename> <key>");
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 3) {
            this.printUsage();
            return;
        }

        String moduleName = args[1];
        String keyName = args[2].toUpperCase();
        int keyCode = Keyboard.getKeyIndex(keyName);

        if (keyCode == Keyboard.KEY_NONE) {
            this.printMessage("Invalid key");
            return;
        }

        Module module = moduleManager.getModule(moduleName);
        if (module == null) {
            this.printMessage("Invalid module");
            return;
        }

        moduleManager.setKeyBind(module, keyCode);
        this.printMessage("Module: " + module.getName() + " bound to key: " + Keyboard.getKeyName(module.getKeyBind()));
    }
}
