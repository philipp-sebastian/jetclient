package dev.jetclient.module;

import dev.jetclient.config.ConfigHandler;
import dev.jetclient.module.type.Render2DModule;
import dev.jetclient.module.type.RuntimeModule;
import dev.jetclient.module.type.Render3DModule;

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
        initKeyCodes(modules);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getActiveModules() {
        return activeModules;
    }

    public void onUpdate() {
        for (Module activeModule : activeModules) {
            if (activeModule instanceof RuntimeModule) {
                ((RuntimeModule) activeModule).onUpdate();
            }
        }
    }

    private void initKeyCodes(List<Module> modules) {
        for (Module module : modules) {
            module.setKeyCode(getKeyCode(module));
        }
    }

    public Module getModule(String module) {
        for (Module m : modules) {
            if (m.getName().equals(module)) return m;
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
        for (Module m : modules) {
            if (m.getCategory() == category) {
                modulesByCategory.add(m);
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
        for (Module activeModule : activeModules) {
            if (activeModule instanceof Render3DModule) {
                ((Render3DModule) activeModule).onRender3D(partialTicks);
            }
        }
    }

    public void onRender2D() {
        for (Module activeModule : activeModules) {
            if (activeModule instanceof Render2DModule) {
                ((Render2DModule) activeModule).onRender2D();
            }
        }
    }
}
