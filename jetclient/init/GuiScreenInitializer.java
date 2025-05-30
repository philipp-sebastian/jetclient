package dev.jetclient.init;

import dev.jetclient.gui.screens.AltLogin;
import dev.jetclient.gui.screens.modulegui.ModuleGui;
import dev.jetclient.gui.screens.modulegui.PanelManager;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiScreenInitializer {
    public static List<GuiScreen> createGuiScreens(PanelManager panelManager) {
        List<GuiScreen> guiScreens = new ArrayList<>();
        guiScreens.add(new ModuleGui(panelManager));
        guiScreens.add(new AltLogin());

        return guiScreens;
    }
}
