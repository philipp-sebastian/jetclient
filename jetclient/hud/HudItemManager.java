package dev.jetclient.hud;

import dev.jetclient.hud.items.ModuleList;

import java.util.ArrayList;
import java.util.List;

public class HudItemManager {
    private List<HudItem> hudItems;

    public HudItemManager(List<HudItem> hudItems) {
        this.hudItems = hudItems;
    }

    public void renderItems() {
        for (HudItem drawableItem : hudItems) {
            drawableItem.renderItem();
        }
    }
}
