package dev.jetclient.gui.screens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;

public class AltLogin extends GuiScreen {

    private GuiTextField username;
    private GuiTextField password;

    @Override
    public void initGui() {
        this.username = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 20, 200, 20);
        this.password = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 2 + 10, 200, 20);

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + 40, 200, 20, "Login"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        this.password.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.username.textboxKeyTyped(typedChar, keyCode);
        this.password.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.username.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            System.out.println(this.username.getText() + ":" + this.password.getText());
        }
        //TODO: Not working anymore because login works only through microsoft api
        super.actionPerformed(button);
    }
}
