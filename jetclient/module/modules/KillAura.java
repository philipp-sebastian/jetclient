package dev.jetclient.module.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.setting.Setting;
import dev.jetclient.setting.settings.SliderSetting;
import dev.jetclient.utils.DelayCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KillAura extends Module {
    private final DelayCalculator delayCalculator;
    private final SliderSetting sliderSetting;
    private final Random random;

    private final int radius = 5;
    private float delay;
    private final int maxDelay = 400;
    private long lastSwingTime = 0;

    public KillAura(DelayCalculator delayCalculator) {
        super("KillAura", Keyboard.KEY_NONE, Category.COMBAT, new HashMap<String, Setting>() {{
            put("Slider", new SliderSetting("Slider"));
        }});
        this.delayCalculator = delayCalculator;
        this.sliderSetting = (SliderSetting) getSettings().get("Slider");
        this.random = new Random();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUpdate() {
        if (System.currentTimeMillis() - lastSwingTime < delay) return;

        //10x10x10
        List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(mc.thePlayer.posX - radius, mc.thePlayer.posY - radius, mc.thePlayer.posZ - radius,
                mc.thePlayer.posX + radius, mc.thePlayer.posY + radius, mc.thePlayer.posZ + radius));
        List<Entity> validEntities = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity != mc.thePlayer && entity instanceof EntityAnimal) {
                validEntities.add(entity);
            }
        }
        if (validEntities.isEmpty()) return;

        Entity entity = validEntities.get(random.nextInt(validEntities.size()));
        float entityX = (float) (entity.posX - mc.thePlayer.posX);
        float entityY = (float) (entity.posY + entity.getEyeHeight() - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight()));
        float entityZ = (float) (entity.posZ - mc.thePlayer.posZ);

        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook((float) Math.toDegrees(Math.atan2(entityZ, entityX)) - 90F,
                (float) -Math.toDegrees(Math.atan2(entityY, Math.sqrt(entityX * entityX + entityZ * entityZ))), mc.thePlayer.onGround));

        mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.ATTACK));
        mc.thePlayer.swingItem();

        lastSwingTime = System.currentTimeMillis();
        delay = delayCalculator.calculateDelay(sliderSetting.getSliderVal(), maxDelay, 10);
    }
}
