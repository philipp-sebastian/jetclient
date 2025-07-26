package dev.jetclient.handler;

import dev.jetclient.JetClient;
import dev.jetclient.gui.modulegui.ModuleGui;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;

public class KeyHandler {
    private final ModuleManager moduleManager;

    public KeyHandler(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void onKeyPressed(int keyCode) {
        if (Minecraft.getMinecraft().currentScreen != null) {
            return;
        }

        /* GuiScreens have higher priority */
        switch (keyCode) {
            case ModuleGui.HOT_KEY:
                Minecraft.getMinecraft().displayGuiScreen(new ModuleGui(JetClient.getPanelManager()));
                break;
            default:
                moduleManager.handleKeyEvent(keyCode);
        }
    }
}
