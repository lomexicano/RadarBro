package me.lomexicano.LiteModRadarBro;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.util.ModUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.RenderItem;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class LiteModRadarBro implements LiteMod, Tickable {
    protected Minecraft mc;

    public KeyBinding GUIkey = new KeyBinding("Radar Settings", 19);

    protected static RenderItem itemRenderer = new RenderItem();

    public static ArrayList AllyList = new ArrayList();

    public static ArrayList EnemyList = new ArrayList();

    public static ArrayList Waypoints = new ArrayList();

    public static HashMap HeadIconCache = new HashMap<Object, Object>();

    public static HashMap WaypointLastInRangePostiton = new HashMap<Object, Object>();

    private static File settingsFile;

    public static boolean RadarEnabled = true;

    public static boolean RadarPlayerNames = true;

    public static boolean RadarColorPlayerNames = true;

    public static boolean RadarUsePlayerSkinTexture = false;

    public static boolean RadarAutoRotate = true;

    public static boolean RadarTerrain = false;

    public static boolean RadarCoordinates = true;

    public static boolean RadarBat = true;

    public static boolean RadarChicken = true;

    public static boolean RadarCow = true;

    public static boolean RadarMooshroom = true;

    public static boolean RadarOcelot = true;

    public static boolean RadarPig = true;

    public static boolean RadarSheep = true;

    public static boolean RadarSnowGolem = true;

    public static boolean RadarSquid = true;

    public static boolean RadarVillager = true;

    public static boolean RadarBlaze = true;

    public static boolean RadarCaveSpider = true;

    public static boolean RadarCreeper = true;

    public static boolean RadarEnderdragon = true;

    public static boolean RadarGhast = true;

    public static boolean RadarMagmaCube = true;

    public static boolean RadarSilverfish = true;

    public static boolean RadarSkeleton = true;

    public static boolean RadarSlime = true;

    public static boolean RadarSpider = true;

    public static boolean RadarWitch = true;

    public static boolean RadarWither = true;

    public static boolean RadarZombie = true;

    public static boolean RadarEnderman = true;

    public static boolean RadarIronGolem = true;

    public static boolean RadarWolf = true;

    public static boolean RadarZombiePigman = true;

    public static boolean RadarArrow = true;

    public static boolean RadarBoat = true;

    public static boolean RadarChest = true;

    public static boolean RadarItem = true;

    public static boolean RadarMinecart = true;

    public static boolean RadarMonsterSpawner = true;

    public static boolean RadarPainting = true;

    public static boolean RadarWaypoint = true;

    public static boolean RadarXPOrb = true;

    public static boolean RadarPlayer = true;

    public static boolean RadarAlly = true;

    public static boolean RadarEnemy = true;

    public static ArrayList entityList = new ArrayList(Arrays.asList((Object[])new String[] { "Bat", "Cow", "Chicken" }));

    @Override
    public String getName() {
        return "RadarBro";
    }

    public String getVersion() {
        return "1.5.1";
    }

    @Override
    public void init() {
        this.mc = Minecraft.getMinecraft();

        ModUtilities.registerKey(this.GUIkey);

        settingsFile = new File(Minecraft.getMinecraftDir() + "\\mods\\RadarBro\\settings.txt");

        loadOptions();
        loadAllyList();
        loadEnemyList();
        loadWaypointList();
    }

    public static BufferedImage getIconFromCache(String url) {
        BufferedImage bufferedimage = (BufferedImage)HeadIconCache.get(url);
        if (bufferedimage != null)
            return bufferedimage;
        return null;
    }

    public void loadWaypointList() {
        try {
            NBTTagCompound nbttagcompound = CompressedStreamTools.read(new File(this.mc.mcDataDir + "\\mods\\RadarBro", "waypoints.dat"));
            NBTTagList nbttaglist = nbttagcompound.getTagList("waypoints");
            for (int i = 0; i < nbttaglist.tagCount(); i++)
                WaypointNBTStorage.createWaypointNBTStorage((NBTTagCompound)nbttaglist.tagAt(i));
        } catch (Exception exception) {}
    }

    public static void loadAllyList() {
        try {
            int[] ai = new int[512];
            File file = new File(Minecraft.getMinecraftDir() + "\\mods\\RadarBro\\", "AllyList.txt");
            if (file.exists()) {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
                String s;
                for (int i = 0; (s = bufferedreader.readLine()) != null; i++) {
                    AllyList.add(s);
                    GuiAllyEnemyManager.currentList.add(s);
                }
                bufferedreader.close();
            }
        } catch (Exception exception) {
            System.err.print(exception.toString());
        }
    }

    public static void loadEnemyList() {
        try {
            int[] ai = new int[512];
            File file = new File(Minecraft.getMinecraftDir() + "\\mods\\RadarBro\\", "EnemyList.txt");
            if (file.exists()) {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
                String s;
                for (int i = 0; (s = bufferedreader.readLine()) != null; i++) {
                    EnemyList.add(s);
                    GuiAllyEnemyManager.currentList.add(s);
                }
                bufferedreader.close();
            }
        } catch (Exception exception) {
            System.err.print(exception.toString());
        }
    }

    public static void saveAllyList() {
        try {
            (new File(Minecraft.getMinecraftDir(), "mods" + File.separator + "RadarBro")).mkdirs();
            File file = new File(Minecraft.getMinecraftDir() + "\\mods\\RadarBro\\", "AllyList.txt");
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < AllyList.size(); i++)
                bufferedwriter.write((String)AllyList.get(i) + "\r\n");
            bufferedwriter.close();
        } catch (Exception exception) {
            System.err.print(exception.toString());
        }
    }

    public static void saveEnemyList() {
        try {
            (new File(Minecraft.getMinecraftDir(), "mods" + File.separator + "RadarBro")).mkdirs();
            File file = new File(Minecraft.getMinecraftDir() + "\\mods\\RadarBro\\", "EnemyList.txt");
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < EnemyList.size(); i++)
                bufferedwriter.write((String)EnemyList.get(i) + "\r\n");
            bufferedwriter.close();
        } catch (Exception exception) {
            System.err.print(exception.toString());
        }
    }

    public static void loadOptions() {
        try {
            (new File(Minecraft.getMinecraftDir(), "mods" + File.separator + "RadarBro")).mkdirs();
            if (!settingsFile.exists())
                return;
            BufferedReader bufferedreader = new BufferedReader(new FileReader(settingsFile));
            for (String s = ""; (s = bufferedreader.readLine()) != null;) {
                try {
                    String[] as = s.split(":");
                    if (as[0].equals("radar_Bat"))
                        RadarBat = as[1].equals("true");
                    if (as[0].equals("radar_Chicken"))
                        RadarChicken = as[1].equals("true");
                    if (as[0].equals("radar_Cow"))
                        RadarCow = as[1].equals("true");
                    if (as[0].equals("radar_Mooshroom"))
                        RadarMooshroom = as[1].equals("true");
                    if (as[0].equals("radar_Ocelot"))
                        RadarOcelot = as[1].equals("true");
                    if (as[0].equals("radar_Pig"))
                        RadarPig = as[1].equals("true");
                    if (as[0].equals("radar_Sheep"))
                        RadarSheep = as[1].equals("true");
                    if (as[0].equals("radar_SnowGolem"))
                        RadarSnowGolem = as[1].equals("true");
                    if (as[0].equals("radar_Squid"))
                        RadarSquid = as[1].equals("true");
                    if (as[0].equals("radar_Villager"))
                        RadarVillager = as[1].equals("true");
                    if (as[0].equals("radar_Blaze"))
                        RadarBlaze = as[1].equals("true");
                    if (as[0].equals("radar_CaveSpider"))
                        RadarCaveSpider = as[1].equals("true");
                    if (as[0].equals("radar_Creeper"))
                        RadarCreeper = as[1].equals("true");
                    if (as[0].equals("radar_Enderdragon"))
                        RadarEnderdragon = as[1].equals("true");
                    if (as[0].equals("radar_Ghast"))
                        RadarGhast = as[1].equals("true");
                    if (as[0].equals("radar_MagmaCube"))
                        RadarMagmaCube = as[1].equals("true");
                    if (as[0].equals("radar_Silverfish"))
                        RadarSilverfish = as[1].equals("true");
                    if (as[0].equals("radar_Skeleton"))
                        RadarSkeleton = as[1].equals("true");
                    if (as[0].equals("radar_Slime"))
                        RadarSlime = as[1].equals("true");
                    if (as[0].equals("radar_Spider"))
                        RadarSpider = as[1].equals("true");
                    if (as[0].equals("radar_Witch"))
                        RadarWitch = as[1].equals("true");
                    if (as[0].equals("radar_Wither"))
                        RadarWither = as[1].equals("true");
                    if (as[0].equals("radar_Zombie"))
                        RadarZombie = as[1].equals("true");
                    if (as[0].equals("radar_Enderman"))
                        RadarEnderman = as[1].equals("true");
                    if (as[0].equals("radar_IronGolem"))
                        RadarIronGolem = as[1].equals("true");
                    if (as[0].equals("radar_Wolf"))
                        RadarWolf = as[1].equals("true");
                    if (as[0].equals("radar_ZombiePigman"))
                        RadarZombiePigman = as[1].equals("true");
                    if (as[0].equals("radar_Arrow"))
                        RadarArrow = as[1].equals("true");
                    if (as[0].equals("radar_Boat"))
                        RadarBoat = as[1].equals("true");
                    if (as[0].equals("radar_Chest"))
                        RadarChest = as[1].equals("true");
                    if (as[0].equals("radar_Item"))
                        RadarItem = as[1].equals("true");
                    if (as[0].equals("radar_Minecart"))
                        RadarMinecart = as[1].equals("true");
                    if (as[0].equals("radar_Painting"))
                        RadarPainting = as[1].equals("true");
                    if (as[0].equals("radar_MonsterSpawner"))
                        RadarMonsterSpawner = as[1].equals("true");
                    if (as[0].equals("radar_Waypoint"))
                        RadarWaypoint = as[1].equals("true");
                    if (as[0].equals("radar_XPOrb"))
                        RadarXPOrb = as[1].equals("true");
                    if (as[0].equals("radar_Player"))
                        RadarPlayer = as[1].equals("true");
                    if (as[0].equals("radar_Ally"))
                        RadarAlly = as[1].equals("true");
                    if (as[0].equals("radar_Enemy"))
                        RadarEnemy = as[1].equals("true");
                    if (as[0].equals("radar_Terrain"))
                        RadarTerrain = as[1].equals("true");
                    if (as[0].equals("radar_Coordinates"))
                        RadarCoordinates = as[1].equals("true");
                    if (as[0].equals("radar_xOffset"))
                        GuiRepositionRadarBro.xEndDisplacement = Integer.parseInt(as[1]);
                    if (as[0].equals("radar_yOffset"))
                        GuiRepositionRadarBro.yEndDisplacement = Integer.parseInt(as[1]);
                } catch (Exception exception1) {
                    System.out.println("Skipping bad setting: " + s);
                }
            }
            bufferedreader.close();
        } catch (Exception exception) {
            System.out.println("Failed to load settings");
            exception.printStackTrace();
        }
    }

    public static void saveOptions() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(settingsFile));
            printwriter.println("radar_Bat:" + RadarBat);
            printwriter.println("radar_Chicken:" + RadarChicken);
            printwriter.println("radar_Cow:" + RadarCow);
            printwriter.println("radar_Mooshroom:" + RadarMooshroom);
            printwriter.println("radar_Ocelot:" + RadarOcelot);
            printwriter.println("radar_Pig:" + RadarPig);
            printwriter.println("radar_Sheep:" + RadarSheep);
            printwriter.println("radar_SnowGolem:" + RadarSnowGolem);
            printwriter.println("radar_Squid:" + RadarSquid);
            printwriter.println("radar_Villager:" + RadarVillager);
            printwriter.println("radar_Blaze:" + RadarBlaze);
            printwriter.println("radar_CaveSpider:" + RadarCaveSpider);
            printwriter.println("radar_Creeper:" + RadarCreeper);
            printwriter.println("radar_Enderdragon:" + RadarEnderdragon);
            printwriter.println("radar_Ghast:" + RadarGhast);
            printwriter.println("radar_MagmaCube:" + RadarMagmaCube);
            printwriter.println("radar_Silverfish:" + RadarSilverfish);
            printwriter.println("radar_Skeleton:" + RadarSkeleton);
            printwriter.println("radar_Slime:" + RadarSlime);
            printwriter.println("radar_Spider:" + RadarSpider);
            printwriter.println("radar_Witch:" + RadarWitch);
            printwriter.println("radar_Wither:" + RadarWither);
            printwriter.println("radar_Zombie:" + RadarZombie);
            printwriter.println("radar_Enderman:" + RadarEnderman);
            printwriter.println("radar_IronGolem:" + RadarIronGolem);
            printwriter.println("radar_Wolf:" + RadarWolf);
            printwriter.println("radar_ZombiePigman:" + RadarZombiePigman);
            printwriter.println("radar_Arrow:" + RadarArrow);
            printwriter.println("radar_Boat:" + RadarBoat);
            printwriter.println("radar_Chest:" + RadarChest);
            printwriter.println("radar_Item:" + RadarItem);
            printwriter.println("radar_Minecart:" + RadarMinecart);
            printwriter.println("radar_Painting:" + RadarPainting);
            printwriter.println("radar_MonsterSpawner:" + RadarMonsterSpawner);
            printwriter.println("radar_Waypoint:" + RadarWaypoint);
            printwriter.println("radar_XPOrb:" + RadarXPOrb);
            printwriter.println("radar_Player:" + RadarPlayer);
            printwriter.println("radar_Ally:" + RadarAlly);
            printwriter.println("radar_Enemy:" + RadarEnemy);
            printwriter.println("radar_Terrain:" + RadarTerrain);
            printwriter.println("radar_Coordinates:" + RadarCoordinates);
            printwriter.println("radar_xOffset:" + GuiRepositionRadarBro.xEndDisplacement);
            printwriter.println("radar_yOffset:" + GuiRepositionRadarBro.yEndDisplacement);
            printwriter.close();
        } catch (Exception exception) {
            System.out.println("Failed to save settings");
            exception.printStackTrace();
        }
    }

    @Override
    public void onTick(Minecraft minecraft, float f, boolean inGame, boolean clock) {
        if (!inGame || minecraft == null || minecraft.thePlayer == null || minecraft.fontRenderer == null || this.GUIkey == null) {
            return;
        }

        if (this.GUIkey.isPressed() && minecraft.currentScreen == null) {
            minecraft.displayGuiScreen(new GuiRadarBroSettings(null));
        }

        if (RadarEnabled) {
            GuiRadarBro grb = new GuiRadarBro(minecraft);
            ScaledResolution sr = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
            int i = sr.getScaledWidth();
            GL11.glPushMatrix();
            GL11.glTranslatef((i - 65 + GuiRepositionRadarBro.xDisplacement + GuiRepositionRadarBro.xEndDisplacement), (65 + GuiRepositionRadarBro.yDisplacement + GuiRepositionRadarBro.yEndDisplacement), 0.0F);
            if (RadarCoordinates) {
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                minecraft.fontRenderer.drawString("(" + (int)minecraft.thePlayer.posX, -8 - minecraft.fontRenderer.getStringWidth("(" + (int)minecraft.thePlayer.posX), 134, 14737632);
                minecraft.fontRenderer.drawString("," + (int)minecraft.thePlayer.posY + "," + (int)minecraft.thePlayer.posZ + ")", -8, 134, 14737632);
                GL11.glScalef(2.0F, 2.0F, 2.0F);
            }
            if (RadarAutoRotate)
                GL11.glRotatef(-minecraft.thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
            try {
                if (RadarTerrain)
                    grb.renderTerrain();
            } catch (Exception e) {}
            GuiRadarBro.drawCircle(0, 0, 63.0D, -2147483648, true);
            GuiRadarBro.drawCircle(0, 0, 63.0D, -2140904094, false);
            GuiRadarBro.drawCircle(0, 0, 43.0D, -2140904094, false);
            GuiRadarBro.drawCircle(0, 0, 22.0D, -2140904094, false);
            GL11.glLineWidth(2.0F);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glBegin(1);
            GL11.glVertex2d(0.0D, -63.0D);
            GL11.glVertex2d(0.0D, 63.0D);
            GL11.glVertex2d(-63.0D, 0.0D);
            GL11.glVertex2d(63.0D, 0.0D);
            GL11.glVertex2d(-44.5D, -44.5D);
            GL11.glVertex2d(44.5D, 44.5D);
            GL11.glVertex2d(-44.5D, 44.5D);
            GL11.glVertex2d(44.5D, -44.5D);
            GL11.glEnd();
            GL11.glDisable(3042);
            GL11.glEnable(3553);
            grb.drawIconRadar();
            if (RadarAutoRotate)
                GL11.glRotatef(minecraft.thePlayer.rotationYaw, 0.0F, 0.0F, 1.0F);
            GuiRadarBro.drawTriangle(0, 0, -4144960);
            GL11.glPopMatrix();
        }
    }
}
