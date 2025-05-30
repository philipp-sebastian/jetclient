package dev.jetclient.hud;

import java.util.List;

public class HudItemManager {
    private final List<HudItem> hudItems;

    public HudItemManager(List<HudItem> hudItems) {
        this.hudItems = hudItems;
    }

    public void renderItems() {
        for (HudItem drawableItem : hudItems) {
            if (drawableItem.shouldRender()) drawableItem.renderItem();
        }
    }
}
