package subaraki.rpginventory.gui.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.rpginventory.gui.GuiRpg;
import subaraki.rpginventory.gui.inventory.RpgPlayerInventory;
import subaraki.rpginventory.gui.inventory.RpgPlayerInventory.RpgStackHandler;

public class ContainerRpg extends Container {

	GuiRpg inventory;

	public ContainerRpg(EntityPlayer player, RpgPlayerInventory inv) {
		RpgStackHandler sh = inv.getInventory(player);
		
		this.addSlotToContainer(new SlotJewels(sh, 0, 6, 16));// necklace
		this.addSlotToContainer(new SlotJewels(sh, 1, 6, 37));// crystal
		this.addSlotToContainer(new SlotJewels(sh, 2, 82, 16));// cloak
		this.addSlotToContainer(new SlotJewels(sh, 3, 82, 38));// gloves
		this.addSlotToContainer(new SlotJewels(sh, 4, 82, 59));// ring
		this.addSlotToContainer(new SlotJewels(sh, 5, 6, 58));// ring

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
