package dev.jetclient.hud;

import dev.jetclient.module.ModuleManager;
import dev.jetclient.module.modules.Hud;

public abstract class HudItem {
    protected ModuleManager moduleManager;

    public HudItem(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    protected boolean shouldRender() {
        return moduleManager.isModuleActive(Hud.class);
    }

    public abstract void renderItem();
}
