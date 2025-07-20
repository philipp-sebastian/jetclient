package dev.jetclient.init;

import dev.jetclient.overlay.OverlayElement;
import dev.jetclient.overlay.elements.ModuleList;

import java.util.ArrayList;
import java.util.List;

public class OverlayElementInitializer {
    public static List<OverlayElement> createOverlayElements() {
        List<OverlayElement> overlayElements = new ArrayList<>();
        overlayElements.add(new ModuleList());

        return overlayElements;
    }
}
