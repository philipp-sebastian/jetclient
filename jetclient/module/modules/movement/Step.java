package dev.jetclient.module.modules.movement;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.type.RuntimeModule;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.Collections;

public class Step extends RuntimeModule {
    private float stepHeight;

    public Step() {
        super("Step", Category.MOVEMENT, Collections.emptyMap());
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
