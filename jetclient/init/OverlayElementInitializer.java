package dev.jetclient.init;

import dev.jetclient.overlay.OverlayElement;
import dev.jetclient.overlay.elements.ModuleList;
import dev.jetclient.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class OverlayElementInitializer {
    public static List<OverlayElement> createOverlayElements(ModuleManager moduleManager) {
        List<OverlayElement> overlayElements = new ArrayList<>();
        overlayElements.add(new ModuleList(moduleManager));

        return overlayElements;
    }
}
