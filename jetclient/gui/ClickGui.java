package dev.jetclient.gui;

import java.io.IOException;

public class ClickGui extends KeyEventGuiScreen {
    PanelManager panelManager;

    public ClickGui(PanelManager panelManager, int keyCode) {
        super(keyCode);

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
