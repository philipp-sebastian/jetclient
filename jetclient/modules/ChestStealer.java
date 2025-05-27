package dev.jetclient.modules;

import dev.jetclient.module.*;
import dev.jetclient.setting.Setting;
import dev.jetclient.settings.BooleanSetting;
import dev.jetclient.settings.SliderSetting;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ChestStealer extends Module {
    private long lastStealTime = 0;
    private long delay = 0;

    private static final int MAX_DELAY = 500;
    private static final int EXTRA_VARIANCE = 75;

    public ChestStealer(String name, int keyBind, Category category) {
        super(name, keyBind, category, new HashMap<String, Setting>() {{
            put("Mode", new BooleanSetting("Mode"));
            put("Test", new BooleanSetting("Test"));
            put("Foo", new BooleanSetting("Foo"));
            put("Slider", new SliderSetting("Slider"));
        }});
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!(mc.currentScreen instanceof GuiChest)) {
            return;
        }

        if (inventoryFull()) {
            mc.thePlayer.closeScreen();
            return;
        }

        if ((System.currentTimeMillis() - lastStealTime) < delay) {
            return;
        }

        GuiChest chestGui = (GuiChest) mc.currentScreen;
        ContainerChest chestContainer = (ContainerChest) chestGui.inventorySlots;

        for (int i = 0; i < chestContainer.getLowerChestInventory().getSizeInventory(); i++) {
            ItemStack itemStack = chestContainer.getLowerChestInventory().getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0) {
                mc.playerController.windowClick(chestContainer.windowId, i, 0, 1, mc.thePlayer);
                lastStealTime = System.currentTimeMillis();
                delay = calculateDelay();
                return;
            }
        }

        mc.thePlayer.closeScreen();
    }

    private long calculateDelay() {
        SliderSetting slider = (SliderSetting) getSettings().get("Slider");
        float sliderVal = slider != null ? slider.getSliderVal() : 0f;

        int minInterval = (int) ((1 - sliderVal) * MAX_DELAY);
        return minInterval + (long) (Math.random() * EXTRA_VARIANCE);
    }

    private boolean inventoryFull() {
        for (ItemStack stack : mc.thePlayer.inventory.mainInventory) {
            if (stack == null || stack.stackSize == 0) return false;
        }
        return true;
    }
}
