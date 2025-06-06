package dev.jetclient.gui.screens.modulegui;

import java.util.List;

public class PanelManager {
    private final List<Panel> panels;

    public PanelManager(List<Panel> panels) {
        this.panels = panels;
    }

    public void drawPanels(int mouseX, int mouseY) {
        for (Panel p : panels) {
            p.drawScreen(mouseX, mouseY);
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Panel p : panels) {
            p.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        for (Panel p : panels) {
            p.mouseReleased(mouseX, mouseY, state);
        }
    }
}
