package subaraki.rpginventory.capability.playerinventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.enums.SlotIndex;

public class RpgPlayerInventory {

	private RpgStackHandler inventory;

	EntityPlayer player;

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

	public void setStackInSlot(int slot, ItemStack stack)
	{
		// TODO: Remove old attribute modifiers from player
		inventory.setStackInSlot(slot, stack);
		// TODO: Add new attribute modifiers to player
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
}
