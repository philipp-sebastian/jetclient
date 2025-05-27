package dev.jetclient.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {

    public Sprint(String name, int keyBind, Category category) {
        super(name, keyBind, category);
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
