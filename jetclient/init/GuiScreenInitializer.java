package dev.jetclient.init;

import dev.jetclient.gui.AltManagerScreen;
import dev.jetclient.gui.ClickGui;
import dev.jetclient.gui.PanelManager;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class GuiScreenInitializer {
    public static List<GuiScreen> createGuiScreens(PanelManager panelManager) {
        List<GuiScreen> guiScreens = new ArrayList<>();
        guiScreens.add(new ClickGui(panelManager, Keyboard.KEY_RSHIFT));
        guiScreens.add(new AltManagerScreen());

        return guiScreens;
    }
}
