package dev.jetclient.gui.modulegui;

import dev.jetclient.module.Category;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;
import java.util.List;

public class PanelManager {
    private final List<Panel> panels = new ArrayList<>();

    private static final int PADDING = 15;
    private static final int GAP = 10;

    public PanelManager(ModuleManager moduleManager) {
        buildPanels(moduleManager);
    }

    private void buildPanels(ModuleManager moduleManager) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int screenWidth = sr.getScaledWidth();

        int currentX = PADDING;
        int currentY = PADDING;

        for (Category category : Category.values()) {
            panels.add(new Panel(moduleManager, category, currentX, currentY));
            currentX += Panel.getWidth() + GAP;

            if (currentX + Panel.getWidth() > screenWidth) {
                currentX = PADDING;
                currentY += Panel.getPadding() + Panel.getEntryHeight() + GAP;
            }
        }
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
