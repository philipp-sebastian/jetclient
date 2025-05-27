package dev.jetclient.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.client.Minecraft;

public class Step extends Module {
    private float stepHeight;

    public Step(String name, int keyBind, Category category) {
        super(name, keyBind, category);
    }

    @Override
    public void onEnable() {
        stepHeight = Minecraft.getMinecraft().thePlayer.stepHeight;
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
