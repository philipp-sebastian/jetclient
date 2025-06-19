package dev.jetclient.init;

import dev.jetclient.module.Module;
import dev.jetclient.module.modules.combat.KillAura;
import dev.jetclient.module.modules.movement.Sprint;
import dev.jetclient.module.modules.movement.Step;
import dev.jetclient.module.modules.player.ChestStealer;
import dev.jetclient.module.modules.world.ChestESP;
import dev.jetclient.utils.DelayCalculator;

import java.util.ArrayList;
import java.util.List;

public class ModuleInitializer {
    public static List<Module> createModules(DelayCalculator delayCalculator) {
        List<Module> modules = new ArrayList<>();
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new ChestStealer(delayCalculator));
        modules.add(new KillAura(delayCalculator));
        modules.add(new ChestESP());

        return modules;
    }
}
