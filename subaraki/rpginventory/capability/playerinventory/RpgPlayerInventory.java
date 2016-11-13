package subaraki.rpginventory.capability.playerinventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import subaraki.rpginventory.enums.SlotIndex;

public class RpgPlayerInventory {

	private RpgStackHandler inventory;

	private EntityPlayer player;

	public RpgPlayerInventory(){
		inventory = new RpgStackHandler(new ItemStack[6]);
	}

	public EntityPlayer getPlayer() { 
		return player; 
	}

	public void setPlayer(EntityPlayer newPlayer){
		this.player = newPlayer;
	}

	/*
	 * Internal method used by IStorage in the capability
	 */
	public RpgStackHandler getTheRpgInventory(){
		return inventory;
	}

	public NBTBase writeData(){
		//hook into the tagcompound of the ItemStackHandler
		NBTTagCompound tag = getTheRpgInventory().serializeNBT();
		//add our own tags
		//none here
		//save mix of itemstacks and personal tags
		return tag;
	}
	
	public void readData(NBTBase nbt){
		getTheRpgInventory().deserializeNBT((NBTTagCompound)nbt);

	}
	
	public ItemStack getCloak(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_CLOAK.ordinal());
	}
	
	public ItemStack getNecklace(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_NECKLACE.ordinal());
	}
	
	public ItemStack getCrystal(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_CRYSTAL.ordinal());
	}
	
	public ItemStack getRing_1(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_RING1.ordinal());
	}
	
	public ItemStack getRing_2(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_RING2.ordinal());
	}
	
	public ItemStack getGloves(){
		return getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_GLOVES.ordinal());
	}
	
	public void setJewel(SlotIndex type, ItemStack stack){
		getTheRpgInventory().setStackInSlot(type.ordinal(), stack);
	}
}
