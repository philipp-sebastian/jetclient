package dev.jetclient.module.type;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.setting.Setting;
import java.util.Map;

public abstract class Render3DModule extends Module {
    protected Render3DModule(String name, int keyBind, Category category, Map<String, Setting> settings) {
        super(name, keyBind, category, settings);
    }

    public abstract void onRender3D(float partialTicks);
}