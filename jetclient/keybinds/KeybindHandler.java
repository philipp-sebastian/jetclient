package dev.jetclient.keybinds;

import dev.jetclient.gui.GuiScreenManager;
import dev.jetclient.module.ModuleManager;

public class KeybindHandler {
    private final ModuleManager moduleManager;
    private final GuiScreenManager guiScreenManager;

    public KeybindHandler(ModuleManager moduleManager, GuiScreenManager guiScreenManager) {
        this.moduleManager = moduleManager;
        this.guiScreenManager = guiScreenManager;
    }

    public void onKeyPressed(int keyCode) {
        /* GuiScreens have higher priority */
        if (guiScreenManager.handleKeyEvent(keyCode)) return;

        moduleManager.handleKeyEvent(keyCode);
    }
}
