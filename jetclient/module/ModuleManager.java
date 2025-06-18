package dev.jetclient.module;

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
        for (Module m : activeModules) {
            m.onUpdate();
        }
    }

    public <T extends Module> T getModule(Class<T> module) {
        if (module != null) {
            for (Module m : modules) {
               if (module.isInstance(m)) return module.cast(m);
            }
        }
        return null;
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

    public boolean handleKeyEvent(int keyCode) {
        for (Module m : modules) {
            if (m.getKeyBind() == keyCode) {
                toggleModule(m);
                return true;
            }
        }
        return false;
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
}
