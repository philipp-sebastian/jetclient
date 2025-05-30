package dev.jetclient.gui.screens.modulegui;

import dev.jetclient.gui.HotkeyScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class ModuleGui extends HotkeyScreen {
    PanelManager panelManager;

    public ModuleGui(PanelManager panelManager) {
        super(Keyboard.KEY_RSHIFT);

        this.panelManager = panelManager;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        panelManager.drawPanels(mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        panelManager.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        panelManager.mouseReleased(mouseX, mouseY, state);
    }
}
