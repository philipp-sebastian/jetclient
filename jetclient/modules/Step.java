package dev.jetclient.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.Collections;

public class Step extends Module {
    private float stepHeight;

    public Step() {
        super("Step", Keyboard.KEY_NONE, Category.MOVEMENT, Collections.emptyMap());
    }

    @Override
    public void onEnable() {
        this.stepHeight = Minecraft.getMinecraft().thePlayer.stepHeight;
        mc.thePlayer.stepHeight = 1.0F;
    }

    @Override
    public void onDisable() {
        mc.thePlayer.stepHeight = this.stepHeight;
    }

    @Override
    public void onUpdate() {
        mc.thePlayer.stepHeight = 1.0F;
    }
}
