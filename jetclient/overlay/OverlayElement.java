package dev.jetclient.overlay;

import dev.jetclient.module.ModuleManager;

public abstract class OverlayElement {
    public interface ChangeListener {
        void onActiveChanged(boolean value);
    }

    protected ModuleManager moduleManager;
    private final String name;
    private boolean active = true;
    private ChangeListener changeListener;

    public OverlayElement(ModuleManager moduleManager, String name) {
        this.moduleManager = moduleManager;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setActive(boolean value) {
        this.active = value;
        notifyChange();
    }

    public boolean isActive() {
        return this.active;
    }

    public void setChangeListener(ChangeListener listener) {
        this.changeListener = listener;
    }

    protected void notifyChange() {
        if (changeListener != null) {
            changeListener.onActiveChanged(active);
        }
    }

    public abstract void onRender2D();
}
