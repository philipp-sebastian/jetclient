package dev.jetclient.overlay;

import dev.jetclient.module.ModuleManager;

public abstract class OverlayElement {
    protected ModuleManager moduleManager;
    private boolean active = true;

    public OverlayElement(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return this.active;
    }

    public abstract void onRender2D();
}
