package dev.jetclient.init;

import dev.jetclient.hud.HudItem;
import dev.jetclient.hud.items.ModuleList;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class HudItemInitializer {
    public static List<HudItem> createHudItems(ModuleManager moduleManager) {
        List<HudItem> hudItems = new ArrayList<>();
        hudItems.add(new ModuleList(moduleManager));

        return hudItems;
    }
}
