package dev.jetclient.hud.items;

import dev.jetclient.hud.HudItem;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.module.modules.Hud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ModuleList extends HudItem {

    public ModuleList(ModuleManager moduleManager) {
        super(moduleManager);
    }

    @Override
    public void renderItem() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int yOffset = 1;

        for (Module module : moduleManager.getActiveModules()) {
            if (module instanceof Hud) continue;

            int xOffset = sr.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) - 1;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(module.getName(), xOffset, yOffset, 0xFFFFFF);
            yOffset += 10;
        }
    }
}
