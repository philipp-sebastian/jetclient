package dev.jetclient.overlay.elements;

import dev.jetclient.module.Module;
import dev.jetclient.overlay.OverlayElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.List;

public class ModuleList extends OverlayElement {
    private final List<Module> modules;

    public ModuleList(List<Module> modules) {
        super("ModuleList");
        this.modules = modules;
    }

    @Override
    public void onRender2D() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int yOffset = 1;

        for (Module module : modules) {
            if (module.isActive()) {
                int xOffset = sr.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) - 1;
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(module.getName(), xOffset, yOffset, 0xFFFFFF);
                yOffset += 10;
            }
        }
    }
}
