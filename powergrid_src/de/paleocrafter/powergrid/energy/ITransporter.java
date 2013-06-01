package de.paleocrafter.powergrid.energy;

import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * PowerGrid
 * 
 * ITransporter
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface ITransporter {
    public int getInternalStorage();

    public int getMaximumInput();
    
    public int getOutputPacketSize();
    
    public void sendPacket(int size);
    
    public ForgeDirection[] getConnections();
}
