package dev.jetclient.config;

import org.lwjgl.input.Keyboard;

public class ConfigHandler {
    private ConfigFile configFile;

    public ConfigHandler(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public int getKeyBind(String moduleName) {
        String keyBind = configFile.getValue(moduleName);
        if (keyBind != null) {
            return Keyboard.getKeyIndex(keyBind);
        }
        return Keyboard.KEY_NONE;
    }

    public void setKeyBind(String moduleName, int keyBind) {
        configFile.setValue(moduleName, Keyboard.getKeyName(keyBind));
    }
}
