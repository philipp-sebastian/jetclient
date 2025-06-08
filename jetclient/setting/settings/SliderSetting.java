package dev.jetclient.setting.settings;

import dev.jetclient.gui.screens.modulegui.Panel;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class SliderSetting extends Setting {
    private int x;
    private int y;
    private float sliderVal = 0.5f;

    public SliderSetting(String name) {
        super(name);
    }

    public float getSliderVal() {
        return this.sliderVal;
    }

    @Override
    public void handleClick(int mouseX, int mouseY) {
        /* return, if click was not on the slider */
        if (mouseY < this.y + Panel.getEntryHeight()) return;

        this.sliderVal = (float) (mouseX - this.x) / Panel.getWidth();
        this.sliderVal = Math.max(0f, Math.min(1f, this.sliderVal));
    }

    @Override
    public void draw(int x, int y) {
        this.x = x; this.y = y;

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.getName(), x, y, 0xFFFFFFFF);
        Gui.drawRect(x, y + Panel.getEntryHeight(), x + Panel.getWidth(), y + (2 * Panel.getEntryHeight()), 0xFF222222);
        Gui.drawRect(x, y + Panel.getEntryHeight(), (int) (x + (sliderVal * 100)), y + (2 * Panel.getEntryHeight()), 0xFF3EE900);
    }

    @Override
    public int getHeight() {
        return 2 * Panel.getEntryHeight();
    }
}
