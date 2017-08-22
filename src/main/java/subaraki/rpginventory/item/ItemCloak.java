package subaraki.rpginventory.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.handler.loot.LootEvent;

public class ItemCloak extends RpgInventoryItem {

	private static final ResourceLocation[] cloakwoolcolors = new ResourceLocation[16];

	public ItemCloak(JewelTypes armorType) {
		super(armorType);
		setHasSubtypes(true);

		for(int i = 0 ; i < 16 ; i ++)
			cloakwoolcolors[i] = new ResourceLocation("blocks/wool_colored_"+EnumDyeColor.byMetadata(i).getName());
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (int i = 0; i < 16; ++i){
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("invisible"))
			return I18n.format("invisibility.cloak");
		return super.getItemStackDisplayName(stack);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		return super.getUnlocalizedName() + "_" + meta ;
	}

	@Override
	public ResourceLocation getRenderOnPlayerTexture(ItemStack stack) {
		return cloakwoolcolors[stack.getMetadata()];
	}
	
	//////////////////////ULT CLOAKS///////////////////////
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
					String theTag = tag.getString(LootEvent.TAG_AFFINITY+i);
					String translate = I18n.format(theTag);
					tooltip.add(translate + ( level == 3 ? " III" : level == 2 ? " II" : " I"));
				}
		}
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
		return stack.hasTagCompound();
	}
}
