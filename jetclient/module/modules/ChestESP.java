package dev.jetclient.module.modules;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class ChestESP extends Module {
    private final ICamera cameraFrustum;

    private static final double BLOCK_SIZE = 1.0;

    public ChestESP() {
        super("ChestESP", Keyboard.KEY_NONE, Category.RENDER, Collections.emptyMap());

        cameraFrustum = new Frustum();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUpdate() {

        //annahme kiste ist bei 100, 54, 2
        //minecraft rendert aber immer relativ zur kamera
        //partialTicks ist der Anteil des aktuellen Frames zwischen zwei Ticks – für flüssiges Rendering.zwischen 0.0f und BLOCK_SIZEf

    }


    public void onRender(float partialTicks) {
        RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        double camX = rm.viewerPosX;
        double camY = rm.viewerPosY;
        double camZ = rm.viewerPosZ;

        cameraFrustum.setPosition(camX, camY, camZ);

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2.0F);
        GL11.glColor4f(1.0F, 1.0F, 0.0F, 1.0F); // gelb

        GL11.glBegin(GL11.GL_LINES);

        for (TileEntity tileEntity : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityChest) {
                BlockPos blockPos = tileEntity.getPos();
                if (!cameraFrustum.isBoundingBoxInFrustum(new AxisAlignedBB(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX() + BLOCK_SIZE, blockPos.getY() + BLOCK_SIZE, blockPos.getZ() + BLOCK_SIZE)))
                    continue;

                double x = blockPos.getX() - camX;
                double y = blockPos.getY() - camY;
                double z = blockPos.getZ() - camZ;

                // Boden
                GL11.glVertex3d(x, y, z);
                GL11.glVertex3d(x + BLOCK_SIZE, y, z);

                GL11.glVertex3d(x, y, z);
                GL11.glVertex3d(x, y, z + BLOCK_SIZE);

                GL11.glVertex3d(x + BLOCK_SIZE, y, z);
                GL11.glVertex3d(x + BLOCK_SIZE, y, z + BLOCK_SIZE);

                GL11.glVertex3d(x, y, z + BLOCK_SIZE);
                GL11.glVertex3d(x + BLOCK_SIZE, y, z + BLOCK_SIZE);

                // Vertikal
                GL11.glVertex3d(x, y, z);
                GL11.glVertex3d(x, y + BLOCK_SIZE, z);

                GL11.glVertex3d(x + BLOCK_SIZE, y, z);
                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z);

                GL11.glVertex3d(x, y, z + BLOCK_SIZE);
                GL11.glVertex3d(x, y + BLOCK_SIZE, z + BLOCK_SIZE);

                GL11.glVertex3d(x + BLOCK_SIZE, y, z + BLOCK_SIZE);
                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z + BLOCK_SIZE);

                // Decke
                GL11.glVertex3d(x, y + BLOCK_SIZE, z);
                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z);

                GL11.glVertex3d(x, y + BLOCK_SIZE, z);
                GL11.glVertex3d(x, y + BLOCK_SIZE, z + BLOCK_SIZE);

                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z);
                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z + BLOCK_SIZE);

                GL11.glVertex3d(x, y + BLOCK_SIZE, z + BLOCK_SIZE);
                GL11.glVertex3d(x + BLOCK_SIZE, y + BLOCK_SIZE, z + BLOCK_SIZE);
            }
        }

        GL11.glEnd();

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
