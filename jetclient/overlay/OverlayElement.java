package dev.jetclient.overlay;

public abstract class OverlayElement {
    private final String name;
    private boolean active = true;

    public OverlayElement(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setActive(boolean value) {
        this.active = value;
    }

    public boolean isActive() {
        return this.active;
    }

    public abstract void onRender2D();
}
