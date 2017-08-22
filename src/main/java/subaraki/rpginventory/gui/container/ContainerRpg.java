package subaraki.rpginventory.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;

public class ContainerRpg extends Container {

	private RpgInventoryData inventory;

	public ContainerRpg(EntityPlayer player, RpgInventoryData inv) {
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber) {
		// Shift clicked the players inventory
		int indexPlayerInventory = slotnumber - 6; //6 is isze of custom inv
		if ((indexPlayerInventory) >= 0) {
			ItemStack tmp1 = player.inventory.getStackInSlot(indexPlayerInventory);
			if ((tmp1.getItem() instanceof RpgInventoryItem)) {
				RpgInventoryItem tmp = (RpgInventoryItem) tmp1.getItem();

				switch (tmp.armorType) {
				case NECKLACE:
					if (!getSlot(0).getStack().isEmpty())
						return ItemStack.EMPTY;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory, ItemStack.EMPTY);
					this.slotClick(0, 0, ClickType.PICKUP, player);
					break;
				case CRYSTAL:
					if (!this.getSlot(1).getStack().isEmpty())
						return ItemStack.EMPTY;
					if (tmp1.getItemDamage() == 0)
						return ItemStack.EMPTY;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,ItemStack.EMPTY);
					this.slotClick(1, 0, ClickType.PICKUP, player);
					break;
				case CLOAK:
					if (!this.getSlot(2).getStack().isEmpty())
						return ItemStack.EMPTY;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,ItemStack.EMPTY);
					this.slotClick(2, 0, ClickType.PICKUP, player);
					break;
				case GLOVES:
					if (!this.getSlot(3).getStack().isEmpty())
						return ItemStack.EMPTY;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,ItemStack.EMPTY);
					this.slotClick(3, 0, ClickType.PICKUP, player);
					break;
				case RING:
					if (!this.getSlot(4).getStack().isEmpty() && !this.getSlot(5).getStack().isEmpty())
						return ItemStack.EMPTY;
					player.inventory.setItemStack(player.inventory.getStackInSlot(indexPlayerInventory));
					player.inventory.setInventorySlotContents(indexPlayerInventory,ItemStack.EMPTY);
					if ((this.getSlot(4)).getStack().isEmpty())
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
				if (is.isEmpty()) {
					player.inventory.setInventorySlotContents(i,inventory.getInventory().getStackInSlot(slotnumber));
					inventory.getInventory().setStackInSlot(slotnumber, ItemStack.EMPTY);

					return ItemStack.EMPTY;
				}
				i++;
			}
		}
		return ItemStack.EMPTY;
	}
}
