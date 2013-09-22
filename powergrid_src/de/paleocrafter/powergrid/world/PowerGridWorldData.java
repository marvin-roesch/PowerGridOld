package de.paleocrafter.powergrid.world;

import java.util.HashMap;

import de.paleocrafter.powergrid.energy.network.EnergyNetwork;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

/**
 * 
 * PowerGrid
 * 
 * PowerGridWorldData
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PowerGridWorldData extends WorldSavedData {

    public static final String IDENTIFIER = "powergrid";

    private int latestId;

    private HashMap<Integer, EnergyNetwork> networks;

    public PowerGridWorldData() {
        super(IDENTIFIER);
        networks = new HashMap<Integer, EnergyNetwork>();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
    }

    public void addNetwork(EnergyNetwork net) {
        networks.put(latestId, net);
        latestId++;
    }

    public EnergyNetwork getNetwork(int id) {
        if (networks.containsKey(id))
            return networks.get(id);
        return null;
    }
    
    public void removeNetwork(int id) {
        if(networks.containsKey(id))
            networks.remove(id);
    }

    public static PowerGridWorldData get(World world) {
        PowerGridWorldData data = (PowerGridWorldData) world.loadItemData(
                PowerGridWorldData.class, IDENTIFIER);
        if (data == null) {
            data = new PowerGridWorldData();
            world.setItemData(IDENTIFIER, data);
        }

        return data;
    }
}
