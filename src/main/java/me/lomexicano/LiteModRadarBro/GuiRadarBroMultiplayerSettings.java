package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;

public class GuiRadarBroMultiplayerSettings extends GuiScreen {
    private GuiScreen parentScreen;

    public GuiRadarBroMultiplayerSettings(GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 - 16, "Player Names: " + (LiteModRadarBro.RadarPlayerNames ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 8, "Color Player Names: " + (LiteModRadarBro.RadarColorPlayerNames ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 32, "Player Skin Texture: " + (LiteModRadarBro.RadarUsePlayerSkinTexture ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 56, stringtranslate.translateKey("gui.done")));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled)
            return;
        if (guibutton.id == 0) {
            LiteModRadarBro.RadarPlayerNames = !LiteModRadarBro.RadarPlayerNames;
            ((GuiButton)this.buttonList.get(0)).displayString = "Player Names: " + (LiteModRadarBro.RadarPlayerNames ? "ON" : "OFF");
        }
        if (guibutton.id == 1) {
            LiteModRadarBro.RadarColorPlayerNames = !LiteModRadarBro.RadarColorPlayerNames;
            ((GuiButton)this.buttonList.get(1)).displayString = "Color Player Names: " + (LiteModRadarBro.RadarColorPlayerNames ? "ON" : "OFF");
        }
        if (guibutton.id == 2) {
            LiteModRadarBro.RadarUsePlayerSkinTexture = !LiteModRadarBro.RadarUsePlayerSkinTexture;
            ((GuiButton)this.buttonList.get(2)).displayString = "Player Skin Texture: " + (LiteModRadarBro.RadarUsePlayerSkinTexture ? "ON" : "OFF");
        }
        if (guibutton.id == 3)
            this.mc.displayGuiScreen(this.parentScreen);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }

    public void drawScreen(int i, int j, float f) {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, "RadarBro Multiplayer Settings", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        super.drawScreen(i, j, f);
    }
}
