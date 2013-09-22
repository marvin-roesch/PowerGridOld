package de.paleocrafter.powergrid.tileentity;

import java.util.ArrayList;

import net.minecraftforge.common.ForgeDirection;
import de.paleocrafter.powergrid.energy.network.EnergyNetwork;
import de.paleocrafter.powergrid.energy.network.INetworkComponent;
import de.paleocrafter.powergrid.network.data.TileData;
import de.paleocrafter.powergrid.util.NetworkHelper;
import de.paleocrafter.powergrid.util.WorldVector3D;

/**
 * 
 * PowerGrid
 * 
 * TilePowerGrid
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TilePowerGrid extends TileEntityWithData implements
        INetworkComponent {
    @TileData
    private int networkId;

    public TilePowerGrid() {
        networkId = -1;
    }

    public void validateNetwork() {
        ForgeDirection[] adjacents = NetworkHelper.getAdjacentComponents(
                worldObj, xCoord, yCoord, zCoord);

        if (adjacents != null) {
            ArrayList<Integer> toMerge = new ArrayList<Integer>();
            for (ForgeDirection dir : adjacents) {
                INetworkComponent comp = (INetworkComponent) worldObj
                        .getBlockTileEntity(xCoord + dir.offsetX, yCoord
                                + dir.offsetY, zCoord + dir.offsetZ);
                if (networkId == -1) {
                    if (comp.getNetworkID() != -1) {
                        networkId = comp.getNetworkID();
                    }
                } else if (networkId != comp.getNetworkID()) {
                    toMerge.add(networkId);
                }
            }

            if (toMerge.size() > 0) {
                for (int id : toMerge) {
                    NetworkHelper.mergeNetworks(worldObj, networkId, id);
                }
            }
        } else {
            EnergyNetwork network = new EnergyNetwork(worldObj,
                    new WorldVector3D(xCoord, yCoord, zCoord));
            NetworkHelper.addNetworkToWorld(worldObj, network);
        }
    }

    @Override
    public int getNetworkID() {
        return networkId;
    }

    @Override
    public void setNetworkID(int newID) {
        networkId = newID;
    }
}
