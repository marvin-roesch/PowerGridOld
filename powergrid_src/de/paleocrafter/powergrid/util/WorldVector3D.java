package de.paleocrafter.powergrid.util;

/**
 * 
 * PowerGrid
 * 
 * WorldVector3D
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WorldVector3D {
    private int x, y, z;

    public WorldVector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WorldVector3D) {
            WorldVector3D vector = (WorldVector3D) obj;
            return vector.getX() == this.getX() && vector.getY() == this.getY()
                    && vector.getZ() == this.getZ();
        }
        return false;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z
     *            the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }
}
