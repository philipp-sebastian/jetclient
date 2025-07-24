package dev.jetclient.utils;

import dev.jetclient.JetClient;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class MessagePrinter {

    public static void printMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(JetClient.getClientName() + " >> " + message));
    }
}
