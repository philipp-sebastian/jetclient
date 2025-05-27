package dev.jetclient.keybinds;

import dev.jetclient.gui.ClickGui;
import dev.jetclient.gui.GuiScreenManager;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class KeybindHandler {
    private ModuleManager moduleManager;
    private GuiScreenManager guiScreenManager;

    public KeybindHandler(ModuleManager moduleManager, GuiScreenManager guiScreenManager) {
        this.moduleManager = moduleManager;
        this.guiScreenManager = guiScreenManager;
    }

    public void onKeyPressed(int keyCode) {
        guiScreenManager.handleKeyEvent(keyCode);

        for (Module module : moduleManager.getAllModules()) {
            if (module.getKeyBind() != keyCode) continue;
            moduleManager.toggleModule(module);
            break;
        }
    }
}
