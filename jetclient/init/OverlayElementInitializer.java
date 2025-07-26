package dev.jetclient.init;

import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.overlay.OverlayElement;
import dev.jetclient.overlay.elements.ModuleList;

import java.util.ArrayList;
import java.util.List;

public class OverlayElementInitializer {
    public static List<OverlayElement> createOverlayElements(List<Module> modules) {
        List<OverlayElement> overlayElements = new ArrayList<>();
        overlayElements.add(new ModuleList(modules));

        return overlayElements;
    }
}
