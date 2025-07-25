package dev.jetclient.gui.modulegui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class ModuleGui extends GuiScreen {
    PanelManager panelManager;

    public static final int HOT_KEY = Keyboard.KEY_RSHIFT;

    public ModuleGui(PanelManager panelManager) {
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
