package subaraki.rpginventory.handler.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgItems;

public class RenderHandler {

	public RenderHandler(){
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerPreRendering(RenderPlayerEvent.Pre evt){

		RpgInventoryData inventory = RpgInventoryData.get(evt.getEntityPlayer());

		ItemStack cloak = inventory.getInventory().getStackInSlot(SlotIndex.SLOT_CLOAK.ordinal());

		if(!cloak.isEmpty() && cloak.hasTagCompound() && cloak.getTagCompound().hasKey("invisible")){
			evt.setCanceled(true);
		}
	}
}
