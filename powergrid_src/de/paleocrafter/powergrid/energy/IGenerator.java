package de.paleocrafter.powergrid.energy;

/**
 * 
 * PowerGrid
 * 
 * IGenerator
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface IGenerator {
    public int getInternalStorage();

    public int getOutputPacketSize();
    
    public void output();
}
