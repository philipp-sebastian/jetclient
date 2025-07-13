package dev.jetclient.overlay;

import dev.jetclient.module.ModuleManager;

public abstract class OverlayElement {
    protected ModuleManager moduleManager;
    private final String name;
    private boolean active = true;

    public OverlayElement(ModuleManager moduleManager, String name) {
        this.moduleManager = moduleManager;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }

    public abstract void onRender2D();
}
