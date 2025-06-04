package dev.jetclient.init;

import dev.jetclient.module.Module;
import dev.jetclient.module.modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleInitializer {
    public static List<Module> createModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new ChestStealer());
        modules.add(new Hud());
        modules.add(new KillAura());

        return modules;
    }
}
