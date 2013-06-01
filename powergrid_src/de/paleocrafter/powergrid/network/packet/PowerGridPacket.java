package de.paleocrafter.powergrid.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import de.paleocrafter.powergrid.PowerGrid;

/**
 * 
 * PowerGrid
 * 
 * PowerGridPacket
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class PowerGridPacket {
    private static final BiMap<Integer, Class<? extends PowerGridPacket>> idMap;

    static {
        ImmutableBiMap.Builder<Integer, Class<? extends PowerGridPacket>> builder = ImmutableBiMap
                .builder();

        idMap = builder.build();
    }

    public static PowerGridPacket constructPacket(int packetId)
            throws ProtocolException, ReflectiveOperationException {
        Class<? extends PowerGridPacket> clazz = idMap.get(Integer
                .valueOf(packetId));
        if (clazz == null) {
            throw new ProtocolException("Unknown Packet Id!");
        } else {
            return clazz.newInstance();
        }
    }

    @SuppressWarnings("serial")
    public static class ProtocolException extends Exception {

        public ProtocolException() {
        }

        public ProtocolException(String message, Throwable cause) {
            super(message, cause);
        }

        public ProtocolException(String message) {
            super(message);
        }

        public ProtocolException(Throwable cause) {
            super(cause);
        }
    }

    public final int getPacketId() {
        if (idMap.inverse().containsKey(getClass())) {
            return idMap.inverse().get(getClass()).intValue();
        } else {
            throw new RuntimeException("Packet " + getClass().getSimpleName()
                    + " is missing a mapping!");
        }
    }

    public final Packet makePacket() throws IllegalArgumentException {
        if (PowerGrid.PACKET_CHANNEL != null) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeByte(getPacketId());
            write(out);
            return PacketDispatcher.getPacket(PowerGrid.PACKET_CHANNEL,
                    out.toByteArray());
        }
        throw new IllegalArgumentException(
                "You have to define a channel for the PowerGrid packets first!");
    }

    public abstract void write(ByteArrayDataOutput out);

    public abstract void read(ByteArrayDataInput in);

    public abstract void execute(EntityPlayer player, Side side);
}
