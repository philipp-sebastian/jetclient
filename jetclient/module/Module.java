package dev.jetclient.module;

import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.Map;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getMinecraft();

    private final String name;
    private int keyBind;
    private final Category category;
    private final Map<String, Setting> settings;

    protected Module(String name, int keyBind, Category category, Map<String, Setting> settings) {
        this.name = name;
        this.keyBind = keyBind;
        this.category = category;
        this.settings = settings;
    }

    public String getName() {
        return this.name;
    }

    public int getKeyBind() {
        return this.keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public Category getCategory() {
        return this.category;
    }

    public Map<String, Setting> getSettings() {
        return this.settings;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onUpdate();
}
