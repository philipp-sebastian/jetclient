package dev.jetclient.gui;

import net.minecraft.client.gui.GuiScreen;

public abstract class KeyEventGuiScreen extends GuiScreen {
    private final int keyCode;

    public KeyEventGuiScreen(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
