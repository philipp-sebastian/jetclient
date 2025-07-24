package dev.jetclient.module;

import dev.jetclient.module.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.Map;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getMinecraft();

    private final String name;
    private int keyCode;
    private final Category category;
    private final Map<String, Setting> settings;

    protected Module(String name, Category category, Map<String, Setting> settings) {
        this.name = name;
        this.category = category;
        this.settings = settings;
    }

    public String getName() {
        return this.name;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public Category getCategory() {
        return this.category;
    }

    public Map<String, Setting> getSettings() {
        return this.settings;
    }
}
