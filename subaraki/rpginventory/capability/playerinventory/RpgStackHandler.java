package subaraki.rpginventory.capability.playerinventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class RpgStackHandler extends ItemStackHandler{

	public static final int SLOTS = 6;
	
	public RpgStackHandler() {
		super(SLOTS);
	}

}
