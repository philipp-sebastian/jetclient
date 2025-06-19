package dev.jetclient.utils;

import org.lwjgl.opengl.GL11;

public class GLColor {
    private final float r, g, b, a;

    public GLColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public void apply() {
        GL11.glColor4f(r, g, b, a);
    }
}
