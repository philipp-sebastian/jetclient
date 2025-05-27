package dev.jetclient.module;

import dev.jetclient.modules.ChestStealer;
import dev.jetclient.modules.Hud;
import dev.jetclient.modules.Sprint;
import dev.jetclient.modules.Step;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class ModuleManager {
    private Set<Module> allModules = new HashSet<>();
    private Set<Module> activeModules = new HashSet<>();

    public ModuleManager() {
        allModules.add(new Sprint("Sprint", Keyboard.KEY_0, Category.MOVEMENT));
        allModules.add(new Step("Step", Keyboard.KEY_9, Category.MOVEMENT));
        allModules.add(new ChestStealer("ChestStealer", Keyboard.KEY_2, Category.PLAYER));
        allModules.add(new Hud("Hud", Keyboard.KEY_NONE, Category.HUD));
    }

    public Set<Module> getAllModules() {
        return Collections.unmodifiableSet(allModules);
    }

    public Set<Module> getActiveModules() {
        return Collections.unmodifiableSet(activeModules);
    }

    public void onUpdate() {
        for (Module m : activeModules) {
            m.onUpdate();
        }
    }

    public Module getModuleByName(String module) {
        for (Module m : allModules) {
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
        for (Module m : allModules) {
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
