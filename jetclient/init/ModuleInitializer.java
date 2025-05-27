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

        modules.add(new Sprint("Sprint", Keyboard.KEY_0, Category.MOVEMENT));
        modules.add(new Step("Step", Keyboard.KEY_9, Category.MOVEMENT));
        modules.add(new ChestStealer("ChestStealer", Keyboard.KEY_2, Category.PLAYER));
        modules.add(new Hud("Hud", Keyboard.KEY_NONE, Category.HUD));

        return modules;
    }
}
