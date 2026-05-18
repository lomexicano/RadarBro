package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;

public class GuiRadarBroSettings extends GuiScreen {
    private GuiScreen parentScreen;

    public GuiRadarBroSettings(GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 - 16, "Edit Radar Icons..."));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 8, "Manage Allies/Enemies..."));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 32, "Manage Waypoints..."));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 56, "Radar: " + (LiteModRadarBro.RadarEnabled ? "Enabled" : "Disabled")));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 80, "GUI Settings..."));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 104, "Multiplayer Settings..."));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height / 4 + 128, stringtranslate.translateKey("gui.done")));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled)
            return;
        if (guibutton.id == 0)
            this.mc.displayGuiScreen(new GuiRadarBroIconSettings(this));
        if (guibutton.id == 1)
            this.mc.displayGuiScreen(new GuiAllyEnemyManager(this));
        if (guibutton.id == 2)
            this.mc.displayGuiScreen(new GuiWaypointManager(this));
        if (guibutton.id == 3) {
            LiteModRadarBro.RadarEnabled = !LiteModRadarBro.RadarEnabled;
            ((GuiButton)this.buttonList.get(3)).displayString = "Radar: " + (LiteModRadarBro.RadarEnabled ? "Enabled" : "Disabled");
        }
        if (guibutton.id == 4)
            this.mc.displayGuiScreen(new GuiRadarBroGUISettings(this));
        if (guibutton.id == 5)
            this.mc.displayGuiScreen(new GuiRadarBroMultiplayerSettings(this));
        if (guibutton.id == 6)
            this.mc.displayGuiScreen(this.parentScreen);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }

    public void drawScreen(int i, int j, float f) {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, "RadarBro Settings", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        super.drawScreen(i, j, f);
    }
}
