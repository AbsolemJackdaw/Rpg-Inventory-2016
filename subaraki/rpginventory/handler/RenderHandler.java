package subaraki.rpginventory.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgItems;

public class RenderHandler {

	public RenderHandler(){
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerPreRendering(RenderPlayerEvent.Pre evt){

		RpgPlayerInventory inventory = evt.getEntityPlayer().getCapability(RpgInventoryCapability.CAPABILITY, null);

		ItemStack cloak = inventory.getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_CLOAK.ordinal());

		if(cloak != null){
			if(cloak.getItem() == RpgItems.cloak_Invisible) {
				evt.setCanceled(true);
			}
		}
	}

}
