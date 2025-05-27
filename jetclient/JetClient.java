package dev.jetclient;

import dev.jetclient.command.Command;
import dev.jetclient.command.CommandManager;
import dev.jetclient.gui.*;
import dev.jetclient.hud.HudItem;
import dev.jetclient.hud.HudItemManager;
import dev.jetclient.hud.items.ModuleList;
import dev.jetclient.keybinds.KeybindHandler;
import dev.jetclient.module.Category;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class JetClient {
    private static GuiScreenManager guiScreenManager;
    private static ModuleManager moduleManager;
    private static KeybindHandler keybindHandler;
    private static HudItemManager hudItemManager;
    private static CommandManager commandManager;
    private static PanelManager panelManager;

    private final static String clientName = "Jet";

    public static String getClientName() {
        return clientName;
    }

    public static void initialize() {
        moduleManager = new ModuleManager();
        panelManager = new PanelManager(initializePanelList());
        guiScreenManager = new GuiScreenManager(initializeGuiScreenList());
        keybindHandler = new KeybindHandler(moduleManager, guiScreenManager);
        hudItemManager = new HudItemManager(initializeHudItemList());
        commandManager = new CommandManager(moduleManager);
    }

    private static List<GuiScreen> initializeGuiScreenList() {
        List<GuiScreen> guiScreens = new ArrayList<>();
        guiScreens.add(new ClickGui(panelManager, Keyboard.KEY_RSHIFT));
        guiScreens.add(new AltManagerScreen());

        return guiScreens;
    }

    private static List<Panel> initializePanelList() {
        final int PADDING = 15;
        final int GAP = 10;

        List<Panel> panels = new ArrayList<>();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = sr.getScaledWidth();
        int currentX = PADDING;
        int currentY = PADDING;

        for (Category category : Category.values()) {
            panels.add(new Panel(moduleManager, moduleManager.getModulesByCategory(category), category, currentX, currentY));
            currentX += Panel.getWidth() + GAP;

            if (currentX + Panel.getWidth() > screenWidth) {
                currentX = PADDING;
                currentY = PADDING + Panel.getPadding() + Panel.getEntryHeight() + GAP;
            }
        }

        return panels;
    }

    public static List<HudItem> initializeHudItemList() {
        List<HudItem> hudItems = new ArrayList<>();
        hudItems.add(new ModuleList(moduleManager));

        return hudItems;
    }

    public static void injectDependencies(Minecraft mc) {
        mc.setModuleManager(moduleManager);
        mc.setKeybindHandler(keybindHandler);
    }

    public static void injectDependencies(GuiIngame guiIngame) {
        guiIngame.setHudItemManager(hudItemManager);
    }

    public static void injectDependencies(GuiChat guiChat) {
        guiChat.setCommandManager(commandManager);
    }
}
