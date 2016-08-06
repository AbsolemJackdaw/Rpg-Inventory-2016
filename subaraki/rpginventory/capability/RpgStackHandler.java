package subaraki.rpginventory.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.ItemStackHandler;

public class RpgStackHandler extends ItemStackHandler{

	public static final int SLOTS = 6;
	public RpgStackHandler(ItemStack[] stack) {
		super(stack);
	}

	@Override
	protected void validateSlotIndex(int slot) {

		if (slot < 0 || slot >= SLOTS)
			throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.length + ")");

	}
	
	public ItemStack[] getStacks(){
		return stacks;
	}
}
