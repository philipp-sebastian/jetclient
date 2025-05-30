package dev.jetclient.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import org.lwjgl.input.Keyboard;

import java.util.Collections;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Keyboard.KEY_0, Category.MOVEMENT, Collections.emptyMap());
    }

    @Override
    public void onEnable() {
        mc.thePlayer.setSprinting(true);
    }

    @Override
    public void onDisable() {
        mc.thePlayer.setSprinting(false);
    }

    @Override
    public void onUpdate() {
        onEnable();
    }
}
