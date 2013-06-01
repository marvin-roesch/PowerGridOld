package de.paleocrafter.powergrid.util;

import java.util.ArrayList;

import de.paleocrafter.powergrid.energy.ITransporter;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * PowerGrid
 * 
 * CableHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CableHelper {
    public static ForgeDirection[] getAdjacent(World world, int x, int y, int z) {
        ArrayList<ForgeDirection> temp = new ArrayList<ForgeDirection>();
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            if (world.getBlockTileEntity(x + dir.offsetX, y + dir.offsetY, z
                    + dir.offsetZ) instanceof ITransporter)
                temp.add(dir);
        return temp.size() > 0 ? temp.toArray(new ForgeDirection[temp.size()])
                : null;
    }
}
