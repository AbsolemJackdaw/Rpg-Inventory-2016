package subaraki.rpginventory.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import subaraki.rpginventory.network.PacketOpenRpgInventory.HandlerOpenRpgInventory;
import subaraki.rpginventory.network.PacketSyncOtherInventory.HandlerSyncOtherInventory;
import subaraki.rpginventory.network.PacketSyncOwnInventory.HandlerSyncOwnInventory;

public class PacketHandler {

	private static final String CHANNEL = "RpgInventoryChannel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
	
	public PacketHandler() {
		//register packets and capability before all handlers. some need this to not be null !
		PacketHandler.NETWORK.registerMessage(HandlerOpenRpgInventory.class, PacketOpenRpgInventory.class, 0, Side.SERVER);
		PacketHandler.NETWORK.registerMessage(HandlerSyncOtherInventory.class, PacketSyncOtherInventory.class, 1, Side.CLIENT);
		PacketHandler.NETWORK.registerMessage(HandlerSyncOwnInventory.class, PacketSyncOwnInventory.class, 2, Side.CLIENT);
	}
}
