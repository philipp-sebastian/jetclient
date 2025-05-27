package dev.jetclient.hud.items;

import dev.jetclient.hud.HudItem;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ModuleList extends HudItem {

    public ModuleList(ModuleManager moduleManager) {
        super(moduleManager);
    }

    @Override
    public void renderItem() {
        if (!shouldRender()) return;

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int yOffset = 1;

        Module hudModule = moduleManager.getModuleByName("Hud");

        for (Module m : moduleManager.getActiveModules()) {
            if (hudModule != null && m.getName().equals(hudModule.getName())) continue;

            int xOffset = sr.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.getName()) - 1;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(m.getName(), xOffset, yOffset, 0xFFFFFF);
            yOffset += 10;
        }
    }
}
