package subaraki.rpginventory.handler.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import subaraki.rpginventory.item.RpgItems;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenders(){
		RpgItems.registerRenders();
	}
	
	@Override
	public void registerColors(){
		RpgItems.registerItemColor();
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}
