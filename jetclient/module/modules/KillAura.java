package dev.jetclient.module.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.setting.Setting;
import dev.jetclient.setting.settings.SliderSetting;
import dev.jetclient.utils.DelayCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.List;

public class KillAura extends Module {
    private final DelayCalculator delayCalculator;
    private final int radius = 5;
    private float delay;
    private final int maxDelay = 400;
    private long lastSwingTime = 0;

    public KillAura(DelayCalculator delayCalculator) {
        super("KillAura", Keyboard.KEY_NONE, Category.COMBAT, new HashMap<String, Setting>() {{
            put("Slider", new SliderSetting("Slider"));
        }});
        this.delayCalculator = delayCalculator;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUpdate() {
        //10x10x10
        AxisAlignedBB box = new AxisAlignedBB(mc.thePlayer.posX - radius, mc.thePlayer.posY - radius, mc.thePlayer.posZ - radius, mc.thePlayer.posX + radius, mc.thePlayer.posY + radius, mc.thePlayer.posZ + radius);
        List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, box);

        for (Entity entity : entities) {
            if (entity == mc.thePlayer) continue;

            if (entity instanceof EntityAnimal) {
                float entityX = (float) (entity.posX - mc.thePlayer.posX);
                float entityY = (float) (entity.posY + entity.getEyeHeight() - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight()));
                float entityZ = (float) (entity.posZ - mc.thePlayer.posZ);

                mc.thePlayer.rotationYaw = (float) Math.toDegrees(Math.atan2(entityZ, entityX)) - 90F;
                mc.thePlayer.rotationPitch = (float) -Math.toDegrees(Math.atan2(entityY, Math.sqrt(entityX * entityX + entityZ * entityZ)));

                if (System.currentTimeMillis() - lastSwingTime < delay) return;

                mc.thePlayer.swingItem();
                mc.playerController.attackEntity(mc.thePlayer, entity);

                lastSwingTime = System.currentTimeMillis();
                SliderSetting sliderSetting = (SliderSetting) getSettings().get("Slider");
                delay = delayCalculator.calculateDelay(sliderSetting.getSliderVal(), maxDelay, 10);
                return;

            }
        }
    }
}
