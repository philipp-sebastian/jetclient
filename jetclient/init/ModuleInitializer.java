package dev.jetclient.init;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.modules.ChestStealer;
import dev.jetclient.modules.Hud;
import dev.jetclient.modules.Sprint;
import dev.jetclient.modules.Step;
import org.lwjgl.input.Keyboard;

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
