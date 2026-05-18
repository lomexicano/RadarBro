package me.lomexicano.LiteModRadarBro;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.MouseHelper;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import java.awt.Cursor;
public class GuiRepositionRadarBro extends GuiScreen {
    public MouseHelper mouseHelper;

    public static Minecraft mc;

    public GuiRepositionRadarBro(Minecraft minecraft) {
        mc = minecraft;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        LiteModRadarBro.saveOptions();
    }

    public void drawScreen(int i, int j, float f) {
        if (dragging || isDraggable) {
            mc.mcCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        } else {
            mc.mcCanvas.setCursor(Cursor.getDefaultCursor());
        }

        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int i3 = sr.getScaledWidth();
        drawCenteredString(this.fontRenderer, "Click and drag the radar to reposition", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        drawString(this.fontRenderer, "Press Esc when you're finished", this.width / 2 - 210, this.height / 4 - 60 + 2, 10526880);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        handleMouseDrag();
        xOffset = xDisplacement + xEndDisplacement;
        yOffset = yDisplacement + yEndDisplacement;
        super.drawScreen(i, j, f);
    }

    private void handleMouseDrag() {
        this.mouseHelper = new MouseHelper(mc.mcCanvas, null);
        if (!Mouse.isButtonDown(0)) {
            dragging = false;
            xEndDisplacement += xDisplacement;
            yEndDisplacement += yDisplacement;
            xDisplacement = 0;
            yDisplacement = 0;
        }
        if (dragging) {
            int x = Mouse.getEventX() * this.width / mc.displayWidth;
            int y = this.height - Mouse.getEventY() * this.height / mc.displayHeight - 1;
            xDisplacement = x - xPos;
            yDisplacement = y - yPos;
        }
    }

    protected void mouseMovedOrUp(int i, int j, int k) {
        if (i >= this.width - 135 + xOffset && i <= this.width + xOffset && j >= yOffset && j <= 130 + yOffset) {
            isDraggable = true;
        } else {
            isDraggable = false;
        }
    }

    protected void mouseClicked(int i, int j, int k) {
        if (i >= this.width - 135 + xOffset && i <= this.width + xOffset && j >= yOffset && j <= 130 + yOffset) {
            xPos = Mouse.getEventX() * this.width / mc.displayWidth;
            yPos = this.height - Mouse.getEventY() * this.height / mc.displayHeight - 1;
            dragging = true;
        }
        super.mouseClicked(i, j, k);
    }

    public static boolean isDraggable = false;

    public static boolean dragging = false;

    public static int xPos;

    public static int yPos;

    public static int xDisplacement;

    public static int yDisplacement;

    public static int xEndDisplacement;

    public static int yEndDisplacement;

    public static int xOffset;

    public static int yOffset;
}
