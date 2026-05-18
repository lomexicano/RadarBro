package me.lomexicano.LiteModRadarBro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class GuiWaypointManager extends GuiScreen {
    private GuiScreen parentScreen;

    private GuiSlotWaypoint waypointSlotContainer;

    private List waypointList;

    private int selectedWaypoint;

    private GuiButton buttonEdit;

    private GuiButton buttonDelete;

    private GuiButton buttonToggle;

    private boolean deleteClicked;

    private boolean addClicked;

    private boolean editClicked;

    private String timeTooltip;

    private WaypointNBTStorage tempWaypoint;

    public Minecraft mc = Minecraft.getMinecraft();

    public FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

    public GuiWaypointManager(GuiScreen par1GuiScreen) {
        this.waypointList = new ArrayList();
        this.selectedWaypoint = -1;
        this.deleteClicked = false;
        this.addClicked = false;
        this.editClicked = false;
        this.timeTooltip = null;
        this.tempWaypoint = null;
        this.parentScreen = par1GuiScreen;
    }

    public void initGui() {
        loadWaypointList();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.waypointSlotContainer = new GuiSlotWaypoint(this);
        initGuiControls();
    }

    public void loadWaypointList() {
        try {
            NBTTagCompound nbttagcompound = CompressedStreamTools.read(new File(this.mc.mcDataDir + "\\mods\\RadarBro", "waypoints.dat"));
            NBTTagList nbttaglist = nbttagcompound.getTagList("waypoints");
            this.waypointList.clear();
            for (int i = 0; i < nbttaglist.tagCount(); i++)
                this.waypointList.add(WaypointNBTStorage.createWaypointNBTStorage((NBTTagCompound)nbttaglist.tagAt(i)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void saveWaypointList() {
        try {
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.waypointList.size(); i++)
                nbttaglist.appendTag((NBTBase)((WaypointNBTStorage)this.waypointList.get(i)).getCompoundTag());
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("waypoints", (NBTBase)nbttaglist);
            CompressedStreamTools.safeWrite(nbttagcompound, new File(this.mc.mcDataDir + "\\mods\\RadarBro", "waypoints.dat"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void initGuiControls() {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        this.buttonList.add(this.buttonEdit = new GuiButton(7, this.width / 2 - 154, this.height - 28, 100, 20, stringtranslate.translateKey("selectServer.edit")));
        this.buttonList.add(this.buttonDelete = new GuiButton(2, this.width / 2 - 50, this.height - 28, 100, 20, stringtranslate.translateKey("selectServer.delete")));
        this.buttonList.add(this.buttonToggle = new GuiButton(4, this.width / 2 - 154, this.height - 52, 155, 20, stringtranslate.translateKey("Toggle Waypoint")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, stringtranslate.translateKey("Add Waypoint")));
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 50, this.height - 28, 100, 20, stringtranslate.translateKey("gui.done")));
        boolean flag = (this.selectedWaypoint >= 0 && this.selectedWaypoint < this.waypointSlotContainer.getSize());
        this.buttonToggle.enabled = flag;
        this.buttonEdit.enabled = flag;
        this.buttonDelete.enabled = flag;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateToggledState(boolean doubleClicked) {
        if (doubleClicked) {
            WaypointNBTStorage waypointnbtstorage = (WaypointNBTStorage) this.waypointList.get(this.selectedWaypoint);
            if (waypointnbtstorage.enabled.equals("true")) {
                waypointnbtstorage.enabled = "false";
            } else {
                waypointnbtstorage.enabled = "true";
            }
            ((GuiButton)this.buttonList.get(2)).displayString = waypointnbtstorage.enabled.equals("true") ? "Disable Waypoint" : "Enable Waypoint";
            saveWaypointList();
            LiteModRadarBro.Waypoints.clear();
            loadWaypointList();
        } else {
            WaypointNBTStorage waypointnbtstorage = (WaypointNBTStorage) this.waypointList.get(this.selectedWaypoint);
            ((GuiButton)this.buttonList.get(2)).displayString = waypointnbtstorage.enabled.equals("true") ? "Disable Waypoint" : "Enable Waypoint";
        }
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (!par1GuiButton.enabled)
            return;
        if (par1GuiButton.id == 2) {
            String s = ((WaypointNBTStorage)this.waypointList.get(this.selectedWaypoint)).name;
            if (s != null) {
                this.deleteClicked = true;
                StringTranslate stringtranslate = StringTranslate.getInstance();
                String s1 = stringtranslate.translateKey("Are you sure you want to remove this waypoint?");
                String s2 = "'" + s + "' " + stringtranslate.translateKey("selectServer.deleteWarning");
                String s3 = stringtranslate.translateKey("selectServer.deleteButton");
                String s4 = stringtranslate.translateKey("gui.cancel");
                GuiYesNo guiyesno = new GuiYesNo(this, s1, s2, s3, s4, this.selectedWaypoint);
                this.mc.displayGuiScreen((GuiScreen)guiyesno);
            }
        } else if (par1GuiButton.id == 4) {
            updateToggledState(true);
            saveWaypointList();
            LiteModRadarBro.Waypoints.clear();
            loadWaypointList();
        } else if (par1GuiButton.id == 3) {
            this.addClicked = true;
            this.mc.displayGuiScreen(new GuiScreenAddWaypoint(this, this.tempWaypoint = new WaypointNBTStorage(StatCollector.translateToLocal("Waypoint"), "", "true")));
        } else if (par1GuiButton.id == 7) {
            this.editClicked = true;
            WaypointNBTStorage waypointnbtstorage = (WaypointNBTStorage) this.waypointList.get(this.selectedWaypoint);
            this.mc.displayGuiScreen(new GuiScreenAddWaypoint(this, this.tempWaypoint = new WaypointNBTStorage(waypointnbtstorage.name, waypointnbtstorage.coordinates, waypointnbtstorage.enabled)));
        } else if (par1GuiButton.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
        } else {
            this.waypointSlotContainer.actionPerformed(par1GuiButton);
        }
    }

    public void confirmClicked(boolean par1, int par2) {
        if (this.deleteClicked) {
            this.deleteClicked = false;
            if (par1) {
                this.waypointList.remove(par2);
                saveWaypointList();
                LiteModRadarBro.Waypoints.clear();
                loadWaypointList();
            }
            this.mc.displayGuiScreen(this);
        } else if (this.addClicked) {
            this.addClicked = false;
            if (par1) {
                this.waypointList.add(this.tempWaypoint);
                saveWaypointList();
            }
            this.mc.displayGuiScreen(this);
        } else if (this.editClicked) {
            this.editClicked = false;
            if (par1) {
                WaypointNBTStorage waypointnbtstorage = (WaypointNBTStorage) this.waypointList.get(this.selectedWaypoint);
                waypointnbtstorage.name = this.tempWaypoint.name;
                waypointnbtstorage.coordinates = this.tempWaypoint.coordinates;
                saveWaypointList();
                LiteModRadarBro.Waypoints.clear();
                loadWaypointList();
            }
            this.mc.displayGuiScreen(this);
        }
    }

    protected void keyTyped(char par1, int par2) {
        if (par1 == '\r')
            actionPerformed((GuiButton) this.buttonList.get(2));
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.timeTooltip = null;
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        this.waypointSlotContainer.drawScreen(par1, par2, par3);
        drawCenteredString(this.fontRenderer, stringtranslate.translateKey("Waypoint Manager"), this.width / 2, 20, 16777215);
        super.drawScreen(par1, par2, par3);
        if (this.timeTooltip != null)
            func_35325_a(this.timeTooltip, par1, par2);
    }

    protected void func_35325_a(String par1Str, int par2, int par3) {
        if (par1Str == null)
            return;
        int i = par2 + 12;
        int j = par3 - 12;
        int k = this.fontRenderer.getStringWidth(par1Str);
        drawGradientRect(i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
        this.fontRenderer.drawStringWithShadow(par1Str, i, j, -1);
    }

    static List getWaypointList(GuiWaypointManager par0GuiMultiplayer) {
        return par0GuiMultiplayer.waypointList;
    }

    static int setSelectedWaypoint(GuiWaypointManager par0GuiMultiplayer, int par1) {
        return par0GuiMultiplayer.selectedWaypoint = par1;
    }

    static int getSelectedWaypoint(GuiWaypointManager par0GuiMultiplayer) {
        return par0GuiMultiplayer.selectedWaypoint;
    }

    static GuiButton getButtonEdit(GuiWaypointManager par0GuiMultiplayer) {
        return par0GuiMultiplayer.buttonEdit;
    }

    static GuiButton getButtonToggle(GuiWaypointManager par0GuiMultiplayer) {
        return par0GuiMultiplayer.buttonToggle;
    }

    static GuiButton getButtonDelete(GuiWaypointManager par0GuiMultiplayer) {
        return par0GuiMultiplayer.buttonDelete;
    }

    static String setTooltipText(GuiWaypointManager par0GuiMultiplayer, String par1Str) {
        return par0GuiMultiplayer.timeTooltip = par1Str;
    }
}
