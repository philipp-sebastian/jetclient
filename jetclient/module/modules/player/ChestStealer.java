package dev.jetclient.module.modules.player;

import dev.jetclient.module.Category;
import dev.jetclient.module.type.RuntimeModule;
import dev.jetclient.module.setting.Setting;
import dev.jetclient.module.setting.settings.BooleanSetting;
import dev.jetclient.module.setting.settings.SliderSetting;
import dev.jetclient.utils.DelayCalculator;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import java.util.LinkedHashMap;

public class ChestStealer extends RuntimeModule {
    private long lastStealTime = 0;
    private long delay = 0;

    private static final int MAX_DELAY = 500;
    private static final int EXTRA_VARIANCE = 75;

    public ChestStealer() {
        super("ChestStealer", Category.PLAYER, new LinkedHashMap<String, Setting>() {{
            put("Mode", new BooleanSetting("Mode"));
            put("Test", new BooleanSetting("Test"));
            put("Foo", new BooleanSetting("Foo"));
            put("Slider", new SliderSetting("Slider"));
        }});
    }

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
                SliderSetting sliderSetting = (SliderSetting) getSettings().get("Slider");
                delay = DelayCalculator.calculateDelay(sliderSetting.getSliderVal(), MAX_DELAY, EXTRA_VARIANCE);
                return;
            }
        }

        mc.thePlayer.closeScreen();
    }

    private boolean inventoryFull() {
        for (ItemStack stack : mc.thePlayer.inventory.mainInventory) {
            if (stack == null || stack.stackSize == 0) return false;
        }
        return true;
    }
}
