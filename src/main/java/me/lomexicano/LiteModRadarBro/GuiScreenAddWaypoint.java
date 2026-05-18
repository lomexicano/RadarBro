package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;

public class GuiScreenAddWaypoint extends GuiScreen {
    private GuiScreen parentGui;

    private GuiTextField waypointX;

    private GuiTextField waypointY;

    private GuiTextField waypointZ;

    private GuiTextField waypointName;

    private WaypointNBTStorage waypointNBTStorage;

    public GuiScreenAddWaypoint(GuiScreen par1GuiScreen, WaypointNBTStorage par2ServerNBTStorage) {
        this.parentGui = par1GuiScreen;
        this.waypointNBTStorage = par2ServerNBTStorage;
    }

    public void updateScreen() {
        this.waypointName.updateCursorCounter();
        this.waypointX.updateCursorCounter();
        this.waypointY.updateCursorCounter();
        this.waypointZ.updateCursorCounter();
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, stringtranslate.translateKey("addServer.add")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, stringtranslate.translateKey("gui.cancel")));
        this.waypointName = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 76, 200, 20);
        this.waypointName.setFocused(true);
        this.waypointName.setText(this.waypointNBTStorage.name);
        this.waypointX = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 116, 60, 20);
        this.waypointY = new GuiTextField(this.fontRenderer, this.width / 2 - 30, 116, 60, 20);
        this.waypointZ = new GuiTextField(this.fontRenderer, this.width / 2 + 40, 116, 60, 20);
        this.waypointX.setMaxStringLength(128);
        this.waypointY.setMaxStringLength(128);
        this.waypointZ.setMaxStringLength(128);
        try {
            String[] coordinates = this.waypointNBTStorage.coordinates.split(",");
            String xCoordinate = coordinates[0];
            String yCoordinate = coordinates[1];
            String zCoordinate = coordinates[2];
            this.waypointX.setText(xCoordinate);
            this.waypointY.setText(yCoordinate);
            this.waypointZ.setText(zCoordinate);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.waypointX.setText(Double.toString((int)this.mc.thePlayer.posX));
            this.waypointY.setText(Double.toString((int)this.mc.thePlayer.posY));
            this.waypointZ.setText(Double.toString((int)this.mc.thePlayer.posZ));
        }
        ((GuiButton)this.buttonList.get(0)).enabled = (this.waypointX.getText().length() > 0 && this.waypointY.getText().length() > 0 && this.waypointZ.getText().length() > 0 && this.waypointName.getText().length() > 0);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (!par1GuiButton.enabled)
            return;
        if (par1GuiButton.id == 1) {
            this.parentGui.confirmClicked(false, 0);
        } else if (par1GuiButton.id == 0) {
            this.waypointNBTStorage.name = this.waypointName.getText();
            this.waypointNBTStorage.coordinates = this.waypointX.getText() + "," + this.waypointY.getText() + "," + this.waypointZ.getText();
            this.parentGui.confirmClicked(true, 0);
        }
    }

    protected void keyTyped(char par1, int par2) {
        this.waypointName.textboxKeyTyped(par1, par2);
        this.waypointX.textboxKeyTyped(par1, par2);
        this.waypointY.textboxKeyTyped(par1, par2);
        this.waypointZ.textboxKeyTyped(par1, par2);
        if (par1 == '\t')
            if (this.waypointName.isFocused()) {
                this.waypointName.setFocused(false);
                this.waypointX.setFocused(true);
                this.waypointY.setFocused(true);
                this.waypointZ.setFocused(true);
            } else {
                this.waypointName.setFocused(true);
                this.waypointX.setFocused(false);
                this.waypointY.setFocused(false);
                this.waypointZ.setFocused(false);
            }
        if (par1 == '\r')
            actionPerformed((GuiButton) this.buttonList.get(0));
        ((GuiButton)this.buttonList.get(0)).enabled = (this.waypointX.getText().length() > 0 && this.waypointY.getText().length() > 0 && this.waypointZ.getText().length() > 0 && this.waypointName.getText().length() > 0);
        if (((GuiButton)this.buttonList.get(0)).enabled) {
            String s = this.waypointX.getText().trim();
            String[] as = s.split(":");
            if (as.length > 2)
                ((GuiButton)this.buttonList.get(0)).enabled = false;
        }
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.waypointX.mouseClicked(par1, par2, par3);
        this.waypointY.mouseClicked(par1, par2, par3);
        this.waypointZ.mouseClicked(par1, par2, par3);
        this.waypointName.mouseClicked(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3) {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, stringtranslate.translateKey("Edit Waypoint Info"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
        drawString(this.fontRenderer, stringtranslate.translateKey("Waypoint Name"), this.width / 2 - 100, 63, 10526880);
        drawString(this.fontRenderer, stringtranslate.translateKey("Waypoint Coordinates"), this.width / 2 - 100, 104, 10526880);
        this.waypointName.drawTextBox();
        this.waypointX.drawTextBox();
        this.waypointY.drawTextBox();
        this.waypointZ.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
