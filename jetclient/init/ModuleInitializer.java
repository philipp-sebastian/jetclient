package dev.jetclient.init;

import dev.jetclient.module.Module;
import dev.jetclient.module.modules.ChestStealer;
import dev.jetclient.module.modules.Hud;
import dev.jetclient.module.modules.Sprint;
import dev.jetclient.module.modules.Step;

import java.util.ArrayList;
import java.util.List;

public class ModuleInitializer {
    public static List<Module> createModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new ChestStealer());
        modules.add(new Hud());

        return modules;
    }
}
