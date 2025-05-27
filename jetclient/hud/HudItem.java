package dev.jetclient.hud;

import dev.jetclient.module.ModuleManager;

public abstract class HudItem {
    protected ModuleManager moduleManager;

    public HudItem(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    protected boolean shouldRender() {
        return moduleManager.isModuleActive("Hud");
    }

    public abstract void renderItem();
}
