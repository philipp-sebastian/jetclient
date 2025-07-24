package dev.jetclient.config;

import org.lwjgl.input.Keyboard;

public class ConfigHandler {
    private final ConfigFile configFile;

    public ConfigHandler(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public int getKeyCode(String moduleName) {
        String keyCode = configFile.getValue(moduleName);
        if (keyCode != null) {
            return Keyboard.getKeyIndex(keyCode);
        }
        return Keyboard.KEY_NONE;
    }

    public void setKeyCode(String moduleName, int keyCode) {
        configFile.setValue(moduleName, Keyboard.getKeyName(keyCode));
    }

    public boolean getOverlayElementState(String overlayElement) {
        String value = configFile.getValue(overlayElement);
        return value != null && value.equalsIgnoreCase("true");
    }

    public void setOverlayElementState(String overlayElement, boolean state) {
        configFile.setValue(overlayElement, state ? "true" : "false");
    }
}
