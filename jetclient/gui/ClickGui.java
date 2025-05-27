package dev.jetclient.gui;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
