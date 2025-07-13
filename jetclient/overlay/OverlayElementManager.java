package dev.jetclient.overlay;

import java.util.List;

public class OverlayElementManager {
    private final List<OverlayElement> overlayElements;

    public OverlayElementManager(List<OverlayElement> overlayElements) {
        this.overlayElements = overlayElements;
    }

    public void toggleOverlayElement(OverlayElement overlayElement) {
        if (overlayElement != null) overlayElement.setActive(!overlayElement.getActive());
    }

    public <T extends OverlayElement> T getOverlayElement(Class<T> clazz) {
        for (OverlayElement element : overlayElements) {
            if (clazz.isInstance(element)) {
                return clazz.cast(element);
            }
        }
        return null;
    }

    public boolean isOverlayElementActive(OverlayElement overlayElement) {
        return overlayElement != null && overlayElement.getActive();
    }

    public void onRender2D() {
        for (OverlayElement overlayElement : overlayElements) {
            if (overlayElement.getActive()) overlayElement.onRender2D();
        }
    }
}
