package dev.jetclient.gui;

import net.minecraft.client.gui.GuiScreen;

public abstract class HotkeyScreen extends GuiScreen {
    private final int keyCode;

    public HotkeyScreen(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
