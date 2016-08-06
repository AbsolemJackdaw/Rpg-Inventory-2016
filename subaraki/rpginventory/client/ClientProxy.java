package subaraki.rpginventory.client;

import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.server.ServerProxy;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenders(){
		RpgItems.registerRenders();
	}
	
	@Override
	public void registerColors(){
		RpgItems.registerItemColor();
	}

}
