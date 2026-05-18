package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;

public class GuiScreenAddAllyEnemy extends GuiScreen {
    private GuiScreen parentGui;

    private GuiTextField playerUsername;

    private int mode;

    public GuiScreenAddAllyEnemy(GuiScreen par1GuiScreen, int mode) {
        this.parentGui = par1GuiScreen;
        this.mode = mode;
    }

    public void updateScreen() {
        this.playerUsername.updateCursorCounter();
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, stringtranslate.translateKey("addServer.add")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, stringtranslate.translateKey("gui.cancel")));
        this.playerUsername = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 76, 200, 20);
        this.playerUsername.setFocused(true);
        this.playerUsername.setText("");
        this.playerUsername.setMaxStringLength(16);
        ((GuiButton)this.buttonList.get(0)).enabled = (this.playerUsername.getText().length() > 0);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (!par1GuiButton.enabled)
            return;
        if (par1GuiButton.id == 1) {
            this.mc.displayGuiScreen(this.parentGui);
        } else if (par1GuiButton.id == 0) {
            String playerUsername = this.playerUsername.getText();
            if (this.mode == 0) {
                if (!LiteModRadarBro.AllyList.contains(playerUsername)) {
                    LiteModRadarBro.AllyList.add(playerUsername);
                    LiteModRadarBro.saveAllyList();
                    this.mc.displayGuiScreen(this.parentGui);
                }
            } else if (!LiteModRadarBro.EnemyList.contains(playerUsername)) {
                LiteModRadarBro.EnemyList.add(playerUsername);
                LiteModRadarBro.saveEnemyList();
                this.mc.displayGuiScreen(this.parentGui);
            }
        }
    }

    protected void keyTyped(char par1, int par2) {
        this.playerUsername.textboxKeyTyped(par1, par2);
        if (par1 == '\t')
            if (this.playerUsername.isFocused()) {
                this.playerUsername.setFocused(false);
            } else {
                this.playerUsername.setFocused(true);
            }
        if (par1 == '\r')
            actionPerformed((GuiButton) this.buttonList.get(0));
        ((GuiButton)this.buttonList.get(0)).enabled = (this.playerUsername.getText().length() > 0);
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.playerUsername.mouseClicked(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3) {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, stringtranslate.translateKey("Add RadarBro " + ((this.mode == 0) ? "Ally" : "Enemy")), this.width / 2, this.height / 4 - 60 + 20, 16777215);
        drawString(this.fontRenderer, stringtranslate.translateKey("Player username"), this.width / 2 - 100, 63, 10526880);
        this.playerUsername.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
