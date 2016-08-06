package subaraki.rpginventory.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotJewels extends SlotItemHandler{

	public SlotJewels(RpgPlayerInventory itemHandler, int index, int xPosition, int yPosition) {
		super((IItemHandler) itemHandler, index, xPosition, yPosition);
	}


}
