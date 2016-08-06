package subaraki.rpginventory.mod.client;

import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.mod.server.ServerProxy;

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
