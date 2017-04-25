package subaraki.rpginventory.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.CapabilityInventoryProvider;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;

public class EventHooks {

	public EventHooks() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void attachCapabilitiesForEntities(AttachCapabilitiesEvent.Entity event){
		final Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
			event.addCapability(CapabilityInventoryProvider.KEY, new CapabilityInventoryProvider((EntityPlayer)entity)); 
	}

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event){
		if (!(event.getEntityLiving() instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.getEntityLiving();

		if (player == null)
			return;

	}

	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent event){
		if(event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();

			if (!player.world.getGameRules().getBoolean("keepInventory")){
				RpgPlayerInventory inventory = player.getCapability(RpgInventoryCapability.CAPABILITY, null);

				for(int slot = 0; slot < inventory.getTheRpgInventory().getSlots(); slot++){
					ItemStack stack = inventory.getTheRpgInventory().getStackInSlot(slot);
					if(stack != ItemStack.EMPTY){
						player.dropItem(stack, true, false);
						inventory.getTheRpgInventory().setStackInSlot(slot, ItemStack.EMPTY);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onClone(Clone event){

		if (event.getEntityPlayer().world.getGameRules().getBoolean("keepInventory")){
			RpgPlayerInventory inventory = event.getEntityPlayer().getCapability(RpgInventoryCapability.CAPABILITY, null);
			RpgPlayerInventory inventory_original = event.getOriginal().getCapability(RpgInventoryCapability.CAPABILITY, null);
			NBTTagCompound tag = (NBTTagCompound) inventory.writeData();
			inventory_original.readData(tag);
		}
	}
}
