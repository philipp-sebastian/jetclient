package dev.jetclient.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import org.lwjgl.input.Keyboard;

public class ChangeKeybind extends Command {

    public ChangeKeybind(ModuleManager moduleManager, String commandName, String command, String usage) {
        super(moduleManager, commandName, command, usage);
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
            this.printString("Invalid key");
            return;
        }

        Module m = moduleManager.getModuleByName(moduleName);
        if (m == null) {
            this.printString("Invalid module");
            return;
        }

        m.setKeyBind(keyCode);
        this.printString("Module: " + m.getName() + " bound to key: " + Keyboard.getKeyName(m.getKeyBind()));
    }
}
