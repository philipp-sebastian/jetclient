package dev.jetclient.module.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;

public class KillAura extends Module {
    private final int radius = 5;

    public KillAura() {
        super("KillAura", Keyboard.KEY_NONE, Category.COMBAT, Collections.emptyMap());
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
                double entityX = entity.posX - mc.thePlayer.posX;
                double entityY = entity.posY + (entity.height / 2) - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
                double entityZ = entity.posZ - mc.thePlayer.posZ;

                mc.thePlayer.rotationYaw = (float) Math.toDegrees(Math.atan2(entityZ, entityX) - 90F);
                mc.thePlayer.rotationPitch = (float) Math.toDegrees(Math.atan2(entityY, Math.sqrt(entityX * entityX + entityZ * entityZ)));

                mc.thePlayer.swingItem();
            }
        }
    }
}
