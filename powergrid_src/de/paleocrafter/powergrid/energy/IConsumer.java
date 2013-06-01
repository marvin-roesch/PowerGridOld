package de.paleocrafter.powergrid.energy;

/**
 *
 * PowerGrid
 *
 * IConsumer
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IConsumer {
    public int getInternalStorage();
    
    public int getMaximumInput();
    
    public int getMinimumInput();
}
