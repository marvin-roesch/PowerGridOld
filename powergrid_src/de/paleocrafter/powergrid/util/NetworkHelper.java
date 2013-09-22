package de.paleocrafter.powergrid.util;

import java.util.ArrayList;

import de.paleocrafter.powergrid.energy.network.EnergyNetwork;
import de.paleocrafter.powergrid.energy.network.INetworkComponent;
import de.paleocrafter.powergrid.world.PowerGridWorldData;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * PowerGrid
 * 
 * NetworkHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class NetworkHelper {
    public static EnergyNetwork getNetworkFromWorld(World world, int id) {
        return PowerGridWorldData.get(world).getNetwork(id);
    }

    public static void addNetworkToWorld(World world, EnergyNetwork network) {
        PowerGridWorldData.get(world).addNetwork(network);
    }

    public static void removeNetworkFromWorld(World world, int id) {
        PowerGridWorldData.get(world).removeNetwork(id);
    }

    public static ForgeDirection[] getAdjacentComponents(World world, int x,
            int y, int z) {
        ArrayList<ForgeDirection> temp = new ArrayList<ForgeDirection>();
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            if (world.getBlockTileEntity(x + dir.offsetX, y + dir.offsetY, z
                    + dir.offsetZ) instanceof INetworkComponent)
                temp.add(dir);
        return temp.size() > 0 ? temp.toArray(new ForgeDirection[temp.size()])
                : null;
    }

    public static void mergeNetworks(World world, int mergeTo, int mergeFrom) {
        EnergyNetwork networkTo = getNetworkFromWorld(world, mergeTo);
        EnergyNetwork networkFrom = getNetworkFromWorld(world, mergeFrom);
        
        networkFrom.updateNetworkIDs(mergeTo);
        networkFrom.mergeComponents(networkTo);
        
        removeNetworkFromWorld(world, mergeFrom);
    }
}
