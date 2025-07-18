package dev.jetclient.module;

import dev.jetclient.module.type.Render2DModule;
import dev.jetclient.module.type.RuntimeModule;
import dev.jetclient.module.type.Render3DModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules;
    private final List<Module> activeModules;

    public ModuleManager(List<Module> modules) {
        this.modules = modules;
        this.activeModules = new ArrayList<>();
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

    public Module getModule(String module) {
        for (Module m : modules) {
            if (m.getName().equals(module)) return m;
        }
        return null;
    }

    public void toggleModule(Module m) {
        if (activeModules.contains(m)) {
            activeModules.remove(m);
        } else {
            activeModules.add(m);
        }
    }

    public void handleKeyEvent(int keyCode) {
        for (Module m : modules) {
            if (m.getKeyBind() == keyCode) {
                toggleModule(m);
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
