package dev.jetclient;

import dev.jetclient.command.CommandManager;
import dev.jetclient.gui.GuiScreenManager;
import dev.jetclient.gui.PanelManager;
import dev.jetclient.hud.HudItemManager;
import dev.jetclient.init.*;
import dev.jetclient.keybinds.KeybindHandler;
import dev.jetclient.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;

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
        moduleManager = new ModuleManager(ModuleInitializer.createModules());
        panelManager = new PanelManager(PanelInitializer.createPanels(moduleManager));
        guiScreenManager = new GuiScreenManager(GuiScreenInitializer.createGuiScreens(panelManager));
        keybindHandler = new KeybindHandler(moduleManager, guiScreenManager);
        hudItemManager = new HudItemManager(HudItemInitializer.createHudItems(moduleManager));
        commandManager = new CommandManager(CommandInitializer.createCommands(moduleManager));
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
