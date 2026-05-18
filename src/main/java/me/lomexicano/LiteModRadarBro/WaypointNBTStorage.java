package me.lomexicano.LiteModRadarBro;

import net.minecraft.src.NBTTagCompound;

public class WaypointNBTStorage {
    public String name;

    public String coordinates;

    public String enabled;

    public WaypointNBTStorage(String par1Str, String par2Str, String par3Str) {
        this.name = par1Str;
        this.coordinates = par2Str;
        this.enabled = par3Str;
    }

    public NBTTagCompound getCompoundTag() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("name", this.name);
        nbttagcompound.setString("coordinates", this.coordinates);
        nbttagcompound.setString("enabled", this.enabled);
        return nbttagcompound;
    }

    public static WaypointNBTStorage createWaypointNBTStorage(NBTTagCompound par0NBTTagCompound) {
        if (!LiteModRadarBro.Waypoints.contains(par0NBTTagCompound.getString("coordinates") + "," + par0NBTTagCompound.getString("name") + "," + par0NBTTagCompound.getString("enabled")))
            LiteModRadarBro.Waypoints.add(par0NBTTagCompound.getString("coordinates") + "," + par0NBTTagCompound.getString("name") + "," + par0NBTTagCompound.getString("enabled"));
        return new WaypointNBTStorage(par0NBTTagCompound.getString("name"), par0NBTTagCompound.getString("coordinates"), par0NBTTagCompound.getString("enabled"));
    }
}
