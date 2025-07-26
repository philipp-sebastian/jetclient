package dev.jetclient.module;

import dev.jetclient.config.ConfigHandler;
import dev.jetclient.module.setting.Setting;
import dev.jetclient.module.setting.settings.BooleanSetting;
import dev.jetclient.module.setting.settings.SliderSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModuleManager {
    private final List<Module> modules;
    private final ConfigHandler configHandler;

    public ModuleManager(List<Module> modules, ConfigHandler configHandler) {
        this.modules = modules;
        this.configHandler = configHandler;
        initKeyCodes();
        initModuleSettings();
    }

    public List<Module> getModules() {
        return modules;
    }

    public void onUpdate() {
        for (Module module : modules) {
            if (module.isActive()) {
                module.onUpdate();
            }
        }
    }

    private void initKeyCodes() {
        for (Module module : modules) {
            module.setKeyCode(configHandler.getKeyCode(module.getName()));
            module.setChangeListener((int keyCode) -> {
                configHandler.setKeyCode(module.getName(), keyCode);
            });
        }
    }

    private void initModuleSettings() {
        for (Module module : modules) {
            for (Map.Entry<String, Setting> entries : module.getSettings().entrySet()) {
                String settingsKey = entries.getKey();
                Setting setting = entries.getValue();

                /* Get settings from config */
                if (setting instanceof SliderSetting) {
                    ((SliderSetting) setting).setSliderVal(configHandler.getModuleSettingSliderValue(module.getName(), settingsKey));
                } else if (setting instanceof BooleanSetting) {
                    ((BooleanSetting) setting).setValue(configHandler.getModuleSettingBooleanValue(module.getName(), settingsKey));
                }

                /* Set listeners */
                setting.setChangeListener((changedSetting) -> {
                    if (changedSetting instanceof SliderSetting) {
                        configHandler.setModuleSettingSliderValue(module.getName(), settingsKey, ((SliderSetting) changedSetting).getSliderVal());
                    } else if (changedSetting instanceof BooleanSetting) {
                        configHandler.setModuleSettingBooleanValue(module.getName(), settingsKey, ((BooleanSetting) changedSetting).getValue());
                    }
                });
            }
        }
    }

    public Module getModule(String module) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(module)) return m;
        }
        return null;
    }

    public void handleKeyEvent(int keyCode) {
        for (Module module : modules) {
            if (module.getKeyCode() == keyCode) {
                module.setActive(!module.isActive());
                break;
            }
        }
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> modulesByCategory = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                modulesByCategory.add(module);
            }
        }
        return modulesByCategory;
    }

    public void onRender3D(float partialTicks) {
        for (Module module : modules) {
            if (module.isActive()) {
                module.onRender3D(partialTicks);
            }
        }
    }

    public void onRender2D() {
        for (Module module : modules) {
            if (module.isActive()) {
                module.onRender2D();
            }
        }
    }
}
