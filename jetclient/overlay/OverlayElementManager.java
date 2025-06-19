package dev.jetclient.overlay;

import java.util.List;

public class OverlayElementManager {
    private final List<OverlayElement> overlayElements;

    public OverlayElementManager(List<OverlayElement> overlayElements) {
        this.overlayElements = overlayElements;
    }

    public void onRender2D() {
        for (OverlayElement overlayElement : overlayElements) {
            overlayElement.onRender2D();
        }
    }
}
