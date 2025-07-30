package dev.jetclient.module.modules.player;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

import java.util.Collections;

public class ScaffoldWalk extends Module {
    double[] previousPlayerPos;
    int previousItem = -1;

    public ScaffoldWalk() {
        super("ScaffoldWalk", Category.PLAYER, Collections.emptyMap());
    }

    @Override
    public void onUpdate() {
        double posX = mc.thePlayer.posX;
        double posY = mc.thePlayer.posY;
        double posZ = mc.thePlayer.posZ;

        BlockPos blockUnder = new BlockPos(posX, posY - 1, posZ);
        if (!mc.theWorld.isAirBlock(blockUnder) || mc.thePlayer.motionY < -0.15) {
            mc.gameSettings.keyBindSneak.pressed = false;
            return;
        }

        if (previousPlayerPos == null) {
            previousPlayerPos = new double[]{posX, posY, posZ};
            return;
        }

        double dx = posX - previousPlayerPos[0];
        double dy = posY - previousPlayerPos[1];
        double dz = posZ - previousPlayerPos[2];
        previousPlayerPos[0] = posX;
        previousPlayerPos[1] = posY;
        previousPlayerPos[2] = posZ;

        double absX = Math.abs(dx);
        double absY = Math.abs(dy);
        double absZ = Math.abs(dz);
        double threshold = 0.1;

        if (absX < threshold && absY < threshold && absZ < threshold) return;

        char match;
        double richtung;
        if (absX >= absY && absX >= absZ) {
            match = 'x';
            richtung = dx;
        } else if (absY >= absX && absY >= absZ) {
            match = 'y';
            richtung = dy;
        } else {
            match = 'z';
            richtung = dz;
        }

        BlockPos supportBlock = null;
        EnumFacing placeAgainst = null;
        switch (match) {
            case 'x':
                supportBlock = new BlockPos(posX - Math.signum(richtung), posY - 1, posZ);
                placeAgainst = richtung > 0 ? EnumFacing.EAST : EnumFacing.WEST;
                break;
            case 'z':
                supportBlock = new BlockPos(posX, posY - 1, posZ - Math.signum(richtung));
                placeAgainst = richtung > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;
                break;
            case 'y':
                supportBlock = new BlockPos(posX, posY - 2, posZ);
                placeAgainst = EnumFacing.UP;
                break;
        }

        if (supportBlock == null || mc.theWorld.isAirBlock(supportBlock)) {
            return;
        }

        previousItem = mc.thePlayer.inventory.currentItem;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof ItemBlock) {
                mc.thePlayer.inventory.currentItem = i;
                float[] rotation = getRotationToBlock(supportBlock);
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(rotation[0], rotation[1], mc.thePlayer.onGround));
                mc.playerController.onPlayerRightClick(
                        mc.thePlayer,
                        mc.theWorld,
                        mc.thePlayer.getHeldItem(),
                        supportBlock,
                        placeAgainst,
                        new Vec3(0.5, 0.5, 0.5)
                );
                mc.gameSettings.keyBindSneak.pressed = false;
                mc.thePlayer.inventory.currentItem = previousItem;
                return;
            }
        }
        /* No block was found */
        if (mc.thePlayer.onGround) mc.gameSettings.keyBindSneak.pressed = true;
    }

    private float[] getRotationToBlock(BlockPos blockPos) {
        double x = blockPos.getX() + 0.5 - mc.thePlayer.posX;
        double y = blockPos.getY() + 0.5 - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double z = blockPos.getZ() + 0.5 - mc.thePlayer.posZ;

        double dXZ = Math.sqrt(x * x + z * z);
        float yaw = (float) Math.toDegrees(Math.atan2(z, x)) - 90F;
        float pitch = (float) -Math.toDegrees(Math.atan2(y, dXZ));
        return new float[]{yaw, pitch};
    }
}
