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
        initModuleStates();
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

    private void initModuleStates() {
        for (Module module : modules) {
            module.setActive(configHandler.getModuleEnabled(module.getName()));
        }
    }

    private void initKeyCodes() {
        for (Module module : modules) {
            module.setKeyCode(configHandler.getKeyCode(module.getName()));
        }
    }

    private void initModuleSettings() {
        for (Module module : modules) {
            for (Map.Entry<String, Setting> setting : module.getSettings().entrySet()) {
                String key = setting.getKey();
                Setting value = setting.getValue();

                if (value instanceof SliderSetting) {
                    ((SliderSetting) value).setSliderVal(configHandler.getSliderValue(module.getName(), key));
                } else if (value instanceof BooleanSetting) {
                    ((BooleanSetting) value).setValue(configHandler.getBooleanValue(module.getName(), key));
                }
            }
        }
    }

    public void onShutdown() {
        for (Module module : modules) {
            configHandler.setModuleEnabled(module.getName(), module.isActive());
            configHandler.setKeyCode(module.getName(), module.getKeyCode());
            for (Map.Entry<String, Setting> setting : module.getSettings().entrySet()) {
                String key = setting.getKey();
                Setting value = setting.getValue();
                if (value instanceof BooleanSetting) {
                    configHandler.setBooleanValue(module.getName(), key, ((BooleanSetting) value).getValue());
                }
                if (value instanceof SliderSetting) {
                    configHandler.setSliderValue(module.getName(), key, ((SliderSetting) value).getSliderVal());
                }
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
