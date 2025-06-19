package dev.jetclient.module.type;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.setting.Setting;

import java.util.Map;

public abstract class RuntimeModule extends Module {
    protected RuntimeModule(String name, int keyBind, Category category, Map<String, Setting> settings) {
        super(name, keyBind, category, settings);
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onUpdate() {}
}
