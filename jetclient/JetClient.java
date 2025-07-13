package dev.jetclient;

import dev.jetclient.command.CommandManager;
import dev.jetclient.gui.GuiScreenManager;
import dev.jetclient.gui.screens.modulegui.PanelManager;
import dev.jetclient.overlay.OverlayElementManager;
import dev.jetclient.init.*;
import dev.jetclient.keybind.KeybindHandler;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.utils.DelayCalculator;
import dev.jetclient.utils.MessagePrinter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.RenderGlobal;

public class JetClient {
    private static GuiScreenManager guiScreenManager;
    private static ModuleManager moduleManager;
    private static KeybindHandler keybindHandler;
    private static OverlayElementManager overlayElementManager;
    private static CommandManager commandManager;
    private static PanelManager panelManager;
    private static MessagePrinter messagePrinter;
    private static DelayCalculator delayCalculator;

    private final static String clientName = "Jet";

    public static String getClientName() {
        return clientName;
    }

    public static void initialize() {
        delayCalculator = new DelayCalculator();
        moduleManager = new ModuleManager(ModuleInitializer.createModules(delayCalculator));
        panelManager = new PanelManager(PanelInitializer.createPanels(moduleManager));
        guiScreenManager = new GuiScreenManager(GuiScreenInitializer.createGuiScreens(panelManager));
        keybindHandler = new KeybindHandler(moduleManager, guiScreenManager);
        overlayElementManager = new OverlayElementManager(OverlayElementInitializer.createOverlayElements());
        messagePrinter = new MessagePrinter();
        commandManager = new CommandManager(CommandInitializer.createCommands(moduleManager, messagePrinter));
    }

    public static OverlayElementManager getOverlayElementManager() {
        return overlayElementManager;
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static void injectDependencies(Minecraft mc) {
        mc.setModuleManager(moduleManager);
        mc.setKeybindHandler(keybindHandler);
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
