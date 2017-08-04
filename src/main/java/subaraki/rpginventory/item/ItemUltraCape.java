package subaraki.rpginventory.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.handler.loot.LootEvent;
import subaraki.rpginventory.item.RpgItems.InventoryItem;

public class ItemUltraCape extends RpgInventoryItem {

	public ItemUltraCape(JewelTypes armorType, InventoryItem le) {
		super(armorType, le);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		if(stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.hasKey(LootEvent.TAG_AMOUNT))
			{
				int nr = tag.getInteger(LootEvent.TAG_AMOUNT);
				return nr == 1 ? EnumRarity.COMMON : nr == 2 ? EnumRarity.UNCOMMON : EnumRarity.EPIC; 
			}
		}

		return super.getRarity(stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, world, tooltip, advanced);

		if(stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();

			for(int i = 0; i < 3; i++)
				if(tag.hasKey(LootEvent.TAG_AFFINITY+i))
				{
					int level = tag.getInteger(LootEvent.TAG_LVL+i);
					tooltip.add(tag.getString(LootEvent.TAG_AFFINITY+i) + ( level == 3 ? " III" : level == 2 ? " II" : " I"));
				}
		}
	}
}
