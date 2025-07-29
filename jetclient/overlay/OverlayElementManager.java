package dev.jetclient.overlay;

import dev.jetclient.config.ConfigHandler;

import java.util.List;

public class OverlayElementManager {
    private final List<OverlayElement> overlayElements;
    private final ConfigHandler configHandler;

    public OverlayElementManager(List<OverlayElement> overlayElements, ConfigHandler configHandler) {
        this.overlayElements = overlayElements;
        this.configHandler = configHandler;
        initOverlayElementState();
    }

    public void initOverlayElementState() {
        for (OverlayElement overlayElement : overlayElements) {
            overlayElement.setActive(configHandler.getOverlayElementEnabled(overlayElement.getName()));
        }
    }

    public void onShutdown() {
        for (OverlayElement overlayElement : overlayElements) {
            configHandler.setOverlayElementEnabled(overlayElement.getName(), overlayElement.isActive());
        }
    }

    public List<OverlayElement> getOverlayElements() {
        return this.overlayElements;
    }

    public void onRender2D() {
        for (OverlayElement overlayElement : overlayElements) {
            if (overlayElement.isActive()) overlayElement.onRender2D();
        }
    }
}
