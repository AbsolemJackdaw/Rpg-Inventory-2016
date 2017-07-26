package subaraki.rpginventory.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RpgInventoryTab extends CreativeTabs {

	public RpgInventoryTab() {
		super("rpgi");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack (RpgItems.gold_gloves,1);
	}
}
