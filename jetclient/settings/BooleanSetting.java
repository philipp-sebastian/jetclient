package dev.jetclient.settings;

import dev.jetclient.gui.Panel;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

public class BooleanSetting extends Setting {
    public BooleanSetting(String name) {
        super(name);
    }

    @Override
    public void handleClick(int mouseX, int mouseY) {
        setActive(!getActive());
    }

    @Override
    public void draw(int x, int y) {
        int color = this.getActive() ? Panel.getActiveColor() : Panel.getInactiveColor();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.getName(), x, y, color);
    }
}
