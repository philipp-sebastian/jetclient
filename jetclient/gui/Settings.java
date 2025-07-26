package dev.jetclient.gui;

import dev.jetclient.JetClient;
import dev.jetclient.overlay.OverlayElement;
import dev.jetclient.overlay.OverlayElementManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.List;

public class Settings extends GuiScreen {
    private final GuiScreen parentScreen;
    private final OverlayElementManager overlayElementManager;

    private static final int DONE_BUTTON_ID = 999;

    public Settings(GuiScreen parentScreenIn, OverlayElementManager overlayElementManager) {
        this.parentScreen = parentScreenIn;
        this.overlayElementManager = overlayElementManager;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();

        int index = 0;
        for (OverlayElement overlayElement : overlayElementManager.getOverlayElements()) {
            this.buttonList.add(new GuiButton(index, this.width / 2 - 100, this.height / 4 + index * 24, 200, 20, getButtonText(overlayElement)));
            index++;
        }
        this.buttonList.add(new GuiButton(DONE_BUTTON_ID, this.width / 2 - 100, this.height / 6 + 168, 200, 20, "Done"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, JetClient.getClientName() + " Settings", this.width / 2, 20, 0xFFFFFFFF);

        List<OverlayElement> elements = overlayElementManager.getOverlayElements();
        for (int i = 0; i < elements.size(); i++) {
            this.buttonList.get(i).displayString = getButtonText(elements.get(i));
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == DONE_BUTTON_ID) {
            this.mc.displayGuiScreen(parentScreen);
            return;
        }

        List<OverlayElement> overlayElements = overlayElementManager.getOverlayElements();
        if (button.id >= 0 && button.id < overlayElements.size()) {
            OverlayElement overlayElement = overlayElements.get(button.id);
            if (overlayElement != null) {
                overlayElement.setActive(!overlayElement.isActive());
            }
        }
    }

    private String getButtonText(OverlayElement overlayElement) {
        return overlayElement.getName() + ": " + ((overlayElement.isActive()) ? "On" : "Off");
    }
}
