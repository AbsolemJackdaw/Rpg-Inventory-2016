package subaraki.rpginventory.item;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.item.RpgItems.LocalizeEnum;
import subaraki.rpginventory.mod.RpgInventory;

public class RpgInventoryItem extends Item {

	public JewelTypes armorType;

	private final ResourceLocation RENDER3D_TEXTURE;

	/**for capes*/
	public final int colorState;

	private String modelLocation;
	public RpgInventoryItem(JewelTypes armorType, int maxDamage, int color, LocalizeEnum le) {
		super();

		setUnlocalizedName(le.getLocalName());
		setRegistryName(le.getLocalName());
		if(le.getRenderTexture().length() > 0)
			RENDER3D_TEXTURE = new ResourceLocation(RpgInventory.MODID+":"+le.getRenderTexture());
		else
			RENDER3D_TEXTURE = null;

		modelLocation = le.getModelPath();

		colorState = color;
		this.armorType = armorType;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxDamage(maxDamage);
	}

	public RpgInventoryItem(JewelTypes armortype, int color, LocalizeEnum le){
		this(armortype, -1, color, le);
	}

	public RpgInventoryItem(JewelTypes armortype, LocalizeEnum le){
		this(armortype, -1, -1, le);
	}
	/**
	 * 'Bounds' the shield to an armor class. this should be overridden in child
	 * mods ! If the string is left/set to "none", it will not check for class
	 * armor and can be used by anyone (like Vanilla Shields)
	 */
	public String bindShieldToArmorClass() {
		return "none";
	}

	public String getModelLocation() {
		return modelLocation;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.getItem().equals(RpgItems.cloak_Invisible);
	}
}
