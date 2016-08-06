package subaraki.rpginventory.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerRpg extends Container {

	GuiRpg inventory;

	public ContainerRpg(EntityPlayer player, RpgPlayerInventory inv) {

		this.addSlotToContainer(new SlotJewels(inv, 0, 6, 16));// necklace
		this.addSlotToContainer(new SlotJewels(inv, 1, 6, 37));// shield
		this.addSlotToContainer(new SlotJewels(inv, 2, 82, 16));// cloak
		this.addSlotToContainer(new SlotJewels(inv, 3, 82, 38));// gloves
		this.addSlotToContainer(new SlotJewels(inv, 4, 82, 59));// ring
		this.addSlotToContainer(new SlotJewels(inv, 5, 6, 58));// ring
		this.addSlotToContainer(new SlotJewels(inv, 6, 105, 16));// crystal

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
		return false;
	}

}
