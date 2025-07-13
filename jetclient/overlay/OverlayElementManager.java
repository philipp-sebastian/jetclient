package dev.jetclient.overlay;

import java.util.List;

public class OverlayElementManager {
    private final List<OverlayElement> overlayElements;

    public OverlayElementManager(List<OverlayElement> overlayElements) {
        this.overlayElements = overlayElements;
    }

    public void toggleOverlayElement(OverlayElement overlayElement) {
        if (overlayElement != null) overlayElement.setActive(!overlayElement.isActive());
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
