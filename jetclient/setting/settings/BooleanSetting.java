package dev.jetclient.setting.settings;

import dev.jetclient.gui.screens.modulegui.Panel;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

public class BooleanSetting extends Setting {
    private boolean value = false;

    public BooleanSetting(String name) {
        super(name);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public void handleClick(int mouseX, int mouseY) {
        setValue(!getValue());
    }

    @Override
    public void draw(int x, int y) {
        int color = getValue() ? Panel.getActiveColor() : Panel.getInactiveColor();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.getName(), x, y, color);
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
