package de.paleocrafter.powergrid.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.paleocrafter.powergrid.energy.IGenerator;
import de.paleocrafter.powergrid.energy.ITransporter;
import de.paleocrafter.powergrid.util.CableHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * PowerGrid
 * 
 * TileGenerator
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileGenerator extends TileEntity implements IGenerator {

    private int internalStorage;
    private int maximumStorage;
    private int packetSize;
    private ArrayList<ForgeDirection> connectableSides;

    public TileGenerator(int maximumStorage, int packetSize,
            ForgeDirection[] connectableSides) {
        this.maximumStorage = maximumStorage;
        this.packetSize = packetSize;
        if (connectableSides != null)
            this.connectableSides = (ArrayList<ForgeDirection>) Arrays
                    .asList(connectableSides);
        else
            this.connectableSides = (ArrayList<ForgeDirection>) Arrays
                    .asList(ForgeDirection.VALID_DIRECTIONS);
    }

    @Override
    public void updateEntity() {
        if (internalStorage > maximumStorage)
            internalStorage = maximumStorage;
        output();
    }

    @Override
    public int getInternalStorage() {
        return internalStorage;
    }

    @Override
    public int getOutputPacketSize() {
        return packetSize;
    }

    @Override
    public void output() {
        ForgeDirection[] adjacents = CableHelper.getAdjacent(worldObj, xCoord,
                yCoord, zCoord);
        if (adjacents != null) {
            ArrayList<ForgeDirection> connections = (ArrayList<ForgeDirection>) getCommonDirections(
                    connectableSides, Arrays.asList(adjacents));
            if (connections.size() > 0) {
                int splitOutput = (int) Math.ceil(packetSize
                        / connections.size());
                if (internalStorage > 0)
                    for (ForgeDirection dir : connections)
                        if (internalStorage >= splitOutput) {
                            internalStorage -= splitOutput;
                            ((ITransporter) worldObj.getBlockTileEntity(xCoord
                                    + dir.offsetX, yCoord + dir.offsetY, zCoord
                                    + dir.offsetZ)).sendPacket(splitOutput);
                        }
            }
        }
    }

    private List<ForgeDirection> getCommonDirections(
            List<ForgeDirection> list1, List<ForgeDirection> list2) {
        List<ForgeDirection> result = new ArrayList<ForgeDirection>();
        if (list1.size() > list2.size()) {
            for (ForgeDirection dir : list1) {
                if (list2.contains(dir)) {
                    result.add(dir);
                }
            }
        } else {
            for (ForgeDirection dir : list2) {
                if (list1.contains(dir)) {
                    result.add(dir);
                }
            }
        }
        return result;
    }
}
