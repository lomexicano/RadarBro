package me.lomexicano.LiteModRadarBro;

import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiSlot;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiSlotAllyEnemy extends GuiSlot {
    final GuiAllyEnemyManager parentGui;

    public GuiSlotAllyEnemy(GuiAllyEnemyManager par1GuiAllyEnemyManager) {
        super(Minecraft.getMinecraft(), par1GuiAllyEnemyManager.width, par1GuiAllyEnemyManager.height, 32, par1GuiAllyEnemyManager.height - 65 + 4, 18);
        this.parentGui = par1GuiAllyEnemyManager;
    }

    protected int getSize() {
        return GuiAllyEnemyManager.getAllyList(this.parentGui).size();
    }

    protected void elementClicked(int par1, boolean par2) {
        GuiAllyEnemyManager.setSelectedUsername(this.parentGui, par1);
        boolean flag = (GuiAllyEnemyManager.getSelectedUsername(this.parentGui) >= 0 && GuiAllyEnemyManager.getSelectedUsername(this.parentGui) < getSize());
        (GuiAllyEnemyManager.getButtonDelete(this.parentGui)).enabled = flag;
    }

    protected boolean isSelected(int par1) {
        return (par1 == GuiAllyEnemyManager.getSelectedUsername(this.parentGui));
    }

    protected int getContentHeight() {
        return getSize() * 18;
    }

    protected void drawBackground() {
        this.parentGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
        String playerUsername = (String) GuiAllyEnemyManager.getAllyList(this.parentGui).get(par1);
        this.parentGui.drawCenteredString(this.parentGui.fontRenderer, (GuiAllyEnemyManager.currentListType == 0) ? ("§a" + playerUsername) : ("§c" + playerUsername), this.parentGui.width / 2, par3 + 1, 16777215);
        drawPlayerHeadImage(GuiRadarBro.generatePlayerHeadImage(playerUsername), this.parentGui.width / 2 - 60, par3 + 1);
    }

    public void drawPlayerHeadImage(BufferedImage bi, int x, int y) {
        this.parentGui.mc.renderEngine.setupTexture(bi, 1111111194);
        GL11.glBindTexture(3553, 1111111194);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        this.parentGui.drawTexturedModalRect(x, y, 0, 0, 32, 32);
        GL11.glPopMatrix();
    }
}
