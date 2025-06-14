package dev.jetclient.setting.settings;

import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

public class InfoSetting extends Setting {

    public InfoSetting(String name) {
        super(name);
    }

    @Override
    public void handleClick(int mouseX, int mouseY) { /* do nothing */ }

    @Override
    public void draw(int x, int y) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.getName(), x, y, 0xFFFFFFFF);
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
