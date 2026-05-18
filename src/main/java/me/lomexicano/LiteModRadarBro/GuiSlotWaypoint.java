package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiSlot;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiSlotWaypoint extends GuiSlot {
    final GuiWaypointManager parentGui;

    public GuiSlotWaypoint(GuiWaypointManager par1GuiMultiplayer) {
        super(par1GuiMultiplayer.mc, par1GuiMultiplayer.width, par1GuiMultiplayer.height, 32, par1GuiMultiplayer.height - 64, 36);
        this.parentGui = par1GuiMultiplayer;
    }

    protected int getSize() {
        return GuiWaypointManager.getWaypointList(this.parentGui).size();
    }

    protected void elementClicked(int par1, boolean par2) {
        GuiWaypointManager.setSelectedWaypoint(this.parentGui, par1);
        boolean flag = (GuiWaypointManager.getSelectedWaypoint(this.parentGui) >= 0 && GuiWaypointManager.getSelectedWaypoint(this.parentGui) < getSize());
        (GuiWaypointManager.getButtonEdit(this.parentGui)).enabled = flag;
        (GuiWaypointManager.getButtonToggle(this.parentGui)).enabled = flag;
        (GuiWaypointManager.getButtonDelete(this.parentGui)).enabled = flag;
        if (par2 && flag) {
            this.parentGui.updateToggledState(true);
        } else {
            this.parentGui.updateToggledState(false);
        }
    }

    protected boolean isSelected(int par1) {
        return (par1 == GuiWaypointManager.getSelectedWaypoint(this.parentGui));
    }

    protected int getContentHeight() {
        return GuiWaypointManager.getWaypointList(this.parentGui).size() * 36;
    }

    protected void drawBackground() {
        this.parentGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
        String enabledState;
        WaypointNBTStorage waypointnbtstorage = (WaypointNBTStorage) GuiWaypointManager.getWaypointList(this.parentGui).get(par1);
        if (waypointnbtstorage.enabled.equals("true")) {
            enabledState = "§2Enabled";
        } else {
            enabledState = "§fDisabled";
        }
        String[] coords = waypointnbtstorage.coordinates.split(",");
        int distance = (int)this.parentGui.mc.thePlayer.getDistance(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
        this.parentGui.drawString(this.parentGui.fontRenderer, waypointnbtstorage.name, par2 + 2, par3 + 1, 16777215);
        this.parentGui.drawString(this.parentGui.fontRenderer, enabledState, par2 + 2, par3 + 12, 8421504);
        this.parentGui.drawString(this.parentGui.fontRenderer, distance + " blocks away", par2 + 100, par3 + 12, 8421504);
        this.parentGui.drawString(this.parentGui.fontRenderer, "(" + waypointnbtstorage.coordinates + ")", par2 + 2, par3 + 12 + 11, 3158064);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i = 0;
        int j = 0;
        String s = "";
        byte byte0 = 4;
        if (this.mouseX >= par2 + 100 - byte0 && this.mouseY >= par3 + 8 && this.mouseX <= par2 + 160 + 10 + byte0 && this.mouseY <= par3 + 16 + byte0)
            GuiWaypointManager.setTooltipText(this.parentGui, "(~" + Math.round(distance / 4.3D) + " seconds of walking)");
    }
}
