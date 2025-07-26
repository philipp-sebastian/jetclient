package dev.jetclient.module.setting;

import dev.jetclient.module.Module;

public abstract class Setting {
    public interface ChangeListener {
        void onSettingChanged(Module module, Setting setting);
    }

    private final String name;
    private Module owner;
    private ChangeListener changeListener;

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOwner(Module module) {
        this.owner = module;
    }

    public void setChangeListener(ChangeListener listener) {
        this.changeListener = listener;
    }

    protected void notifyChange() {
        if (changeListener != null && owner != null) {
            changeListener.onSettingChanged(owner, this);
        }
    }
    public abstract void handleClick(int mouseX, int mouseY);

    public abstract void draw(int x, int y);

    public abstract int getHeight();
}
