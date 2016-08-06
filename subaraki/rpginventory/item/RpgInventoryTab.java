package subaraki.rpginventory.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class RpgInventoryTab extends CreativeTabs {

	public RpgInventoryTab() {
		super("rpgi");
	}

	@Override
	public Item getTabIconItem() {
		return RpgItems.gold_gloves;
	}
}
