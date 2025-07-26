package dev.jetclient.overlay;

public abstract class OverlayElement {
    public interface ChangeListener {
        void onActiveChanged(boolean value);
    }

    private final String name;
    private boolean active = true;
    private ChangeListener changeListener;

    public OverlayElement(String name) {
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
