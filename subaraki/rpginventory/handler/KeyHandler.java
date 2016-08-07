package subaraki.rpginventory.handler;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketOpenRpgInventory;

public class KeyHandler {

	public KeyHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	protected static KeyBinding keyInventory = 
			new KeyBinding("RPG Inventory Key", Keyboard.KEY_R, "Rpg Inventory");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void keys(KeyInputEvent evt) {
		if(keyInventory.isPressed())
			PacketHandler.NETWORK.sendToServer(new PacketOpenRpgInventory());

	}
}
