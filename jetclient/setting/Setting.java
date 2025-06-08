package dev.jetclient.setting;

public abstract class Setting {
    private final String name;

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void handleClick(int mouseX, int mouseY);

    public abstract void draw(int x, int y);

    public abstract int getHeight();
}
