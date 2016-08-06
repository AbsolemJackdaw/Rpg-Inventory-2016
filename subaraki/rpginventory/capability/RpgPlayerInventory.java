package subaraki.rpginventory.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

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
		System.out.println(inventory.getSlots());
		System.out.println(inventory.getStacks());

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
}
