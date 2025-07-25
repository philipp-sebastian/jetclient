package dev.jetclient.module;

import dev.jetclient.config.ConfigHandler;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules;
    private final List<Module> activeModules;
    private final ConfigHandler configHandler;

    public ModuleManager(List<Module> modules, ConfigHandler configHandler) {
        this.modules = modules;
        this.activeModules = new ArrayList<>();
        this.configHandler = configHandler;
        initKeyCodes();
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getActiveModules() {
        return activeModules;
    }

    public void onUpdate() {
       for (Module module : activeModules) {
           module.onUpdate();
       }
    }

    private void initKeyCodes() {
        for (Module module : modules) {
            module.setKeyCode(getKeyCode(module));
        }
    }

    public Module getModule(String module) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(module)) return m;
        }
        return null;
    }

    public void setKeyCode(Module module, int keyCode) {
        module.setKeyCode(keyCode);
        configHandler.setKeyCode(module.getName(), keyCode);
    }

    public int getKeyCode(Module module) {
        return configHandler.getKeyCode(module.getName());
    }

    public void toggleModule(Module module) {
        if (activeModules.contains(module)) {
            activeModules.remove(module);
        } else {
            activeModules.add(module);
        }
    }

    public void handleKeyEvent(int keyCode) {
        for (Module module : modules) {
            if (module.getKeyCode() == keyCode) {
                toggleModule(module);
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

    public boolean isModuleActive(Class<? extends Module> module) {
        if (module != null) {
            for (Module activeModule : activeModules) {
                if (module.isInstance(activeModule)) return true;
            }
        }
        return false;
    }

    public void onRender3D(float partialTicks) {
        for (Module module : activeModules) {
            module.onRender3D(partialTicks);
        }
    }

    public void onRender2D() {
        for (Module module : activeModules) {
            module.onRender2D();
        }
    }
}
