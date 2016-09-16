package subaraki.rpginventory.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.api.ModeledArmor;
import subaraki.rpginventory.capability.playerinventory.CapabilityInventoryProvider;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.mod.RpgInventory;

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

		determinePlayerClass(player);
	}

	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent event){
		if(event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();

			if (!player.worldObj.getGameRules().getBoolean("keepInventory")){
				RpgPlayerInventory inventory = player.getCapability(RpgInventoryCapability.CAPABILITY, null);

				for(int i = 0; i < inventory.getTheRpgInventory().getStacks().length; i++){
					ItemStack stack = inventory.getTheRpgInventory().getStacks()[i];
					if(stack != null){
						player.dropItem(stack, true, false);
						inventory.getTheRpgInventory().getStacks()[i] = null;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onClone(Clone event){

		if (event.getEntityPlayer().worldObj.getGameRules().getBoolean("keepInventory")){
			RpgPlayerInventory inventory = event.getEntityPlayer().getCapability(RpgInventoryCapability.CAPABILITY, null);
			RpgPlayerInventory inventory_original = event.getOriginal().getCapability(RpgInventoryCapability.CAPABILITY, null);
			NBTTagCompound tag = (NBTTagCompound) inventory.writeData();
			inventory_original.readData(tag);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final int BOOTS = 36;
	public static final int LEGS = 37;
	public static final int CHEST = 38;
	public static final int HELM = 39;

	private void determinePlayerClass(EntityPlayer player){

		String classname = "noClass";
		String shieldName = "_noShield";

		if (player.inventory.offHandInventory[0] != null)
			if(player.inventory.offHandInventory[0].getItem() instanceof ItemShield){
				shieldName = RpgInventory.shielded_vanilla;
				RpgInventory.playerClass = classname+shieldName;//noclass vanilla shield
			}else
				RpgInventory.playerClass = classname+shieldName;//noclass noshield

		/*checking armor...*/
		for (ItemStack is : player.inventory.armorInventory) {
			if (is == null) {
				RpgInventory.playerClass = classname+shieldName;//noclass (no)vanilla shield
				return;//if one of the items is null, jump out. all items need to be worn
			}
			else // if there is one item that is no AbstractArmor, skip the setting of the player class
				if (!(is.getItem() instanceof ModeledArmor)){
					RpgInventory.playerClass = classname+shieldName;//noclass (no)vanilla shield
					return;//no need to check for the next if one item is not class armour
				}
		}
		//at this point, all armour is class armour ! no need to check for it.

		//check if all armor is from the same class
		String a = ((ModeledArmor) player.inventory.getStackInSlot(HELM).getItem()).armorClassName();
		String b = ((ModeledArmor) player.inventory.getStackInSlot(CHEST).getItem()).armorClassName();
		String c = ((ModeledArmor) player.inventory.getStackInSlot(LEGS).getItem()).armorClassName();
		String d = ((ModeledArmor) player.inventory.getStackInSlot(BOOTS).getItem()).armorClassName();

		if(a.equals(b) && a.equals(c) && a.equals(d))
			classname = a;
		else{
			RpgInventory.playerClass = classname+shieldName;//noclass (no)vanilla shield
			return; //if there is a difference, jump out
		}
		//check if any other shields
		ModeledArmor helm = ((ModeledArmor) player.inventory.getStackInSlot(HELM).getItem());
		if (player.inventory.offHandInventory[0] != null && !shieldName.equals(RpgInventory.shielded_vanilla)) 
			if (player.inventory.offHandInventory[0].getItem().equals(helm.getLinkedShieldItem()))
				shieldName = RpgInventory.shielded_class;

		if(!RpgInventory.playerClass.equals(classname+shieldName))
			RpgInventory.playerClass = classname+shieldName;
	}
}
