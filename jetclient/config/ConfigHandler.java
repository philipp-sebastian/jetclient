package dev.jetclient.config;

import org.lwjgl.input.Keyboard;

public class ConfigHandler {
    private final ConfigFile configFile;

    public ConfigHandler(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public int getKeyCode(String moduleName) {
        String keyCode = configFile.getValue(moduleName + "_keycode");
        if (keyCode != null) {
            return Keyboard.getKeyIndex(keyCode);
        }
        return Keyboard.KEY_NONE;
    }

    public void setKeyCode(String moduleName, int keyCode) {
        configFile.setValue(moduleName + "_keycode", Keyboard.getKeyName(keyCode));
    }

    public boolean getOverlayElementEnabled(String overlayElement) {
        String value = configFile.getValue(overlayElement + "_enabled");
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public void setOverlayElementEnabled(String overlayElement, boolean enabled) {
        configFile.setValue(overlayElement + "_enabled", Boolean.toString(enabled));
    }

    public float getSliderValue(String moduleName, String settingsName) {
        String value = configFile.getValue(moduleName + "_" + settingsName);
        if (value != null) {
            return Float.parseFloat(value);
        }
        return 0.5F;
    }

    public void setSliderValue(String moduleName, String settingsName, float value) {
        configFile.setValue(moduleName + "_" + settingsName, Float.toString(value));
    }

    public boolean getBooleanValue(String moduleName, String settingsName) {
        String value = configFile.getValue(moduleName + "_" + settingsName);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public void setBooleanValue(String moduleName, String settingsName, boolean value) {
        configFile.setValue(moduleName + "_" + settingsName, Boolean.toString(value));
    }

    public boolean getModuleEnabled(String moduleName) {
        String value = configFile.getValue(moduleName + "_enabled");
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public void setModuleEnabled(String moduleName, boolean enabled) {
        configFile.setValue(moduleName + "_enabled", Boolean.toString(enabled));
    }

    public void saveConfig() {
        configFile.saveConfig();
    }
}
