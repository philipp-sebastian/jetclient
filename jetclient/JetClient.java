package dev.jetclient;

import dev.jetclient.command.Command;
import dev.jetclient.command.CommandManager;
import dev.jetclient.commands.ChangeKeybind;
import dev.jetclient.commands.ShowCommands;
import dev.jetclient.gui.*;
import dev.jetclient.hud.HudItem;
import dev.jetclient.hud.HudItemManager;
import dev.jetclient.hud.items.ModuleList;
import dev.jetclient.init.*;
import dev.jetclient.keybinds.KeybindHandler;
import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.modules.ChestStealer;
import dev.jetclient.modules.Hud;
import dev.jetclient.modules.Sprint;
import dev.jetclient.modules.Step;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
