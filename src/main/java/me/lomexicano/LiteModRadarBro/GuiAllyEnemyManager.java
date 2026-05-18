package me.lomexicano.LiteModRadarBro;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;

public class GuiAllyEnemyManager extends GuiScreen {
    protected GuiScreen parentGui;

    private int selectedUsernameIndex;

    public static List currentList;

    public static int currentListType;

    private GuiButton buttonAdd;

    private GuiButton buttonDelete;

    private GuiButton buttonAllyList;

    private GuiButton buttonEnemyList;

    private GuiSlotAllyEnemy allyEnemyList;

    Minecraft mc = Minecraft.getMinecraft();

    public FontRenderer fontRenderer = mc.fontRenderer;

    public GuiAllyEnemyManager(GuiScreen par1GuiScreen) {
        this.parentGui = par1GuiScreen;
        currentList = new ArrayList();
        this.selectedUsernameIndex = -1;
        currentListType = 0;
    }

    public void initGui() {
        StringTranslate var1 = StringTranslate.getInstance();
        this.buttonList.add(this.buttonAdd = new GuiButton(0, this.width / 2 - 154, this.height - 28, 100, 20, "Add Ally"));
        this.buttonList.add(this.buttonDelete = new GuiButton(1, this.width / 2 - 50, this.height - 28, 100, 20, "Delete Ally"));
        this.buttonList.add(this.buttonAllyList = new GuiButton(2, this.width / 2 - 154, this.height - 52, 155, 20, "Ally List"));
        this.buttonList.add(this.buttonEnemyList = new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, "Enemy List"));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 4 + 50, this.height - 28, 100, 20, "Done"));
        boolean flag = (this.selectedUsernameIndex >= 0 && this.selectedUsernameIndex < this.allyEnemyList.getSize());
        this.buttonDelete.enabled = flag;
        this.buttonAllyList.enabled = false;
        this.allyEnemyList = new GuiSlotAllyEnemy(this);
        this.allyEnemyList.registerScrollButtons(this.buttonList, 7, 8);
        currentList.clear();
        currentListType = 0;
        LiteModRadarBro.AllyList.clear();
        LiteModRadarBro.EnemyList.clear();
        LiteModRadarBro.loadAllyList();
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.enabled) {
            String playerUsername;
            switch (par1GuiButton.id) {
                case 0:
                    this.mc.displayGuiScreen(new GuiScreenAddAllyEnemy(this, (currentListType == 0) ? 0 : 1));
                    return;
                case 1:
                    playerUsername = (String) currentList.get(getSelectedUsername(this));
                    if (currentListType == 0) {
                        if (LiteModRadarBro.AllyList.contains(playerUsername)) {
                            LiteModRadarBro.AllyList.remove(playerUsername);
                            LiteModRadarBro.saveAllyList();
                            currentList.remove(playerUsername);
                        }
                    } else if (LiteModRadarBro.EnemyList.contains(playerUsername)) {
                        LiteModRadarBro.EnemyList.remove(playerUsername);
                        LiteModRadarBro.saveEnemyList();
                        currentList.remove(playerUsername);
                    }
                    this.buttonDelete.enabled = (this.selectedUsernameIndex >= 0 && this.selectedUsernameIndex < this.allyEnemyList.getSize());
                    return;
                case 2:
                    currentListType = 0;
                    this.buttonAllyList.enabled = false;
                    this.buttonEnemyList.enabled = true;
                    this.buttonAdd.displayString = "Add Ally";
                    this.buttonDelete.displayString = "Delete Ally";
                    currentList.clear();
                    this.buttonDelete.enabled = (this.selectedUsernameIndex >= 0 && this.selectedUsernameIndex < this.allyEnemyList.getSize());
                    LiteModRadarBro.loadAllyList();
                    return;
                case 3:
                    currentListType = 1;
                    this.buttonEnemyList.enabled = false;
                    this.buttonAllyList.enabled = true;
                    this.buttonAdd.displayString = "Add Enemy";
                    this.buttonDelete.displayString = "Delete Enemy";
                    currentList.clear();
                    this.buttonDelete.enabled = (this.selectedUsernameIndex >= 0 && this.selectedUsernameIndex < this.allyEnemyList.getSize());
                    LiteModRadarBro.loadEnemyList();
                    return;
                case 4:
                    this.mc.displayGuiScreen(this.parentGui);
                    LiteModRadarBro.loadAllyList();
                    LiteModRadarBro.loadEnemyList();
                    return;
            }
            this.allyEnemyList.actionPerformed(par1GuiButton);
        }
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.allyEnemyList.drawScreen(par1, par2, par3);
        drawCenteredString(this.fontRenderer, (currentListType == 0) ? "Ally Manager" : "Enemy Manager", this.width / 2, 16, 16777215);
        super.drawScreen(par1, par2, par3);
    }

    public void updateScreen() {
        super.updateScreen();
    }

    static int setSelectedUsername(GuiAllyEnemyManager par0GuiAllyEnemyManager, int par1) {
        return par0GuiAllyEnemyManager.selectedUsernameIndex = par1;
    }

    static int getSelectedUsername(GuiAllyEnemyManager par0GuiAllyEnemyManager) {
        return par0GuiAllyEnemyManager.selectedUsernameIndex;
    }

    static List getAllyList(GuiAllyEnemyManager par0GuiAllyEnemyManager) {
        return currentList;
    }

    static GuiButton getButtonDelete(GuiAllyEnemyManager par0GuiAllyEnemyManager) {
        return par0GuiAllyEnemyManager.buttonDelete;
    }
}
