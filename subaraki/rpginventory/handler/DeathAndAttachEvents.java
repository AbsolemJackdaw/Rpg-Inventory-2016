package subaraki.rpginventory.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.CapabilityInventoryProvider;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;

public class DeathAndAttachEvents {

	public DeathAndAttachEvents() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void attachCapabilitiesForEntities(AttachCapabilitiesEvent.Entity event){
		final Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
			event.addCapability(CapabilityInventoryProvider.KEY, new CapabilityInventoryProvider((EntityPlayer)entity)); 
	}

	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent event){
		if(event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();

			if (!player.world.getGameRules().getBoolean("keepInventory")){
				RpgInventoryData data = RpgInventoryData.get(player);

				for(int slot = 0; slot < data.getInventory().getSlots(); slot++)
				{
					ItemStack stack = data.getInventory().getStackInSlot(slot);
					if(!stack.isEmpty())
					{
						player.dropItem(stack, true, false);
						data.getInventory().setStackInSlot(slot, ItemStack.EMPTY);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onClone(Clone event){

		if (event.getEntityPlayer().world.getGameRules().getBoolean("keepInventory"))
		{
			RpgInventoryData inventory = RpgInventoryData.get(event.getEntityPlayer());
			RpgInventoryData inventory_original = RpgInventoryData.get(event.getOriginal());
			NBTTagCompound tag = (NBTTagCompound) inventory.writeData();
			inventory_original.readData(tag);
		}
	}
}
