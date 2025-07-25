package dev.jetclient.overlay.elements;

import dev.jetclient.JetClient;
import dev.jetclient.module.Module;
import dev.jetclient.overlay.OverlayElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ModuleList extends OverlayElement {

    public ModuleList() {
        super(JetClient.getModuleManager(), "ModuleList");
    }

    @Override
    public void onRender2D() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int yOffset = 1;

        for (Module activeModule : moduleManager.getActiveModules()) {
            int xOffset = sr.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(activeModule.getName()) - 1;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(activeModule.getName(), xOffset, yOffset, 0xFFFFFF);
            yOffset += 10;
        }
    }
}
