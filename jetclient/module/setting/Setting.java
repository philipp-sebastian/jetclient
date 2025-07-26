package dev.jetclient.module.setting;

public abstract class Setting {
    public interface ChangeListener {
        void onSettingChanged(Setting setting);
    }

    private final String name;
    private ChangeListener changeListener;

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setChangeListener(ChangeListener listener) {
        this.changeListener = listener;
    }

    protected void notifyChange() {
        if (changeListener != null) {
            changeListener.onSettingChanged(this);
        }
    }

    public abstract void handleClick(int mouseX, int mouseY);

    public abstract void draw(int x, int y);

    public abstract int getHeight();
}
