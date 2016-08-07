package subaraki.rpginventory.handler;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.render.RenderCapeLayer;

public class RenderHandler {

	private ModelBiped main;

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
