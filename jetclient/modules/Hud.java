package dev.jetclient.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import org.lwjgl.input.Keyboard;

import java.util.Collections;

public class Hud extends Module {

    public Hud() {
        super("Hud", Keyboard.KEY_NONE, Category.HUD, Collections.emptyMap());
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {}
}
