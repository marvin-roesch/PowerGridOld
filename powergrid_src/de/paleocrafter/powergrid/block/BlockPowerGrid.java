package de.paleocrafter.powergrid.block;

import de.paleocrafter.powergrid.tileentity.TilePowerGrid;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * PowerGrid
 * 
 * BlockPowerGrid
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockPowerGrid extends Block implements ITileEntityProvider {

    public BlockPowerGrid(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TileEntity tileRaw = world.getBlockTileEntity(x, y, z);
            if (tileRaw instanceof TilePowerGrid) {
                TilePowerGrid tile = (TilePowerGrid) tileRaw;
                tile.validateNetwork();
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TilePowerGrid();
    }

}
