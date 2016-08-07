package subaraki.rpginventory.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketSyncOwnInventory;

public class ContainerRpg extends Container {

	private RpgPlayerInventory inventory;

	public ContainerRpg(EntityPlayer player, RpgPlayerInventory inv) {
		inventory = inv;

		this.addSlotToContainer(new SlotJewels(inventory, 0, 6, 16));// necklace
		this.addSlotToContainer(new SlotJewels(inventory, 1, 6, 37));// crystal
		this.addSlotToContainer(new SlotJewels(inventory, 2, 82, 16));// cloak
		this.addSlotToContainer(new SlotJewels(inventory, 3, 82, 38));// gloves
		this.addSlotToContainer(new SlotJewels(inventory, 4, 82, 59));// ring
		this.addSlotToContainer(new SlotJewels(inventory, 5, 6, 58));// ring

		// ADD THIS FIRST
		// quickbar inventory
		for (int var4 = 0; var4 < 9; ++var4) {
			this.addSlotToContainer(new Slot(player.inventory, var4,
					8 + (var4 * 18), 142));
		}
		// players inventory
		for (int var4 = 0; var4 < 3; ++var4) {
			for (int var5 = 0; var5 < 9; ++var5) {
				this.addSlotToContainer(new Slot(player.inventory,
						(var5 + ((var4 + 1) * 9)), 8 + (var5 * 18),
						84 + (var4 * 18)));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {

//		if (!player.worldObj.isRemote)
//			PacketHandler.NETWORK.sendTo(new PacketSyncOwnInventory(player), (EntityPlayerMP) player);

		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber) {
		// Shift clicked the players inventory
		int indexPlayerInventory = slotnumber - 6; //6 is isze of custom inv
		if ((indexPlayerInventory) >= 0) {
			ItemStack tmp1 = player.inventory.getStackInSlot(indexPlayerInventory);
			if ((tmp1 != null) && (tmp1.getItem() instanceof RpgInventoryItem)) {
				RpgInventoryItem tmp = (RpgInventoryItem) tmp1.getItem();

				switch (tmp.armorType) {
				case NECKLACE:
					if (((SlotJewels)getSlot(0)).getStack() != null)
						return null;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory, null);
					this.slotClick(0, 0, ClickType.PICKUP, player);
					break;
				case CRYSTAL:
					if (((SlotJewels) this.getSlot(1)).getStack() != null)
						return null;
					if (tmp1.getItemDamage() == 0)
						return null;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,null);
					this.slotClick(1, 0, ClickType.PICKUP, player);
					break;
				case CAPE:
					if (((SlotJewels) this.getSlot(2)).getStack() != null)
						return null;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,null);
					this.slotClick(2, 0, ClickType.PICKUP, player);
					break;
				case GLOVES:
					if (((SlotJewels) this.getSlot(3)).getStack() != null)
						return null;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,null);
					this.slotClick(3, 0, ClickType.PICKUP, player);
					break;
				case RING:
					if ((((SlotJewels) this.getSlot(4)).getStack() != null) &&
							(((SlotJewels) this.getSlot(5)).getStack() != null))
						return null;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,null);
					if (((SlotJewels) this.getSlot(4)).getStack() == null)
						this.slotClick(4, 0, ClickType.PICKUP, player);
					else
						this.slotClick(5, 0, ClickType.PICKUP, player);
					break;
				}
			}
		} // Shift clicked the rpgarmor inventory
		else if (inventory!= null) {
			int i = 0;
			for (ItemStack is : player.inventory.mainInventory) {
				if (is == null) {
					player.inventory.setInventorySlotContents(i,inventory.getTheRpgInventory().getStackInSlot(slotnumber));
					inventory.setStackInSlot(slotnumber, null);

					if(!player.worldObj.isRemote){
						((WorldServer)player.worldObj).getEntityTracker().sendToAllTrackingEntity(
								player, PacketHandler.NETWORK.getPacketFrom(/*new PacketSyncOtherInventory(player)*/null));
					}
					return null;
				}
				i++;
			}
		}
		return null;
	}
}
