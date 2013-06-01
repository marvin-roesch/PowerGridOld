package de.paleocrafter.powergrid.energy;

/**
 * 
 * PowerGrid
 * 
 * IStorage
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface IStorage {
    public int getStorage();

    public int getMaximumStorage();

    public int getMaximumInput();

    public int getMinimumInput();

    public int getOutputPacketSize();

    public void output();
}
