package dev.jetclient.module.modules.combat;

import dev.jetclient.module.Category;
import dev.jetclient.module.type.RuntimeModule;
import dev.jetclient.module.setting.Setting;
import dev.jetclient.module.setting.settings.BooleanSetting;
import dev.jetclient.module.setting.settings.InfoSetting;
import dev.jetclient.module.setting.settings.SliderSetting;
import dev.jetclient.utils.DelayCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class KillAura extends RuntimeModule {
    private final SliderSetting blockRange;
    private final SliderSetting attackSpeed;
    private final BooleanSetting playerTarget;
    private final BooleanSetting animalTarget;
    private final BooleanSetting mobTarget;
    private final Random random;

    private static final int MAX_RADIUS = 10;
    private static final int MAX_DELAY = 400;
    private float delay;
    private long lastSwingTime = 0;

    public KillAura() {
        super("KillAura", Category.COMBAT, new LinkedHashMap<String, Setting>() {{
            put("blockRange", new SliderSetting("Block range:"));
            put("attackSpeed", new SliderSetting("Attack speed:"));
            put("targetInfo", new InfoSetting("Targets:"));
            put("playerTarget", new BooleanSetting("Player"));
            put("animalTarget", new BooleanSetting("Animal"));
            put("mobTarget", new BooleanSetting("Mob"));
        }});
        this.blockRange = (SliderSetting) getSettings().get("blockRange");
        this.attackSpeed = (SliderSetting) getSettings().get("attackSpeed");
        this.playerTarget = (BooleanSetting) this.getSettings().get("playerTarget");
        this.animalTarget = (BooleanSetting) this.getSettings().get("animalTarget");
        this.mobTarget = (BooleanSetting) this.getSettings().get("mobTarget");
        this.random = new Random();
    }

    @Override
    public void onUpdate() {
        if (System.currentTimeMillis() - lastSwingTime < delay) return;

        /* radius should be between (1, 10) */
        float radius =  1 + (blockRange.getSliderVal() * (MAX_RADIUS - 1));
        List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(mc.thePlayer.posX - radius, mc.thePlayer.posY - radius, mc.thePlayer.posZ - radius,
                mc.thePlayer.posX + radius, mc.thePlayer.posY + radius, mc.thePlayer.posZ + radius));
        List<Entity> validEntities = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity != mc.thePlayer && isAttackAllowed(entity)) {
                validEntities.add(entity);
            }
        }
        if (validEntities.isEmpty()) return;

        Entity entity = validEntities.get(random.nextInt(validEntities.size()));
        faceEntity(entity);
        attackEntity(entity);

        lastSwingTime = System.currentTimeMillis();
        delay = DelayCalculator.calculateDelay(attackSpeed.getSliderVal(), MAX_DELAY, 10);
    }

    private void faceEntity(Entity entity) {
        float entityX = (float) (entity.posX - mc.thePlayer.posX);
        float entityY = (float) (entity.posY + entity.getEyeHeight() - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight()));
        float entityZ = (float) (entity.posZ - mc.thePlayer.posZ);

        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook((float) Math.toDegrees(Math.atan2(entityZ, entityX)) - 90F,
                (float) -Math.toDegrees(Math.atan2(entityY, Math.sqrt(entityX * entityX + entityZ * entityZ))), mc.thePlayer.onGround));
    }

    private boolean isAttackAllowed(Entity entity) {
        if (entity instanceof EntityPlayer && playerTarget.getValue()) return true;
        if (entity instanceof EntityAnimal && animalTarget.getValue()) return true;
        if (entity instanceof EntityMob && mobTarget.getValue()) return true;
        return false;
    }

    private void attackEntity(Entity entity) {
        mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.ATTACK));
        mc.thePlayer.swingItem();
    }
}
