package dev.jetclient.settings;

import dev.jetclient.gui.screens.modulegui.Panel;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

public class BooleanSetting extends Setting {
    private boolean active = false;

    public BooleanSetting(String name) {
        super(name);
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void handleClick(int mouseX, int mouseY) {
        setActive(!getActive());
    }

    @Override
    public void draw(int x, int y) {
        int color = getActive() ? Panel.getActiveColor() : Panel.getInactiveColor();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.getName(), x, y, color);
    }
}
