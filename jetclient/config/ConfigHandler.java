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
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public void setOverlayElementState(String overlayElement, boolean state) {
        configFile.setValue(overlayElement, Boolean.toString(state));
    }

    public float getModuleSettingSliderValue(String moduleName, String settingsName) {
        String value = configFile.getValue(moduleName + "_" + settingsName);
        if (value != null) {
            return Float.parseFloat(value);
        }
        return 0.5F;
    }

    public boolean getModuleSettingBooleanValue(String moduleName, String settingsName) {
        String value = configFile.getValue(moduleName + "_" + settingsName);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public void setModuleSettingSliderValue(String moduleName, String settingsName, float value) {
        configFile.setValue(moduleName + "_" + settingsName, Float.toString(value));
    }

    public void setModuleSettingBooleanValue(String moduleName, String settingsName, boolean value) {
        configFile.setValue(moduleName + "_" + settingsName, Boolean.toString(value));
    }
}
