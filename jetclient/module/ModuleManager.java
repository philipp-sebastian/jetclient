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

    public Module getModuleByName(String module) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(module)) {
                return m;
            }
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

    public List<Module> getModulesByCategory(Category category) {
        List<Module> modulesByCategory = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == category) {
                modulesByCategory.add(m);
            }
        }
        return modulesByCategory;
    }

    public boolean isModuleActive(String module) {
        if (module == null) return false;

        for (Module m : activeModules) {
            if (m.getName().equals(module)) {
                return true;
            }
        }
        return false;
    }
}
