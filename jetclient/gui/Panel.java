package dev.jetclient.gui;

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

    private ModuleManager moduleManager;
    private List<Module> modules;
    private Category category;
    private int x, y;

    private boolean dragging = false;
    private int dragOffsetX, dragOffsetY;

    private boolean showModules = false;
    private boolean showSettings = false;
    private Module selectedModule = null;

    public Panel(ModuleManager moduleManager, List<Module> modules, Category category, int x, int y) {
        this.moduleManager = moduleManager;
        this.modules = modules;
        this.category = category;
        this.x = x;
        this.y = y;
    }

    public void drawScreen(int mouseX, int mouseY) {
        if (dragging) {
            this.x = mouseX - dragOffsetX;
            this.y = mouseY - dragOffsetY;
        }

        int panelHeight = ENTRY_HEIGHT + PADDING;
        if (showModules) {
            panelHeight += ENTRY_HEIGHT * modules.size();
        }

        drawBox(this.x, this.y, WIDTH, panelHeight, BACKGROUND_COLOR);
        drawText(this.category.name(), this.x + PADDING, this.y + PADDING, 0xFFFFFFFF);

        if (!showModules) return;

        int currentY = this.y + PADDING + ENTRY_HEIGHT;
        for (Module m : modules) {
            int color = moduleManager.getActiveModules().contains(m) ? ACTIVE_COLOR : INACTIVE_COLOR;
            drawText(m.getName(), this.x + PADDING, currentY, color);
            currentY += ENTRY_HEIGHT;
        }

        if (showSettings && selectedModule != null) {
            int height = getModuleHeight(selectedModule);
            drawModuleSettings(selectedModule, this.x + WIDTH + 5, height);
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int headerY = this.y + PADDING;
        if (isInBox(mouseX, mouseY, this.x, headerY, WIDTH, ENTRY_HEIGHT)) {
            if (mouseButton == 0) {
                dragging = true;
                dragOffsetX = mouseX - x;
                dragOffsetY = mouseY - y;
            } else if (mouseButton == 1) {
                showModules = !showModules;
                selectedModule = null;
                showSettings = false;
            }
            return;
        }

        if (!showModules) return;

        int currentY = this.y + PADDING + ENTRY_HEIGHT;

        for (Module m : modules) {
            if (isInBox(mouseX, mouseY, this.x, currentY, WIDTH, ENTRY_HEIGHT)) {
                if (mouseButton == 0) {
                    moduleManager.toggleModule(m);
                    return;
                } else if (mouseButton == 1 && !m.getSettings().isEmpty()) {
                    if (selectedModule != null && selectedModule.getName().equals(m.getName())) {
                        selectedModule = null;
                        showSettings = false;
                        return;
                    }
                    selectedModule = m;
                    showSettings = true;
                    return;
                }
            }
            currentY += ENTRY_HEIGHT;
        }

        if (showSettings && selectedModule != null) {
            int settingsY = this.y + PADDING + ENTRY_HEIGHT + (ENTRY_HEIGHT * modules.indexOf(selectedModule) + 1);
            for (Setting s : selectedModule.getSettings().values()) {
                if (isInBox(mouseX, mouseY, this.x + WIDTH + 5, settingsY, WIDTH, ENTRY_HEIGHT)) {
                    s.handleClick(mouseX, mouseY);
                    return;
                }
                settingsY += ENTRY_HEIGHT;
            }
        }
    }

    private boolean isInBox(int mouseX, int mouseY, int x, int y, int width, int height) {
        return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) {
            dragging = false;
        }
    }

    private void drawBox(int x, int y, int width, int heigth, int color) {
        Gui.drawRect(x, y, x + width, y + heigth, color);
    }

    private void drawText(String text, int x, int y, int color) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, color);
    }

    private void drawModuleSettings(Module selectedModule, int x, int y) {
        drawBox(x, y, WIDTH, ENTRY_HEIGHT * selectedModule.getSettings().size() + PADDING, BACKGROUND_COLOR);

        int yOffset = PADDING;
        for (Setting s : selectedModule.getSettings().values()) {
            s.draw(x, y + yOffset);
            yOffset += ENTRY_HEIGHT;
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
