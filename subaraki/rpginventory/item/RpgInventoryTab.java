package subaraki.rpginventory.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RpgInventoryTab extends CreativeTabs {

	private static ItemStack tabicon = new ItemStack (RpgItems.gold_gloves,1);
	
	public RpgInventoryTab() {
		super("rpgi");
	}

	@Override
	public ItemStack getTabIconItem() {
		return tabicon;
	}
}
