package me.lomexicano.LiteModRadarBro;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class GuiRadarBro extends GuiScreen {
    private Minecraft mc;

    public GuiRadarBro(Minecraft minecraft) {
        this.mc = minecraft;
    }

    public void drawIconRadar() {
        if (LiteModRadarBro.RadarWaypoint)
            for (int j41 = 0; j41 < LiteModRadarBro.Waypoints.size(); j41++) {
                String[] waypointInfo = LiteModRadarBro.Waypoints.get(j41).toString().split(",");
                int waypointX = (int)Double.parseDouble(waypointInfo[0]);
                int waypointY = (int)Double.parseDouble(waypointInfo[1]);
                int waypointZ = (int)Double.parseDouble(waypointInfo[2]);
                int posX = (int)(this.mc.thePlayer.posX - waypointX);
                int posY = (int)(this.mc.thePlayer.posY - waypointY);
                int posZ = (int)(this.mc.thePlayer.posZ - waypointZ);
                String name = waypointInfo[3];
                String enabled = waypointInfo[4];
                if (enabled.equals("true"))
                    if (Math.hypot(posX, posZ) < 120.0D) {
                        drawIconRadarIcon(posX, posZ, 32, 32);
                        drawRadarNames(posX, posZ, name);
                        LiteModRadarBro.WaypointLastInRangePostiton.put(LiteModRadarBro.Waypoints.get(j41).toString(), posX + "," + posZ);
                    } else {
                        try {
                            String[] lastposition = ((String)LiteModRadarBro.WaypointLastInRangePostiton.get(LiteModRadarBro.Waypoints.get(j41).toString())).split(",");
                            int lastposX = Integer.parseInt(lastposition[0]);
                            int lastposZ = Integer.parseInt(lastposition[1]);
                            drawIconRadarWaypointIcon(lastposX, lastposZ);
                            drawRadarNames(lastposX, lastposZ, name);
                        } catch (NullPointerException e) {}
                    }
            }
        if (LiteModRadarBro.RadarChest || LiteModRadarBro.RadarMonsterSpawner) {
            List<TileEntity> tileEntities = this.mc.theWorld.loadedTileEntityList;
            for (int j = 0; j < tileEntities.size(); j++) {
                TileEntity tileEntity = tileEntities.get(j);
                int pPosX = (int)Math.round(this.mc.thePlayer.posX);
                int pPosY = (int)Math.round(this.mc.thePlayer.posY);
                int pPosZ = (int)Math.round(this.mc.thePlayer.posZ);
                int tileEntPosX = Math.round(tileEntity.xCoord);
                int tileEntPosY = Math.round(tileEntity.yCoord);
                int tileEntPosZ = Math.round(tileEntity.zCoord);
                int dPosX = pPosX - tileEntPosX;
                int dPosY = pPosY - tileEntPosY;
                int dPosZ = pPosZ - tileEntPosZ;
                if (Math.hypot(dPosX, dPosZ) < 130.0D) {
                    if (tileEntity instanceof net.minecraft.src.TileEntityChest && LiteModRadarBro.RadarChest)
                        drawIconRadarIcon(dPosX, dPosZ, 16, 32);
                    if (tileEntity instanceof net.minecraft.src.TileEntityEnderChest && LiteModRadarBro.RadarChest)
                        drawIconRadarIcon(dPosX, dPosZ, 64, 32);
                    if (tileEntity instanceof net.minecraft.src.TileEntityMobSpawner && LiteModRadarBro.RadarMonsterSpawner)
                        drawIconRadarIcon(dPosX, dPosZ, 128, 32);
                }
            }
        }
        List<Entity> entities = this.mc.theWorld.loadedEntityList;
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int pPosX = (int)Math.round(this.mc.thePlayer.posX);
            int pPosY = (int)Math.round(this.mc.thePlayer.posY);
            int pPosZ = (int)Math.round(this.mc.thePlayer.posZ);
            int entPosX = (int)Math.round(entity.posX);
            int entPosY = (int)Math.round(entity.posY);
            int entPosZ = (int)Math.round(entity.posZ);
            int dPosX = pPosX - entPosX;
            int dPosY = pPosY - entPosY;
            int dPosZ = pPosZ - entPosZ;
            if (Math.hypot(dPosX, dPosZ) < 130.0D && entity != this.mc.thePlayer) {
                if (entity instanceof net.minecraft.src.EntityBat && LiteModRadarBro.RadarBat)
                    drawIconRadarIcon(dPosX, dPosZ, 80, 32);
                if (entity instanceof net.minecraft.src.EntityChicken && LiteModRadarBro.RadarChicken)
                    drawIconRadarIcon(dPosX, dPosZ, 32, 0);
                if (entity instanceof net.minecraft.src.EntityCow && LiteModRadarBro.RadarCow && !(entity instanceof net.minecraft.src.EntityMooshroom))
                    drawIconRadarIcon(dPosX, dPosZ, 48, 0);
                if (entity instanceof net.minecraft.src.EntityMooshroom && LiteModRadarBro.RadarMooshroom)
                    drawIconRadarIcon(dPosX, dPosZ, 144, 0);
                if (entity instanceof net.minecraft.src.EntityOcelot && LiteModRadarBro.RadarOcelot)
                    drawIconRadarIcon(dPosX, dPosZ, 240, 16);
                if (entity instanceof net.minecraft.src.EntityPig && LiteModRadarBro.RadarPig)
                    drawIconRadarIcon(dPosX, dPosZ, 160, 0);
                if (entity instanceof net.minecraft.src.EntitySheep && LiteModRadarBro.RadarSheep)
                    drawIconRadarIcon(dPosX, dPosZ, 176, 0);
                if (entity instanceof net.minecraft.src.EntitySnowman && LiteModRadarBro.RadarSnowGolem)
                    drawIconRadarIcon(dPosX, dPosZ, 240, 0);
                if (entity instanceof net.minecraft.src.EntitySquid && LiteModRadarBro.RadarSquid)
                    drawIconRadarIcon(dPosX, dPosZ, 16, 16);
                if (entity instanceof net.minecraft.src.EntityVillager && LiteModRadarBro.RadarVillager)
                    drawIconRadarIcon(dPosX, dPosZ, 80, 16);
                if (entity instanceof net.minecraft.src.EntityBlaze && LiteModRadarBro.RadarBlaze)
                    drawIconRadarIcon(dPosX, dPosZ, 0, 0);
                if (entity instanceof net.minecraft.src.EntityCaveSpider && LiteModRadarBro.RadarCaveSpider)
                    drawIconRadarIcon(dPosX, dPosZ, 16, 0);
                if (entity instanceof net.minecraft.src.EntityCreeper && LiteModRadarBro.RadarCreeper)
                    drawIconRadarIcon(dPosX, dPosZ, 64, 0);
                if (entity instanceof net.minecraft.src.EntityDragon && LiteModRadarBro.RadarEnderdragon)
                    drawIconRadarIcon(dPosX, dPosZ, 80, 0);
                if (entity instanceof net.minecraft.src.EntityGhast && LiteModRadarBro.RadarGhast)
                    drawIconRadarIcon(dPosX, dPosZ, 112, 0);
                if (entity instanceof net.minecraft.src.EntityMagmaCube && LiteModRadarBro.RadarMagmaCube)
                    drawIconRadarIcon(dPosX, dPosZ, 128, 0);
                if (entity instanceof net.minecraft.src.EntitySilverfish && LiteModRadarBro.RadarSilverfish)
                    drawIconRadarIcon(dPosX, dPosZ, 192, 0);
                if (entity instanceof net.minecraft.src.EntitySkeleton && LiteModRadarBro.RadarSkeleton)
                    drawIconRadarIcon(dPosX, dPosZ, 208, 0);
                if (entity instanceof net.minecraft.src.EntitySlime && LiteModRadarBro.RadarSlime && !(entity instanceof net.minecraft.src.EntityMagmaCube))
                    drawIconRadarIcon(dPosX, dPosZ, 224, 0);
                if (entity instanceof net.minecraft.src.EntitySpider && LiteModRadarBro.RadarSpider)
                    drawIconRadarIcon(dPosX, dPosZ, 0, 16);
                if (entity instanceof net.minecraft.src.EntityWitch && LiteModRadarBro.RadarWitch)
                    drawIconRadarIcon(dPosX, dPosZ, 96, 32);
                if (entity instanceof net.minecraft.src.EntityWither && LiteModRadarBro.RadarWither)
                    drawIconRadarIcon(dPosX, dPosZ, 112, 32);
                if (entity instanceof net.minecraft.src.EntityZombie && LiteModRadarBro.RadarZombie)
                    drawIconRadarIcon(dPosX, dPosZ, 48, 16);
                if (entity instanceof net.minecraft.src.EntityEnderman && LiteModRadarBro.RadarEnderman)
                    drawIconRadarIcon(dPosX, dPosZ, 96, 0);
                if (entity instanceof net.minecraft.src.EntityIronGolem && LiteModRadarBro.RadarIronGolem)
                    drawIconRadarIcon(dPosX, dPosZ, 0, 32);
                if (entity instanceof net.minecraft.src.EntityWolf && LiteModRadarBro.RadarWolf)
                    drawIconRadarIcon(dPosX, dPosZ, 32, 16);
                if (entity instanceof net.minecraft.src.EntityPigZombie && LiteModRadarBro.RadarZombiePigman)
                    drawIconRadarIcon(dPosX, dPosZ, 64, 16);
                if (entity instanceof net.minecraft.src.EntityArrow && LiteModRadarBro.RadarArrow)
                    drawIconRadarIcon(dPosX, dPosZ, 112, 16);
                if (entity instanceof net.minecraft.src.EntityBoat && LiteModRadarBro.RadarBoat)
                    drawIconRadarIcon(dPosX, dPosZ, 128, 16);
                if (entity instanceof EntityItem && LiteModRadarBro.RadarItem) {
                    EntityItem entitem = (EntityItem)entities.get(i);
                    drawIconRadarItemIcon(dPosX, dPosZ, entitem.getEntityItem());
                }
                if (entity instanceof net.minecraft.src.EntityMinecart && LiteModRadarBro.RadarMinecart)
                    drawIconRadarIcon(dPosX, dPosZ, 160, 16);
                if (entity instanceof net.minecraft.src.EntityPainting && LiteModRadarBro.RadarPainting)
                    drawIconRadarIcon(dPosX, dPosZ, 176, 16);
                if (entity instanceof net.minecraft.src.EntityXPOrb && LiteModRadarBro.RadarXPOrb)
                    drawIconRadarIcon(dPosX, dPosZ, 192, 16);
                if (entity instanceof net.minecraft.src.EntityPlayer)
                    try {
                        EntityOtherPlayerMP eop = (EntityOtherPlayerMP)entities.get(i);
                        if (LiteModRadarBro.AllyList.contains(eop.username)) {
                            drawIconRadarIcon(dPosX, dPosZ, 208, 16);
                            if (LiteModRadarBro.RadarPlayerNames)
                                drawRadarNames(dPosX, dPosZ, eop.username);
                        }
                        if (LiteModRadarBro.EnemyList.contains(eop.username) && LiteModRadarBro.RadarEnemy) {
                            drawIconRadarIcon(dPosX, dPosZ, 224, 16);
                            if (LiteModRadarBro.RadarPlayerNames)
                                drawRadarNames(dPosX, dPosZ, eop.username);
                        }
                        if (LiteModRadarBro.RadarPlayer) {
                            if (LiteModRadarBro.RadarUsePlayerSkinTexture) {
                                drawPlayerHeadImage(generatePlayerHeadImage(eop.username), (dPosX + 5) / 2, (dPosZ + 5) / 2);
                            } else {
                                drawIconRadarIcon(dPosX, dPosZ, 96, 16);
                            }
                            if (LiteModRadarBro.RadarPlayerNames)
                                drawRadarNames(dPosX, dPosZ, eop.username);
                        }
                    } catch (ClassCastException e) {}
            }
        }
    }

    public static void drawCircle(int x, int y, double r, int c, boolean filled) {
        float f = (c >> 16 & 0xFF) / 255.0F;
        float f1 = (c >> 8 & 0xFF) / 255.0F;
        float f2 = (c & 0xFF) / 255.0F;
        float f3 = (c >> 24 & 0xFF) / 255.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f, f1, f2, f3);
        GL11.glBegin(filled ? 6 : 2);
        for (int i = 0; i <= 360; i++) {
            double x2 = Math.sin(i * 3.141526D / 180.0D) * r;
            double y2 = Math.cos(i * 3.141526D / 180.0D) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawTriangle(int cx, int cy, int c) {
        if (!LiteModRadarBro.RadarAutoRotate) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            GL11.glRotatef(-Minecraft.getMinecraft().thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
        }
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f = (c >> 24 & 0xFF) / 255.0F;
        float f1 = (c >> 16 & 0xFF) / 255.0F;
        float f2 = (c >> 8 & 0xFF) / 255.0F;
        float f3 = (c & 0xFF) / 255.0F;
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(4);
        GL11.glVertex2d(cx, (cy + 3));
        GL11.glVertex2d((cx + 3), (cy - 3));
        GL11.glVertex2d((cx - 3), (cy - 3));
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
        if (!LiteModRadarBro.RadarAutoRotate)
            GL11.glPopMatrix();
    }

    private void setColorizedColor(int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public void renderTerrain() {
        this.mc.renderEngine.bindTexture("/terrain.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        for (int ii = -80; ii < 80; ii++) {
            int iX = ii;
            for (int kk = -80; kk < 80; kk++) {
                int kZ = kk;
                int blockX = (int)(this.mc.thePlayer.posX + iX), blockZ = (int)(this.mc.thePlayer.posZ + kZ), blockY = this.mc.theWorld.getHeightValue(blockX, blockZ) - 1;
                int blockId = this.mc.theWorld.getBlockId(blockX, blockY, blockZ);
                int par0 = (int)MathHelper.clamp_float(this.mc.theWorld.getWorldChunkManager().getBiomeGenAt(blockX, blockY).getFloatTemperature(), 0.0F, 1.0F);
                int par2 = (int)MathHelper.clamp_float(this.mc.theWorld.getWorldChunkManager().getBiomeGenAt(blockX, blockY).getFloatRainfall(), 1.0F, 0.0F);
                int dPosX = (int)(this.mc.thePlayer.posX - blockX);
                int dPosZ = (int)(this.mc.thePlayer.posZ - blockZ);
                if (Math.PI * dPosX * dPosX + Math.PI * dPosZ * dPosZ <= 12409.290981679684D) {
                    int[] colorized = { 2, 18, 8, 9 };
                    if (blockId == colorized[0]) {
                        setColorizedColor(ColorizerGrass.getGrassColor(par0, par2));
                    } else if (blockId == colorized[1]) {
                        setColorizedColor(ColorizerFoliage.getFoliageColor(par0, par2));
                    } else if (blockId == colorized[2] || blockId == colorized[3]) {
                        setColorizedColor(-16773121);
                    } else {
                        GL11.glColor4f(255.0F, 255.0F, 255.0F, 255.0F);
                    }
                    drawTexturedModalRect(dPosX, dPosZ, 0, 0, 1, 1);
                }
            }
        }
    }

    public void drawIconRadarIcon(int x, int y, int x2, int y2) {
        this.mc.renderEngine.bindTexture("/radaricons.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3042);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef((x + 1), (y + 1), 0.0F);
        if (LiteModRadarBro.RadarAutoRotate)
            GL11.glRotatef(this.mc.thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
        drawTexturedModalRect(-8, -8, x2, y2, 16, 16);
        GL11.glTranslatef((-x - 1), (-y - 1), 0.0F);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glDisable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public void drawIconRadarItemIcon(int x, int y, ItemStack is) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef((x + 1), (y + 1), 0.0F);
        if (LiteModRadarBro.RadarAutoRotate)
            GL11.glRotatef(this.mc.thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
        LiteModRadarBro.itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, is, -8, -8);
        GL11.glTranslatef((-x - 1), (-y - 1), 0.0F);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glDisable(2896);
        GL11.glPopMatrix();
    }

    public void drawIconRadarWaypointIcon(int x, int y) {
        this.mc.renderEngine.bindTexture("/radaricons.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef((x + 1), (y + 1), 0.0F);
        if (LiteModRadarBro.RadarAutoRotate)
            GL11.glRotatef(-this.mc.thePlayer.rotationYaw / 180.0F - 180.0F, 0.0F, 0.0F, 1.0F);
        drawTexturedModalRect(-8, 0, 48, 32, 16, 16);
        GL11.glTranslatef((-x - 1), (-y - 1), 0.0F);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glDisable(2896);
        GL11.glPopMatrix();
    }

    public void drawRadarNames(int x, int y, String username) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef(x, y, 0.0F);
        if (LiteModRadarBro.RadarAutoRotate)
            GL11.glRotatef((mc.thePlayer.rotationYaw), 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-x, -y, 0.0F);
        FontRenderer fontrenderer = mc.fontRenderer;
        if (LiteModRadarBro.AllyList.contains(username)) {
            drawCenteredString(fontrenderer, username, x, y - 18, 3665978);
        } else if (LiteModRadarBro.EnemyList.contains(username)) {
            drawCenteredString(fontrenderer, username, x, y - 18, 15742782);
        } else if (LiteModRadarBro.RadarColorPlayerNames) {
            drawCenteredString(fontrenderer, username, x, y - 18, 14737632);
        } else {
            if (username.contains("§"))
                    username = username.substring(2);
            drawCenteredString(fontrenderer, username, x, y - 18, 14737632);
        }
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    public void drawPlayerHeadImage(BufferedImage bufferedimage, int x, int y) {
        this.mc.renderEngine.setupTexture(bufferedimage, 1111111194);
        GL11.glBindTexture(3553, 1111111194);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glTranslatef((x - 3), (y - 2), 0.0F);
        if (LiteModRadarBro.RadarAutoRotate)
            GL11.glRotatef(this.mc.thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef((-x - 3), (-y - 4), 0.0F);
        drawTexturedModalRect(x, y, 0, 0, 32, 32);
        GL11.glPopMatrix();
    }

    public static BufferedImage generatePlayerHeadImage(String username) {
        if (username.contains("§"))
                username = username.substring(2);
        String skinURL = "http://minecraft.net/skin/" + username + ".png";
        if (!LiteModRadarBro.HeadIconCache.containsKey(skinURL))
            LiteModRadarBro.HeadIconCache.put(skinURL, null);
        URL url = null;
        try {
            url = new URL(skinURL);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        BufferedImage bufferedimage = LiteModRadarBro.getIconFromCache(skinURL);
        if (bufferedimage == null) {
            BufferedImage playerSkin = null;
            try {
                playerSkin = ImageIO.read(url.openStream());
                BufferedImage playerHead = playerSkin.getSubimage(8, 8, 8, 8);
                BufferedImage playerBody = new BufferedImage(256, 256, 2);
                Graphics g = playerBody.getGraphics();
                g.drawImage(playerHead, 0, 0, null);
                LiteModRadarBro.HeadIconCache.remove(skinURL);
                LiteModRadarBro.HeadIconCache.put(skinURL, playerBody);
                return playerBody;
            } catch (IOException e) {
                BufferedImage bi = null;
                try {
                    bi = ImageIO.read(
                            Objects.requireNonNull(Minecraft.class.getResourceAsStream("/mob/char.png"))
                    );
                    BufferedImage playerHead = bi.getSubimage(8, 8, 8, 8);
                    BufferedImage playerBody = new BufferedImage(256, 256, 2);
                    Graphics g = playerBody.getGraphics();
                    g.drawImage(playerHead, 0, 0, null);
                    LiteModRadarBro.HeadIconCache.remove(skinURL);
                    LiteModRadarBro.HeadIconCache.put(skinURL, playerBody);
                    return playerBody;
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return bufferedimage;
                }
            }
        }
        return bufferedimage;
    }
}
