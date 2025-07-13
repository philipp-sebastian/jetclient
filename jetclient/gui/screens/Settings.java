package dev.jetclient.gui.screens;

import dev.jetclient.JetClient;
import dev.jetclient.overlay.OverlayElementManager;
import dev.jetclient.overlay.elements.ModuleList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class Settings extends GuiScreen {
    private final GuiScreen parentScreen;
    private final OverlayElementManager overlayElementManager;
    private final ModuleList moduleList;

    public Settings(GuiScreen parentScreenIn, OverlayElementManager overlayElementManager) {
        this.parentScreen = parentScreenIn;
        this.overlayElementManager = overlayElementManager;
        this.moduleList = overlayElementManager.getOverlayElement(ModuleList.class);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24, getModuleListButtonText()));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Done"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, JetClient.getClientName() + " Settings", this.width / 2, 20, 16777215);
        this.buttonList.get(0).displayString = getModuleListButtonText();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0 && moduleList != null) overlayElementManager.toggleOverlayElement(moduleList);
        if (button.id == 1) this.mc.displayGuiScreen(parentScreen);
        super.actionPerformed(button);
    }

    private String getModuleListButtonText() {
        return "Module List: " + ((overlayElementManager.isOverlayElementActive(moduleList)) ? "true" : "false");
    }
}
