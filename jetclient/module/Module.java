package dev.jetclient.module;

import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getMinecraft();
    private int keyBind;
    private final String name;
    private final Category category;
    private HashMap<String, Setting> settings = new HashMap<>();

    protected Module(String name, int keyBind, Category category) {
        this.name = name;
        this.keyBind = keyBind;
        this.category = category;
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

    public Category getCategory() { return this.category; }

    public HashMap<String, Setting> getSettings() { return this.settings; }

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onUpdate();
}
