package dev.jetclient.overlay;

import dev.jetclient.module.ModuleManager;

public abstract class OverlayElement {
    protected ModuleManager moduleManager;

    public OverlayElement(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public abstract void onRender2D();
}
