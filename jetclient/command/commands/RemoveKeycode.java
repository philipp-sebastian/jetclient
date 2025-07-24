package dev.jetclient.command.commands;

import dev.jetclient.command.Command;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import org.lwjgl.input.Keyboard;

public class RemoveKeycode extends Command {

    public RemoveKeycode(ModuleManager moduleManager) {
        super(moduleManager, "Remove keycode", "removekeycode", "removekeycode <module>");
    }

    @Override
    public void executeCommand(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        String moduleName = args[1];
        for (Module module : moduleManager.getModules()) {
            if (module.getName().equalsIgnoreCase(moduleName)) {
               module.setKeyCode(Keyboard.KEY_NONE);
               printMessage("Removed keycode from module: " + module.getName());
               return;
            }
        }

        printMessage("Invalid module");
    }
}
