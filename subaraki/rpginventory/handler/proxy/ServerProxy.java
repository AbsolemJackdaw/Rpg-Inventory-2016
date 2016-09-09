package subaraki.rpginventory.handler.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy {

	public void registerRenders(){/*empty for serverside*/}
	public void registerColors(){/*empty for serverside*/}
	public EntityPlayer getClientPlayer(){return null;}
	public World getClientWorld(){return null;}
	public void addRenderLayers(){}
	public void registerClientEvents() {};
}
