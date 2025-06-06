package dev.jetclient.init;

import dev.jetclient.module.Module;
import dev.jetclient.module.modules.*;
import dev.jetclient.utils.DelayCalculator;

import java.util.ArrayList;
import java.util.List;

public class ModuleInitializer {
    public static List<Module> createModules(DelayCalculator delayCalculator) {
        List<Module> modules = new ArrayList<>();
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new ChestStealer(delayCalculator));
        modules.add(new Hud());
        modules.add(new KillAura(delayCalculator));

        return modules;
    }
}
