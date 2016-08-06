package subaraki.rpginventory.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {

	private static final String CHANNEL = "Rpg_Inventory_Channel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
}
