package me.lomexicano.LiteModRadarBro.actions;

import me.lomexicano.LiteModRadarBro.ModuleInfo;
import net.eq2online.macros.scripting.ScriptAction;
import net.eq2online.macros.scripting.ScriptCore;
import net.eq2online.macros.scripting.api.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagCompound;
import org.json.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import net.eq2online.macros.gui.helpers.HelperContainerSlots;

@APIVersion(ModuleInfo.API_VERSION)
public class CloudScriptActionGetSlotItemInv extends ScriptAction {

    public ScriptActionGetSlotItemInv() {
        super("getslotiteminv");
    }

    @Override
    public IReturnValue executeAction(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {

        String itemName = "air";
        String itemID = "0";
        int itemMetaData = 0;
        int itemStack = 0;
        String itemNbtTags = "";
        String itemEnchantments = "";
        ItemStack itemNoInv = null;
        extraLog2 = "";

        if (params.length >= 1) {
            int slotId = Math.max(0, ScriptCore.tryParseInt(ScriptCore.parseVars(provider, macro, params[0], false), 0));

            int slot = -1;
            if (HelperContainerSlots.currentScreenIsContainer(Minecraft.getMinecraft())) {
                // Isso aqui é só pra deixar ler os slots da craftingtable do inventário se ele estiver aberto;
                // Ou para ler baús e tal;
                slot = slotId;
                itemNoInv = HelperContainerSlots.getSlotStack(slotId);
            } else {
                if ((slotId >= 36) && (slotId <= 44))
                    //slots da hotbar são 0-8;
                    slot = slotId - 36;
                else if ((slotId >= 9) && (slotId <= 35))
                    //demais slots do inventário têm o número certo;
                    slot = slotId;
                else if ((slotId >= 5) && (slotId <= 8))
                    // Slots de armaduras são tratados como 39-36 (capacete-bota);
                    slot = 44 - slotId;
                if (slot >= 0)
                    itemNoInv = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(slot);
            }

            if ((itemNoInv != null)&&(slot >= 0)) {
                // Nome do item;
                itemName = itemNoInv.getDisplayName();

                // ID do item;
                itemID = "" + itemNoInv.itemID;

                // MetaData do item;
                itemMetaData = itemNoInv.getItemDamage();

                // Quantidade de itens;
                itemStack = itemNoInv.stackSize;

                // Tags NBT, em formato JSON;
                itemNbtTags = getNbtTagsFromItemStack(itemNoInv, false);

                // Encantamentos, separados por vírgula;
                itemEnchantments = getEnchantmentsFromItemStack(itemNoInv);

            }

            //provider.actionAddChatMessage(extraLog2);

            String[] array_para_retorno = {itemID, String.valueOf(itemStack), String.valueOf(itemMetaData), itemName, itemNbtTags, itemEnchantments};
            if (params.length >= 2) {
                for (int paramIndex = 1; paramIndex < params.length; ++paramIndex) {
                    try {
                        ScriptCore.setVariable(provider, macro, params[paramIndex], array_para_retorno[paramIndex - 1]);
                    } catch (Exception e) {
                        String retorno = "Erro inesperado: " + e.getMessage();
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        retorno += "\n" + sw.toString();
                        provider.actionAddChatMessage(retorno);
                    }
                }
            }
        }
        return new ReturnValue(true);
    }

    public String extraLog;
    public String extraLog2;

    public String getNbtTagsFromItemStack(ItemStack itemNoInv, boolean breakLines) {
        extraLog = "";
        JSONObject json = new JSONObject();
        String itemNbtTags = "";
        if (itemNoInv.isItemStackDamageable()) {
            JSONObject subJsonDamage = new JSONObject();
            int itemMaxDamage = itemNoInv.getMaxDamage();
            int itemDamage = itemNoInv.getItemDamage();
            subJsonDamage.put("totalDamage", itemDamage);
            subJsonDamage.put("maxDamage", itemMaxDamage);
            double percentDamage = Math.round((double) itemDamage / itemMaxDamage * 10000) / 100.0;
            subJsonDamage.put("percentualDamage", percentDamage);
            subJsonDamage.put("durability", itemMaxDamage-itemDamage);

            json.put("durability", subJsonDamage);
        }

        // Todas as informações visíveis ao passar o mouse sobre o item (toolTip);
        String itemToolTip = ((List) itemNoInv.getTooltip(Minecraft.getMinecraft().thePlayer, true)).toString();
        extraLog2 += "toolTip: " + itemToolTip;

        // Remover "[" do começo e "]" do final;
        if (itemToolTip.startsWith("[") && itemToolTip.endsWith("]")) {
            itemToolTip = itemToolTip.substring(1, itemToolTip.length() - 1);
            extraLog2+=",retirou os colchetes";
        }

        // Transformar em um array;
        String[] itemToolTipArray = new String[0];
        if (itemToolTip.contains(",")) {
            itemToolTipArray = itemToolTip.split(",");
            extraLog2 += ",separou por vírgulas";
        } else {
            itemToolTipArray = new String[]{itemToolTip};
        }

        // Colocar em formato json;
        if (itemToolTipArray.length > 0) {
            extraLog2 += ",tamanho era maior que 0 (" + itemToolTipArray.length + ")";
            JSONArray itemToolTipJsonArray = new JSONArray();
            for (String toolTip : itemToolTipArray) {
                itemToolTipJsonArray.put(toolTip);
            }
            JSONObject itemToolTipJsonObject = new JSONObject();
            itemToolTipJsonObject.put("tooltip", itemToolTipJsonArray);
            json.put("tooltip", itemToolTipJsonObject);
        }



        NBTTagCompound nbt = itemNoInv.getTagCompound();
        if (nbt != null) {
            try {
                for (String key : getKeySet(nbt)) {
                    NBTBase tag = nbt.getTag(key);
                    if (tag instanceof NBTTagList) {
                        extraLog+=",InstanceOfNBTTagList";
                        JSONArray listJson = new JSONArray();
                        NBTTagList tagList = (NBTTagList) tag;
                        for (int i = 0; i < tagList.tagCount(); i++) {
                            NBTTagCompound subTag = getCompoundTagAtIndex(tagList, i);
                            if (subTag == null)
                                continue;
                            JSONObject subJson = new JSONObject();
                            for (String subKey : getKeySet(subTag)) {
                                NBTBase subTagBase = subTag.getTag(subKey);
                                if (subTagBase == null)
                                    continue;
                                extraLog+=",key:"+key;
                                extraLog+=",subKey:"+subKey;
                                extraLog+=",subTagBase:"+subTagBase.toString();
                                subJson.put(subKey, subTagBase.toString());

                                // Also add the name of the enchantment to the subJson;
                                if (subJson.has("lvl")&&(subJson.has("id"))) {
                                    String id = subTagBase.toString();
                                    String lvl = subJson.getString("lvl");
                                    int idInt = tryParseInt(id,-1);
                                    int lvlInt = tryParseInt(lvl,-1);

                                    if((idInt != -1)&&(lvlInt != -1)) {
                                        String nomeDoEncantamento = Enchantment.enchantmentsList[idInt].getTranslatedName(lvlInt);
                                        subJson.put("name", nomeDoEncantamento);
                                    }
                                }

                            }
                            listJson.put(subJson);
                        }
                        json.put(key, listJson);
                    } else if (tag instanceof NBTTagCompound) {
                        extraLog+=",InstanceOfNBTTagCompound";
                        JSONObject subJson = new JSONObject();
                        NBTTagCompound subTag = (NBTTagCompound) tag;
                        for (String subKey : getKeySet(subTag)) {
                            NBTBase subTagBase = subTag.getTag(subKey);
                            if (subTagBase == null)
                                continue;
                            extraLog+=",key:"+key;
                            extraLog+=",subKey:"+subKey;
                            extraLog+=",subTagBase:"+subTagBase.toString();
                            subJson.put(subKey, subTagBase.toString());
                        }
                        json.put(key, subJson);
                    } else {
                        json.put(key, tag.toString());
                    }
                }
            } catch (Exception e) {
                String retorno = "An exception occurred: " + e.getMessage();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                retorno += "\n" + sw.toString();
                return retorno;
            }
        }
        itemNbtTags = json.toString();
        if (breakLines) {
            itemNbtTags = itemNbtTags.replaceAll(",", ",\n");
        }
        return itemNbtTags;
    }

    public String getEnchantmentsFromItemStack(ItemStack itemNoInv) {
        String itemEnchantments = "";
        NBTTagList listaDeEncantamentos = itemNoInv.getEnchantmentTagList();
        if (itemNoInv.getItem() != null && itemNoInv.getItem() instanceof ItemEnchantedBook) {
            listaDeEncantamentos = ((ItemEnchantedBook) itemNoInv.getItem()).func_92110_g(itemNoInv);
        }

        if (listaDeEncantamentos != null) {
            for (int i = 0; i < listaDeEncantamentos.tagCount(); ++i) {
                short enchantmentId = ((NBTTagCompound) listaDeEncantamentos.tagAt(i)).getShort("id");
                short enchantmentLevel = ((NBTTagCompound) listaDeEncantamentos.tagAt(i)).getShort("lvl");
                if (Enchantment.enchantmentsList[enchantmentId] != null) {
                    if (!itemEnchantments.isEmpty())
                        itemEnchantments += ",";
                    itemEnchantments += Enchantment.enchantmentsList[enchantmentId].getTranslatedName(enchantmentLevel);
                }
            }
            return itemEnchantments;
        }
        return itemEnchantments;
    }

    public static Set<String> getKeySet(NBTTagCompound tagCompound) {
        Set<String> keySet = new HashSet<String>();
        if (tagCompound != null) {
            for (Iterator<?> iterator = tagCompound.getTags().iterator(); iterator.hasNext();) {
                NBTBase tag = (NBTBase) iterator.next();
                keySet.add(tag.getName());
            }
        }
        return keySet;
    }

    public static NBTBase getTagAtIndex(NBTTagList tagList, int index) {
        return tagList.tagAt(index);
    }

    public static NBTTagCompound getCompoundTagAtIndex(NBTTagList tagList, int index) {
        NBTBase tag = tagList.tagAt(index);
        if (tag instanceof NBTTagCompound) {
            return (NBTTagCompound) tag;
        } else {
            return null;
        }
    }


    public int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public void onInit() {
        registerAction((IScriptAction)this);
    }
}