package dev.jetclient.module.modules.movement;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.type.RuntimeModule;
import org.lwjgl.input.Keyboard;

import java.util.Collections;

public class Sprint extends RuntimeModule {

    public Sprint() {
        super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT, Collections.emptyMap());
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
