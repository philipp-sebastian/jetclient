package dev.jetclient.gui;

import dev.jetclient.gui.screens.AltLogin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class GuiScreenManager {
    private final List<GuiScreen> guiScreens;

    public GuiScreenManager(List<GuiScreen> guiScreens) {
        this.guiScreens = guiScreens;
    }

    public boolean handleKeyEvent(int keyCode) {
        if (Minecraft.getMinecraft().currentScreen != null) return false;

        for (GuiScreen guiScreen : guiScreens) {
            if (guiScreen instanceof HotkeyScreen && ((HotkeyScreen) guiScreen).getKeyCode() == keyCode) {
                Minecraft.getMinecraft().displayGuiScreen(guiScreen);
                return true;
            }
        }
        return false;
    }

    public void displayAltLogin() {
        if (Minecraft.getMinecraft().currentScreen != null) return;

        for (GuiScreen guiScreen : guiScreens) {
            if (guiScreen instanceof AltLogin) {
                Minecraft.getMinecraft().displayGuiScreen(guiScreen);
                return;
            }
        }
    }
}
