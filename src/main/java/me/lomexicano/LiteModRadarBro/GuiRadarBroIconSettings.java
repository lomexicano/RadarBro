package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiRadarBroIconSettings extends GuiScreen {
    private GuiScreen parentScreen;

    public GuiRadarBroIconSettings(GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    }

    public void updateScreen() {}

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 65, 22, 14, LiteModRadarBro.RadarBat ? "On" : "Off"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 50, 22, 14, LiteModRadarBro.RadarChicken ? "On" : "Off"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 130, this.height / 2 - 35, 22, 14, LiteModRadarBro.RadarCow ? "On" : "Off"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 130, this.height / 2 - 20, 22, 14, LiteModRadarBro.RadarMooshroom ? "On" : "Off"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 130, this.height / 2 - 5, 22, 14, LiteModRadarBro.RadarOcelot ? "On" : "Off"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 130, this.height / 2 + 10, 22, 14, LiteModRadarBro.RadarPig ? "On" : "Off"));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 130, this.height / 2 + 25, 22, 14, LiteModRadarBro.RadarSheep ? "On" : "Off"));
        this.buttonList.add(new GuiButton(7, this.width / 2 - 130, this.height / 2 + 40, 22, 14, LiteModRadarBro.RadarSnowGolem ? "On" : "Off"));
        this.buttonList.add(new GuiButton(8, this.width / 2 - 130, this.height / 2 + 55, 22, 14, LiteModRadarBro.RadarSquid ? "On" : "Off"));
        this.buttonList.add(new GuiButton(9, this.width / 2 - 130, this.height / 2 + 70, 22, 14, LiteModRadarBro.RadarVillager ? "On" : "Off"));
        this.buttonList.add(new GuiButton(10, this.width / 2 - 17, this.height / 2 - 80, 22, 14, LiteModRadarBro.RadarBlaze ? "On" : "Off"));
        this.buttonList.add(new GuiButton(11, this.width / 2 - 17, this.height / 2 - 65, 22, 14, LiteModRadarBro.RadarCaveSpider ? "On" : "Off"));
        this.buttonList.add(new GuiButton(12, this.width / 2 - 17, this.height / 2 - 50, 22, 14, LiteModRadarBro.RadarCreeper ? "On" : "Off"));
        this.buttonList.add(new GuiButton(13, this.width / 2 - 17, this.height / 2 - 35, 22, 14, LiteModRadarBro.RadarEnderdragon ? "On" : "Off"));
        this.buttonList.add(new GuiButton(14, this.width / 2 - 17, this.height / 2 - 20, 22, 14, LiteModRadarBro.RadarGhast ? "On" : "Off"));
        this.buttonList.add(new GuiButton(15, this.width / 2 - 17, this.height / 2 - 5, 22, 14, LiteModRadarBro.RadarMagmaCube ? "On" : "Off"));
        this.buttonList.add(new GuiButton(16, this.width / 2 - 17, this.height / 2 + 10, 22, 14, LiteModRadarBro.RadarSilverfish ? "On" : "Off"));
        this.buttonList.add(new GuiButton(17, this.width / 2 - 17, this.height / 2 + 25, 22, 14, LiteModRadarBro.RadarSkeleton ? "On" : "Off"));
        this.buttonList.add(new GuiButton(18, this.width / 2 - 17, this.height / 2 + 40, 22, 14, LiteModRadarBro.RadarSlime ? "On" : "Off"));
        this.buttonList.add(new GuiButton(19, this.width / 2 - 17, this.height / 2 + 55, 22, 14, LiteModRadarBro.RadarSpider ? "On" : "Off"));
        this.buttonList.add(new GuiButton(20, this.width / 2 - 17, this.height / 2 + 70, 22, 14, LiteModRadarBro.RadarZombie ? "On" : "Off"));
        this.buttonList.add(new GuiButton(21, this.width / 2 - 17, this.height / 2 + 85, 22, 14, LiteModRadarBro.RadarZombie ? "On" : "Off"));
        this.buttonList.add(new GuiButton(22, this.width / 2 - 17, this.height / 2 + 100, 22, 14, LiteModRadarBro.RadarZombie ? "On" : "Off"));
        this.buttonList.add(new GuiButton(23, this.width / 2 + 95, this.height / 2 - 50, 22, 14, LiteModRadarBro.RadarEnderman ? "On" : "Off"));
        this.buttonList.add(new GuiButton(24, this.width / 2 + 95, this.height / 2 - 35, 22, 14, LiteModRadarBro.RadarIronGolem ? "On" : "Off"));
        this.buttonList.add(new GuiButton(25, this.width / 2 + 95, this.height / 2 - 20, 22, 14, LiteModRadarBro.RadarWolf ? "On" : "Off"));
        this.buttonList.add(new GuiButton(26, this.width / 2 + 95, this.height / 2 - 5, 22, 14, LiteModRadarBro.RadarZombiePigman ? "On" : "Off"));
        this.buttonList.add(new GuiButton(27, this.width / 2 + 185, this.height / 2 - 50, 22, 14, LiteModRadarBro.RadarArrow ? "On" : "Off"));
        this.buttonList.add(new GuiButton(28, this.width / 2 + 185, this.height / 2 - 35, 22, 14, LiteModRadarBro.RadarBoat ? "On" : "Off"));
        this.buttonList.add(new GuiButton(29, this.width / 2 + 185, this.height / 2 - 20, 22, 14, LiteModRadarBro.RadarChest ? "On" : "Off"));
        this.buttonList.add(new GuiButton(30, this.width / 2 + 185, this.height / 2 - 5, 22, 14, LiteModRadarBro.RadarItem ? "On" : "Off"));
        this.buttonList.add(new GuiButton(31, this.width / 2 + 185, this.height / 2 + 10, 22, 14, LiteModRadarBro.RadarMinecart ? "On" : "Off"));
        this.buttonList.add(new GuiButton(32, this.width / 2 + 185, this.height / 2 + 25, 22, 14, LiteModRadarBro.RadarPainting ? "On" : "Off"));
        this.buttonList.add(new GuiButton(33, this.width / 2 + 185, this.height / 2 + 40, 22, 14, LiteModRadarBro.RadarMonsterSpawner ? "On" : "Off"));
        this.buttonList.add(new GuiButton(34, this.width / 2 + 185, this.height / 2 + 55, 22, 14, LiteModRadarBro.RadarWaypoint ? "On" : "Off"));
        this.buttonList.add(new GuiButton(35, this.width / 2 + 185, this.height / 2 + 70, 22, 14, LiteModRadarBro.RadarXPOrb ? "On" : "Off"));
        this.buttonList.add(new GuiButton(36, this.width / 2 + 95, this.height / 2 + 55, 22, 14, LiteModRadarBro.RadarPlayer ? "On" : "Off"));
        this.buttonList.add(new GuiButton(37, this.width / 2 + 95, this.height / 2 + 70, 22, 14, LiteModRadarBro.RadarAlly ? "On" : "Off"));
        this.buttonList.add(new GuiButton(38, this.width / 2 + 95, this.height / 2 + 85, 22, 14, LiteModRadarBro.RadarEnemy ? "On" : "Off"));
        this.buttonList.add(new GuiButton(39, this.width / 2 + 135, this.height / 2 + 90, 60, 20, stringtranslate.translateKey("gui.done")));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled)
            return;
        if (guibutton.id == 0) {
            LiteModRadarBro.RadarBat = !LiteModRadarBro.RadarBat;
            ((GuiButton)this.buttonList.get(0)).displayString = LiteModRadarBro.RadarBat ? "On" : "Off";
        }
        if (guibutton.id == 1) {
            LiteModRadarBro.RadarChicken = !LiteModRadarBro.RadarChicken;
            ((GuiButton)this.buttonList.get(1)).displayString = LiteModRadarBro.RadarChicken ? "On" : "Off";
        }
        if (guibutton.id == 2) {
            LiteModRadarBro.RadarCow = !LiteModRadarBro.RadarCow;
            ((GuiButton)this.buttonList.get(2)).displayString = LiteModRadarBro.RadarCow ? "On" : "Off";
        }
        if (guibutton.id == 3) {
            LiteModRadarBro.RadarMooshroom = !LiteModRadarBro.RadarMooshroom;
            ((GuiButton)this.buttonList.get(3)).displayString = LiteModRadarBro.RadarMooshroom ? "On" : "Off";
        }
        if (guibutton.id == 4) {
            LiteModRadarBro.RadarOcelot = !LiteModRadarBro.RadarOcelot;
            ((GuiButton)this.buttonList.get(4)).displayString = LiteModRadarBro.RadarOcelot ? "On" : "Off";
        }
        if (guibutton.id == 5) {
            LiteModRadarBro.RadarPig = !LiteModRadarBro.RadarPig;
            ((GuiButton)this.buttonList.get(5)).displayString = LiteModRadarBro.RadarPig ? "On" : "Off";
        }
        if (guibutton.id == 6) {
            LiteModRadarBro.RadarSheep = !LiteModRadarBro.RadarSheep;
            ((GuiButton)this.buttonList.get(6)).displayString = LiteModRadarBro.RadarSheep ? "On" : "Off";
        }
        if (guibutton.id == 7) {
            LiteModRadarBro.RadarSnowGolem = !LiteModRadarBro.RadarSnowGolem;
            ((GuiButton)this.buttonList.get(7)).displayString = LiteModRadarBro.RadarSnowGolem ? "On" : "Off";
        }
        if (guibutton.id == 8) {
            LiteModRadarBro.RadarSquid = !LiteModRadarBro.RadarSquid;
            ((GuiButton)this.buttonList.get(8)).displayString = LiteModRadarBro.RadarSquid ? "On" : "Off";
        }
        if (guibutton.id == 9) {
            LiteModRadarBro.RadarVillager = !LiteModRadarBro.RadarVillager;
            ((GuiButton)this.buttonList.get(9)).displayString = LiteModRadarBro.RadarVillager ? "On" : "Off";
        }
        if (guibutton.id == 10) {
            LiteModRadarBro.RadarBlaze = !LiteModRadarBro.RadarBlaze;
            ((GuiButton)this.buttonList.get(10)).displayString = LiteModRadarBro.RadarBlaze ? "On" : "Off";
        }
        if (guibutton.id == 11) {
            LiteModRadarBro.RadarCaveSpider = !LiteModRadarBro.RadarCaveSpider;
            ((GuiButton)this.buttonList.get(11)).displayString = LiteModRadarBro.RadarCaveSpider ? "On" : "Off";
        }
        if (guibutton.id == 12) {
            LiteModRadarBro.RadarCreeper = !LiteModRadarBro.RadarCreeper;
            ((GuiButton)this.buttonList.get(12)).displayString = LiteModRadarBro.RadarCreeper ? "On" : "Off";
        }
        if (guibutton.id == 13) {
            LiteModRadarBro.RadarEnderdragon = !LiteModRadarBro.RadarEnderdragon;
            ((GuiButton)this.buttonList.get(13)).displayString = LiteModRadarBro.RadarEnderdragon ? "On" : "Off";
        }
        if (guibutton.id == 14) {
            LiteModRadarBro.RadarGhast = !LiteModRadarBro.RadarGhast;
            ((GuiButton)this.buttonList.get(14)).displayString = LiteModRadarBro.RadarGhast ? "On" : "Off";
        }
        if (guibutton.id == 15) {
            LiteModRadarBro.RadarMagmaCube = !LiteModRadarBro.RadarMagmaCube;
            ((GuiButton)this.buttonList.get(15)).displayString = LiteModRadarBro.RadarMagmaCube ? "On" : "Off";
        }
        if (guibutton.id == 16) {
            LiteModRadarBro.RadarSilverfish = !LiteModRadarBro.RadarSilverfish;
            ((GuiButton)this.buttonList.get(16)).displayString = LiteModRadarBro.RadarSilverfish ? "On" : "Off";
        }
        if (guibutton.id == 17) {
            LiteModRadarBro.RadarSkeleton = !LiteModRadarBro.RadarSkeleton;
            ((GuiButton)this.buttonList.get(17)).displayString = LiteModRadarBro.RadarSkeleton ? "On" : "Off";
        }
        if (guibutton.id == 18) {
            LiteModRadarBro.RadarSlime = !LiteModRadarBro.RadarSlime;
            ((GuiButton)this.buttonList.get(18)).displayString = LiteModRadarBro.RadarSlime ? "On" : "Off";
        }
        if (guibutton.id == 19) {
            LiteModRadarBro.RadarSpider = !LiteModRadarBro.RadarSpider;
            ((GuiButton)this.buttonList.get(19)).displayString = LiteModRadarBro.RadarSpider ? "On" : "Off";
        }
        if (guibutton.id == 20) {
            LiteModRadarBro.RadarWitch = !LiteModRadarBro.RadarWitch;
            ((GuiButton)this.buttonList.get(20)).displayString = LiteModRadarBro.RadarWitch ? "On" : "Off";
        }
        if (guibutton.id == 21) {
            LiteModRadarBro.RadarWither = !LiteModRadarBro.RadarWither;
            ((GuiButton)this.buttonList.get(21)).displayString = LiteModRadarBro.RadarWither ? "On" : "Off";
        }
        if (guibutton.id == 22) {
            LiteModRadarBro.RadarZombie = !LiteModRadarBro.RadarZombie;
            ((GuiButton)this.buttonList.get(22)).displayString = LiteModRadarBro.RadarZombie ? "On" : "Off";
        }
        if (guibutton.id == 23) {
            LiteModRadarBro.RadarEnderman = !LiteModRadarBro.RadarEnderman;
            ((GuiButton)this.buttonList.get(23)).displayString = LiteModRadarBro.RadarEnderman ? "On" : "Off";
        }
        if (guibutton.id == 24) {
            LiteModRadarBro.RadarIronGolem = !LiteModRadarBro.RadarIronGolem;
            ((GuiButton)this.buttonList.get(24)).displayString = LiteModRadarBro.RadarIronGolem ? "On" : "Off";
        }
        if (guibutton.id == 25) {
            LiteModRadarBro.RadarWolf = !LiteModRadarBro.RadarWolf;
            ((GuiButton)this.buttonList.get(25)).displayString = LiteModRadarBro.RadarWolf ? "On" : "Off";
        }
        if (guibutton.id == 26) {
            LiteModRadarBro.RadarZombiePigman = !LiteModRadarBro.RadarZombiePigman;
            ((GuiButton)this.buttonList.get(26)).displayString = LiteModRadarBro.RadarZombiePigman ? "On" : "Off";
        }
        if (guibutton.id == 27) {
            LiteModRadarBro.RadarArrow = !LiteModRadarBro.RadarArrow;
            ((GuiButton)this.buttonList.get(27)).displayString = LiteModRadarBro.RadarArrow ? "On" : "Off";
        }
        if (guibutton.id == 28) {
            LiteModRadarBro.RadarBoat = !LiteModRadarBro.RadarBoat;
            ((GuiButton)this.buttonList.get(28)).displayString = LiteModRadarBro.RadarBoat ? "On" : "Off";
        }
        if (guibutton.id == 29) {
            LiteModRadarBro.RadarChest = !LiteModRadarBro.RadarChest;
            ((GuiButton)this.buttonList.get(29)).displayString = LiteModRadarBro.RadarChest ? "On" : "Off";
        }
        if (guibutton.id == 30) {
            LiteModRadarBro.RadarItem = !LiteModRadarBro.RadarItem;
            ((GuiButton)this.buttonList.get(30)).displayString = LiteModRadarBro.RadarItem ? "On" : "Off";
        }
        if (guibutton.id == 31) {
            LiteModRadarBro.RadarMinecart = !LiteModRadarBro.RadarMinecart;
            ((GuiButton)this.buttonList.get(31)).displayString = LiteModRadarBro.RadarMinecart ? "On" : "Off";
        }
        if (guibutton.id == 32) {
            LiteModRadarBro.RadarPainting = !LiteModRadarBro.RadarPainting;
            ((GuiButton)this.buttonList.get(32)).displayString = LiteModRadarBro.RadarPainting ? "On" : "Off";
        }
        if (guibutton.id == 33) {
            LiteModRadarBro.RadarMonsterSpawner = !LiteModRadarBro.RadarMonsterSpawner;
            ((GuiButton)this.buttonList.get(33)).displayString = LiteModRadarBro.RadarMonsterSpawner ? "On" : "Off";
        }
        if (guibutton.id == 34) {
            LiteModRadarBro.RadarWaypoint = !LiteModRadarBro.RadarWaypoint;
            ((GuiButton)this.buttonList.get(34)).displayString = LiteModRadarBro.RadarWaypoint ? "On" : "Off";
        }
        if (guibutton.id == 35) {
            LiteModRadarBro.RadarXPOrb = !LiteModRadarBro.RadarXPOrb;
            ((GuiButton)this.buttonList.get(35)).displayString = LiteModRadarBro.RadarXPOrb ? "On" : "Off";
        }
        if (guibutton.id == 36) {
            LiteModRadarBro.RadarPlayer = !LiteModRadarBro.RadarPlayer;
            ((GuiButton)this.buttonList.get(36)).displayString = LiteModRadarBro.RadarPlayer ? "On" : "Off";
        }
        if (guibutton.id == 37) {
            LiteModRadarBro.RadarAlly = !LiteModRadarBro.RadarAlly;
            ((GuiButton)this.buttonList.get(37)).displayString = LiteModRadarBro.RadarAlly ? "On" : "Off";
        }
        if (guibutton.id == 38) {
            LiteModRadarBro.RadarEnemy = !LiteModRadarBro.RadarEnemy;
            ((GuiButton)this.buttonList.get(38)).displayString = LiteModRadarBro.RadarEnemy ? "On" : "Off";
        }
        if (guibutton.id == 39)
            this.mc.displayGuiScreen(this.parentScreen);
        LiteModRadarBro.saveOptions();
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }

    public void drawIcon(int x, int y, int x2, int y2) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef(x, y, 0.0F);
        drawTexturedModalRect(x, y, x2, y2, 16, 16);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    public void drawScreen(int i, int j, float f) {
        GL11.glDisable(2929);
        drawDefaultBackground();
        this.mc.renderEngine.bindTexture("/radaricons.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawIcon(this.width / 2 - 205, this.height / 2 - 62, 80, 32);
        drawIcon(this.width / 2 - 205, this.height / 2 - 47, 32, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 - 32, 48, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 - 17, 144, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 - 2, 240, 16);
        drawIcon(this.width / 2 - 205, this.height / 2 + 13, 160, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 + 28, 176, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 + 43, 240, 0);
        drawIcon(this.width / 2 - 205, this.height / 2 + 58, 16, 16);
        drawIcon(this.width / 2 - 205, this.height / 2 + 73, 80, 16);
        drawIcon(this.width / 2 - 100, this.height / 2 - 77, 0, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 - 62, 16, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 - 47, 64, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 - 32, 80, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 - 17, 112, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 - 2, 128, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 + 13, 192, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 + 28, 208, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 + 43, 224, 0);
        drawIcon(this.width / 2 - 100, this.height / 2 + 58, 0, 16);
        drawIcon(this.width / 2 - 100, this.height / 2 + 73, 96, 32);
        drawIcon(this.width / 2 - 100, this.height / 2 + 88, 112, 32);
        drawIcon(this.width / 2 - 100, this.height / 2 + 103, 48, 16);
        drawIcon(this.width / 2 + 10, this.height / 2 - 47, 96, 0);
        drawIcon(this.width / 2 + 10, this.height / 2 - 32, 0, 32);
        drawIcon(this.width / 2 + 10, this.height / 2 - 17, 32, 16);
        drawIcon(this.width / 2 + 10, this.height / 2 - 2, 64, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 - 47, 112, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 - 32, 128, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 - 17, 16, 32);
        drawIcon(this.width / 2 + 125, this.height / 2 - 2, 144, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 + 13, 160, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 + 28, 176, 16);
        drawIcon(this.width / 2 + 125, this.height / 2 + 43, 128, 32);
        drawIcon(this.width / 2 + 125, this.height / 2 + 58, 32, 32);
        drawIcon(this.width / 2 + 125, this.height / 2 + 73, 192, 16);
        drawIcon(this.width / 2 + 10, this.height / 2 + 58, 96, 16);
        drawIcon(this.width / 2 + 10, this.height / 2 + 73, 208, 16);
        drawIcon(this.width / 2 + 10, this.height / 2 + 88, 224, 16);
        drawCenteredString(this.fontRenderer, "RadarBro Icon Settings", this.width / 2, this.height / 4 - 72 + 20, 16777215);
        drawString(this.fontRenderer, "Passive", this.width / 2 - 180, this.height / 2 - 77, 16777215);
        drawString(this.fontRenderer, "Hostile", this.width / 2 - 60, this.height / 2 - 95, 16777215);
        drawString(this.fontRenderer, "Neutral", this.width / 2 + 40, this.height / 2 - 62, 16777215);
        drawString(this.fontRenderer, "Other", this.width / 2 + 150, this.height / 2 - 62, 16777215);
        drawString(this.fontRenderer, "Players", this.width / 2 + 40, this.height / 2 + 35, 16777215);
        drawString(this.fontRenderer, "Bat", this.width / 2 - 190, this.height / 2 - 62, 10526880);
        drawString(this.fontRenderer, "Chicken", this.width / 2 - 190, this.height / 2 - 47, 10526880);
        drawString(this.fontRenderer, "Cow", this.width / 2 - 190, this.height / 2 - 32, 10526880);
        drawString(this.fontRenderer, "Mooshroom", this.width / 2 - 190, this.height / 2 - 17, 10526880);
        drawString(this.fontRenderer, "Ocelot", this.width / 2 - 190, this.height / 2 - 2, 10526880);
        drawString(this.fontRenderer, "Pig", this.width / 2 - 190, this.height / 2 + 13, 10526880);
        drawString(this.fontRenderer, "Sheep", this.width / 2 - 190, this.height / 2 + 28, 10526880);
        drawString(this.fontRenderer, "Snow Golem", this.width / 2 - 190, this.height / 2 + 43, 10526880);
        drawString(this.fontRenderer, "Squid", this.width / 2 - 190, this.height / 2 + 58, 10526880);
        drawString(this.fontRenderer, "Villager", this.width / 2 - 190, this.height / 2 + 73, 10526880);
        drawString(this.fontRenderer, "Blaze", this.width / 2 - 85, this.height / 2 - 77, 10526880);
        drawString(this.fontRenderer, "Cave Spider", this.width / 2 - 85, this.height / 2 - 62, 10526880);
        drawString(this.fontRenderer, "Creeper", this.width / 2 - 85, this.height / 2 - 47, 10526880);
        drawString(this.fontRenderer, "Enderdragon", this.width / 2 - 85, this.height / 2 - 32, 10526880);
        drawString(this.fontRenderer, "Ghast", this.width / 2 - 85, this.height / 2 - 17, 10526880);
        drawString(this.fontRenderer, "Magma Cube", this.width / 2 - 85, this.height / 2 - 2, 10526880);
        drawString(this.fontRenderer, "Silverfish", this.width / 2 - 85, this.height / 2 + 13, 10526880);
        drawString(this.fontRenderer, "Skeleton", this.width / 2 - 85, this.height / 2 + 28, 10526880);
        drawString(this.fontRenderer, "Slime", this.width / 2 - 85, this.height / 2 + 43, 10526880);
        drawString(this.fontRenderer, "Spider", this.width / 2 - 85, this.height / 2 + 58, 10526880);
        drawString(this.fontRenderer, "Witch", this.width / 2 - 85, this.height / 2 + 73, 10526880);
        drawString(this.fontRenderer, "Wither", this.width / 2 - 85, this.height / 2 + 88, 10526880);
        drawString(this.fontRenderer, "Zombie", this.width / 2 - 85, this.height / 2 + 103, 10526880);
        drawString(this.fontRenderer, "Enderman", this.width / 2 + 25, this.height / 2 - 47, 10526880);
        drawString(this.fontRenderer, "Iron Golem", this.width / 2 + 25, this.height / 2 - 32, 10526880);
        drawString(this.fontRenderer, "Wolf", this.width / 2 + 25, this.height / 2 - 17, 10526880);
        drawString(this.fontRenderer, "Zombie Pigman", this.width / 2 + 25, this.height / 2 - 2, 10526880);
        drawString(this.fontRenderer, "Arrow", this.width / 2 + 140, this.height / 2 - 47, 10526880);
        drawString(this.fontRenderer, "Boat", this.width / 2 + 140, this.height / 2 - 32, 10526880);
        drawString(this.fontRenderer, "Chest", this.width / 2 + 140, this.height / 2 - 17, 10526880);
        drawString(this.fontRenderer, "Item", this.width / 2 + 140, this.height / 2 - 2, 10526880);
        drawString(this.fontRenderer, "Minecart", this.width / 2 + 140, this.height / 2 + 13, 10526880);
        drawString(this.fontRenderer, "Painting", this.width / 2 + 140, this.height / 2 + 28, 10526880);
        drawString(this.fontRenderer, "Spawner", this.width / 2 + 140, this.height / 2 + 43, 10526880);
        drawString(this.fontRenderer, "Waypoint", this.width / 2 + 140, this.height / 2 + 58, 10526880);
        drawString(this.fontRenderer, "XP Orb", this.width / 2 + 140, this.height / 2 + 73, 10526880);
        drawString(this.fontRenderer, "Player", this.width / 2 + 25, this.height / 2 + 58, 10526880);
        drawString(this.fontRenderer, "Ally", this.width / 2 + 25, this.height / 2 + 73, 10526880);
        drawString(this.fontRenderer, "Enemy", this.width / 2 + 25, this.height / 2 + 88, 10526880);
        super.drawScreen(i, j, f);
    }
}
