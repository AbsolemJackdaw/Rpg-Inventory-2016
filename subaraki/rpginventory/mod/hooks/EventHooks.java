package subaraki.rpginventory.mod.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.gui.inventory.RpgPlayerInventory;
import subaraki.rpginventory.gui.inventory.SerializableInventory;

public class EventHooks {

	@SubscribeEvent
	public void stuff(AttachCapabilitiesEvent.Entity event){
		final Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
			event.addCapability(RpgPlayerInventory.PROP_KEY, new SerializableInventory()); 
	}
}
