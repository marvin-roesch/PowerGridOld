package de.paleocrafter.powergrid.energy.network;

import com.google.common.collect.HashBiMap;

import net.minecraft.world.World;

import de.paleocrafter.powergrid.util.WorldVector3D;

/**
 * 
 * PowerGrid
 * 
 * EnergyNetwork
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EnergyNetwork {
    private HashBiMap<WorldVector3D, INetworkComponent> components;

    public EnergyNetwork(World world, WorldVector3D startingComponent) {
        components = HashBiMap.create();
        if (world.getBlockTileEntity(startingComponent.getX(),
                startingComponent.getY(), startingComponent.getZ()) instanceof INetworkComponent) {
            components
                    .put(startingComponent, (INetworkComponent) world
                            .getBlockTileEntity(startingComponent.getX(),
                                    startingComponent.getY(),
                                    startingComponent.getZ()));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void updateNetworkIDs(int newId) {
        for (INetworkComponent comp : components.values()) {
            comp.setNetworkID(newId);
        }
    }

    public void mergeComponents(EnergyNetwork newNetwork) {
        for (INetworkComponent comp : components.values()) {
            WorldVector3D coords = components.inverse().get(comp);
            newNetwork.addComponent(coords.getX(), coords.getY(),
                    coords.getZ(), comp);
        }
    }

    public void addComponent(int x, int y, int z, INetworkComponent comp) {
        components.put(new WorldVector3D(x, y, z), comp);
    }
}
