package subaraki.rpginventory.handler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.handler.proxy.ClientProxy;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketOpenRpgInventory;

public class KeyHandler {

	public KeyHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void keys(KeyInputEvent evt) {
		if(ClientProxy.keyInventory.isPressed())
			PacketHandler.NETWORK.sendToServer(new PacketOpenRpgInventory());
	}
}
