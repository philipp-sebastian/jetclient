package dev.jetclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class GuiScreenManager {
    private final List<GuiScreen> guiScreens;

    public GuiScreenManager(List<GuiScreen> guiScreens) {
        this.guiScreens = guiScreens;
    }

    public void handleKeyEvent(int keyCode) {
        for (GuiScreen guiScreen : guiScreens) {
            if (guiScreen instanceof KeyEventGuiScreen
                    && ((KeyEventGuiScreen) guiScreen).getKeyCode() == keyCode
                    && Minecraft.getMinecraft().currentScreen == null) {
                Minecraft.getMinecraft().displayGuiScreen(guiScreen);
                return;
            }
        }
    }

    public void displayAltManagerScreen() {
        //implement
    }
}
