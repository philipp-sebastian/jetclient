package dev.jetclient.init;

import dev.jetclient.gui.Panel;
import dev.jetclient.module.Category;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;
import java.util.List;

public class PanelInitializer {
    public static List<Panel> createPanels(ModuleManager moduleManager) {
        final int PADDING = 15;
        final int GAP = 10;

        List<Panel> panels = new ArrayList<>();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = sr.getScaledWidth();
        int currentX = PADDING;
        int currentY = PADDING;

        for (Category category : Category.values()) {
            panels.add(new Panel(moduleManager, moduleManager.getModulesByCategory(category), category, currentX, currentY));
            currentX += Panel.getWidth() + GAP;

            if (currentX + Panel.getWidth() > screenWidth) {
                currentX = PADDING;
                currentY = PADDING + Panel.getPadding() + Panel.getEntryHeight() + GAP;
            }
        }

        return panels;
    }
}
