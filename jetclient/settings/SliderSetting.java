package dev.jetclient.settings;

import dev.jetclient.gui.Panel;
import dev.jetclient.setting.Setting;
import net.minecraft.client.gui.Gui;

public class SliderSetting extends Setting {
    private int leftX;
    private float sliderVal = 0.5f;

    public SliderSetting(String name) {
        super(name);
    }

    @Override
    public void handleClick(int mouseX, int mouseY) {
        this.sliderVal = (float) (mouseX - this.leftX) / Panel.getWidth();
        this.sliderVal = Math.max(0f, Math.min(1f, this.sliderVal));
    }

    @Override
    public void draw(int x, int y) {
        this.leftX = x;

        Gui.drawRect(x, y, x + Panel.getWidth(), y + Panel.getEntryHeight(), 0xFF222222);
        Gui.drawRect(x, y, (int) (x + (sliderVal * 100)), y + Panel.getEntryHeight(), 0xFF3EE900);
    }

    public float getSliderVal() {
        return this.sliderVal;
    }
}
