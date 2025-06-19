package dev.jetclient.gui.screens.modulegui;

import dev.jetclient.module.Category;
import dev.jetclient.module.Module;
import dev.jetclient.module.ModuleManager;
import dev.jetclient.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.util.List;

public class Panel {
    private static final int WIDTH = 100;
    private static final int ENTRY_HEIGHT = 10;
    private static final int PADDING = 3;
    private static final int BACKGROUND_COLOR = 0xAA000000;
    private static final int ACTIVE_COLOR = 0x3EE900;
    private static final int INACTIVE_COLOR = 0xFFFFFFFF;

    private final ModuleManager moduleManager;
    private final Category category;
    private int x, y;

    private final List<Module> modules;

    private boolean dragging = false;
    private int dragOffsetX, dragOffsetY;

    private boolean showModules = false;
    private boolean showSettings = false;
    private Module selectedModule = null;

    public Panel(ModuleManager moduleManager, Category category, int x, int y) {
        this.moduleManager = moduleManager;
        this.category = category;
        this.x = x;
        this.y = y;

        this.modules = moduleManager.getModulesByCategory(category);
    }

    public void drawScreen(int mouseX, int mouseY) {
        updateDrag(mouseX, mouseY);
        drawPanelBackground();
        drawCategoryTitle();

        if (!showModules) return;

        drawModules();

        if (showSettings && selectedModule != null) {
            drawModuleSettings(selectedModule, x + WIDTH + 5, getModuleHeight(selectedModule));
        }
    }

    private void drawPanelBackground() {
        int height = ENTRY_HEIGHT + PADDING + (showModules ? modules.size() * ENTRY_HEIGHT : 0);
        drawBox(x, y, WIDTH, height, BACKGROUND_COLOR);
    }

    private void drawCategoryTitle() {
        drawText(category.name(), x + PADDING, y + PADDING, 0xFFFFFFFF);
    }

    private void drawModules() {
        int currentY = y + PADDING + ENTRY_HEIGHT;

        for (Module m : modules) {
            int color = moduleManager.isModuleActive(m.getClass()) ? ACTIVE_COLOR : INACTIVE_COLOR;
            drawText(m.getName(), x + PADDING, currentY, color);
            currentY += ENTRY_HEIGHT;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (handleHeaderClick(mouseX, mouseY, mouseButton)) return;
        if (!showModules) return;
        if (handleModuleClick(mouseX, mouseY, mouseButton)) return;
        handleSettingClick(mouseX, mouseY);
    }

    private boolean handleHeaderClick(int mouseX, int mouseY, int mouseButton) {
        if (isInBox(mouseX, mouseY, x, y + PADDING, WIDTH, ENTRY_HEIGHT)) {
            if (mouseButton == 0) {
                dragging = true;
                dragOffsetX = mouseX - x;
                dragOffsetY = mouseY - y;
            } else if (mouseButton == 1) {
                showModules = !showModules;
                selectedModule = null;
                showSettings = false;
            }
            return true;
        }
        return false;
    }

    private boolean handleModuleClick(int mouseX, int mouseY, int mouseButton) {
        int currentY = y + PADDING + ENTRY_HEIGHT;
        for (Module m : modules) {
            if (isInBox(mouseX, mouseY, x, currentY, WIDTH, ENTRY_HEIGHT)) {
                if (mouseButton == 0) {
                    moduleManager.toggleModule(m);
                } else if (mouseButton == 1 && !m.getSettings().isEmpty()) {
                    if (selectedModule == null || !selectedModule.getName().equals(m.getName())) {
                        selectedModule = m;
                        showSettings = true;
                    } else {
                        selectedModule = null;
                        showSettings = false;
                    }
                }
                return true;
            }
            currentY += ENTRY_HEIGHT;
        }
        return false;
    }

    //old
    private void handleSettingClick(int mouseX, int mouseY) {
        if (!showSettings || selectedModule == null) return;

        int settingY = y + PADDING + ENTRY_HEIGHT * (modules.indexOf(selectedModule) + 1) + PADDING;
        for (Setting setting : selectedModule.getSettings().values()) {
            if (isInBox(mouseX, mouseY, x + WIDTH + 5, settingY, WIDTH, (setting.getHeight() * Panel.getEntryHeight()))) {
                setting.handleClick(mouseX, mouseY);
                return;
            }
            settingY += (setting.getHeight() * Panel.getEntryHeight());
        }
    }

    private void updateDrag(int mouseX, int mouseY) {
        if (dragging) {
            this.x = mouseX - dragOffsetX;
            this.y = mouseY - dragOffsetY;
        }
    }

    private boolean isInBox(int mouseX, int mouseY, int x, int y, int width, int height) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) dragging = false;
    }

    private void drawBox(int x, int y, int width, int heigth, int color) {
        Gui.drawRect(x, y, x + width, y + heigth, color);
    }

    private void drawText(String text, int x, int y, int color) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, color);
    }

    private void drawModuleSettings(Module selectedModule, int x, int y) {
        int boxHeight = 0;
        for (Setting setting : selectedModule.getSettings().values()) {
            boxHeight += (setting.getHeight() * Panel.getEntryHeight());
        }
        drawBox(x, y, WIDTH, boxHeight + PADDING, BACKGROUND_COLOR);

        int yOffset = PADDING;
        for (Setting setting : selectedModule.getSettings().values()) {
            setting.draw(x, y + yOffset);
            yOffset += (setting.getHeight() * Panel.getEntryHeight());
        }
    }

    private int getModuleHeight(Module m) {
        int index = modules.indexOf(m);
        return this.y + PADDING + ENTRY_HEIGHT * (index + 1);
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getEntryHeight() {
        return ENTRY_HEIGHT;
    }

    public static int getPadding() {
        return PADDING;
    }

    public static int getActiveColor() {
        return ACTIVE_COLOR;
    }

    public static int getInactiveColor() {
        return INACTIVE_COLOR;
    }
}
