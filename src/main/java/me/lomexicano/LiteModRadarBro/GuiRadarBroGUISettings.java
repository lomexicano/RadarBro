package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;

public class GuiRadarBroGUISettings extends GuiScreen {
    private GuiScreen parentScreen;

    public GuiRadarBroGUISettings(GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 - 16, "Auto Rotate: " + (LiteModRadarBro.RadarAutoRotate ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 8, "Coordinates: " + (LiteModRadarBro.RadarCoordinates ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 32, "Terrain: " + (LiteModRadarBro.RadarTerrain ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 56, "Reposition Radar..."));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 80, stringtranslate.translateKey("gui.done")));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled)
            return;
        if (guibutton.id == 0) {
            LiteModRadarBro.RadarAutoRotate = !LiteModRadarBro.RadarAutoRotate;
            ((GuiButton)this.buttonList.get(0)).displayString = "Auto Rotate: " + (LiteModRadarBro.RadarAutoRotate ? "ON" : "OFF");
        }
        if (guibutton.id == 1) {
            LiteModRadarBro.RadarCoordinates = !LiteModRadarBro.RadarCoordinates;
            ((GuiButton)this.buttonList.get(1)).displayString = "Coordinates: " + (LiteModRadarBro.RadarCoordinates ? "ON" : "OFF");
        }
        if (guibutton.id == 2) {
            LiteModRadarBro.RadarTerrain = !LiteModRadarBro.RadarTerrain;
            ((GuiButton)this.buttonList.get(2)).displayString = "Terrain: " + (LiteModRadarBro.RadarTerrain ? "ON" : "OFF");
        }
        if (guibutton.id == 3)
            this.mc.displayGuiScreen(new GuiRepositionRadarBro(this.mc));
        if (guibutton.id == 4)
            this.mc.displayGuiScreen(this.parentScreen);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }

    public void drawScreen(int i, int j, float f) {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, "RadarBro GUI Settings", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        super.drawScreen(i, j, f);
    }
}
