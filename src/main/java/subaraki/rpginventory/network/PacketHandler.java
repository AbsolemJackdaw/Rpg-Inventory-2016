package subaraki.rpginventory.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import subaraki.rpginventory.network.PacketInventoryToClient.PacketInventoryToClientHandler;
import subaraki.rpginventory.network.PacketInventoryToServerAndTrackedPlayers.HandlerPacketInventoryToServerAndTrackedPlayers;
import subaraki.rpginventory.network.PacketInventoryToTrackedPlayer.PacketInventoryToTrackedPlayerHandler;
import subaraki.rpginventory.network.PacketOpenRpgInventory.PacketOpenRpgInventoryHandler;

public class PacketHandler {

	private static final String CHANNEL = "RpgInventoryChannel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
	
	public PacketHandler() {
		//register packets and capability before all handlers. some need this to not be null !
		PacketHandler.NETWORK.registerMessage(PacketOpenRpgInventoryHandler.class, PacketOpenRpgInventory.class, 0, Side.SERVER);
		PacketHandler.NETWORK.registerMessage(PacketInventoryToTrackedPlayerHandler.class, PacketInventoryToTrackedPlayer.class, 1, Side.CLIENT);
		PacketHandler.NETWORK.registerMessage(PacketInventoryToClientHandler.class, PacketInventoryToClient.class, 2, Side.CLIENT);
		PacketHandler.NETWORK.registerMessage(HandlerPacketInventoryToServerAndTrackedPlayers.class, PacketInventoryToServerAndTrackedPlayers.class, 3, Side.SERVER);
	}
}