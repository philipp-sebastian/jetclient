package dev.jetclient;

import dev.jetclient.command.CommandManager;
import dev.jetclient.config.ConfigFile;
import dev.jetclient.config.ConfigHandler;
import dev.jetclient.handler.KeyHandler;
import dev.jetclient.init.CommandInitializer;
import dev.jetclient.init.ModuleInitializer;
import dev.jetclient.init.OverlayElementInitializer;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.overlay.OverlayElementManager;
import dev.jetclient.gui.modulegui.PanelManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.RenderGlobal;

public class JetClient {
    private static ModuleManager moduleManager;
    private static KeyHandler keyHandler;
    private static OverlayElementManager overlayElementManager;
    private static CommandManager commandManager;
    private static PanelManager panelManager;
    private static ConfigHandler configHandler;

    private final static String clientName = "Jet";

    public static String getClientName() {
        return clientName;
    }

    public static void initialize() {
        configHandler = new ConfigHandler(new ConfigFile("config.txt"));
        moduleManager = new ModuleManager(ModuleInitializer.createModules(), configHandler);
        panelManager = new PanelManager(moduleManager);
        keyHandler = new KeyHandler(moduleManager);
        overlayElementManager = new OverlayElementManager(OverlayElementInitializer.createOverlayElements(), configHandler);
        commandManager = new CommandManager(CommandInitializer.createCommands(moduleManager));
    }

    public static OverlayElementManager getOverlayElementManager() {
        return overlayElementManager;
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static PanelManager getPanelManager() { return panelManager; }

    public static ConfigHandler getConfigHandler() { return configHandler; }

    public static void injectDependencies(Minecraft mc) {
        mc.setModuleManager(moduleManager);
        mc.setKeybindHandler(keyHandler);
    }

    public static void injectDependencies(GuiIngame guiIngame) {
        guiIngame.setOverlayElementManager(overlayElementManager);
        guiIngame.setModuleManager(moduleManager);
    }

    public static void injectDependencies(GuiChat guiChat) {
        guiChat.setCommandManager(commandManager);
    }

    public static void injectDependencies(RenderGlobal renderGlobal) { renderGlobal.setModuleManager(moduleManager); }
}
