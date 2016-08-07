package subaraki.rpginventory.gui.container;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.capability.playerinventory.RpgStackHandler;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.item.RpgInventoryItem;

public class SlotJewels extends SlotItemHandler{

	public SlotJewels(RpgPlayerInventory itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler.getTheRpgInventory(), index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {

		if(stack == null)
			return false;
		if(stack.getItem()== null)
			return false;

		RpgInventoryItem jewel = (RpgInventoryItem) stack.getItem();

		switch (getSlotIndex()) {
		case 0:
			return jewel.armorType == JewelTypes.NECKLACE;
		case 1:
			return jewel.armorType == JewelTypes.CRYSTAL;
		case 2: 
			return jewel.armorType == JewelTypes.CAPE;
		case 3:
			return jewel.armorType == JewelTypes.GLOVES;
		case 4:
		case 5://falltrough : same return for 4 and 5 !
			return jewel.armorType == JewelTypes.RING;
		}
		return false;
	}
}
