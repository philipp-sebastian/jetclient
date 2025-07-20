package dev.jetclient.module.modules.world;

import dev.jetclient.module.Category;
import dev.jetclient.module.type.Render3DModule;
import dev.jetclient.module.setting.Setting;
import dev.jetclient.module.setting.settings.BooleanSetting;
import dev.jetclient.module.setting.settings.InfoSetting;
import dev.jetclient.utils.GLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.LinkedHashMap;

public class ChestESP extends Render3DModule {
    private final ICamera cameraFrustum;
    private final BooleanSetting chest;
    private final BooleanSetting enderChest;

    private final GLColor YELLOW;
    private final GLColor PURPLE;

    private static final double BLOCK_SIZE = 1.0;

    public ChestESP() {
        super("ChestESP", Keyboard.KEY_NONE, Category.WORLD, new LinkedHashMap<String, Setting>() {{
            put("chestType", new InfoSetting("Chest Type:"));
            put("chest", new BooleanSetting("Chest"));
            put("enderChest", new BooleanSetting("Ender Chest"));
        }});

        cameraFrustum = new Frustum();
        chest = (BooleanSetting) getSettings().get("chest");
        enderChest = (BooleanSetting) getSettings().get("enderChest");

        YELLOW = new GLColor(1.0F, 1.0F, 0.0F, 1.0F);
        PURPLE = new GLColor(0.5F, 0.0F, 1.0F, 1.0F);
    }

    @Override
    public void onRender3D(float partialTicks) {
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

        GL11.glBegin(GL11.GL_LINES);

        for (TileEntity tileEntity : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
            if (!isValidTileEntity(tileEntity)) continue;
            applyColorForTileEntity(tileEntity);

            BlockPos blockPos = tileEntity.getPos();
            if (!cameraFrustum.isBoundingBoxInFrustum(new AxisAlignedBB(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX() + BLOCK_SIZE, blockPos.getY() + BLOCK_SIZE, blockPos.getZ() + BLOCK_SIZE)))
                continue;

            drawBoxAt(blockPos.getX() - camX, blockPos.getY() - camY, blockPos.getZ() - camZ);
        }

        GL11.glEnd();

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private boolean isValidTileEntity(TileEntity tileEntity) {
        return (tileEntity instanceof TileEntityChest && chest.getValue()) ||
                (tileEntity instanceof TileEntityEnderChest && enderChest.getValue());
    }

    private void applyColorForTileEntity(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) YELLOW.apply();
        if (tileEntity instanceof TileEntityEnderChest) PURPLE.apply();
    }

    private void drawBoxAt(double x, double y, double z) {
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