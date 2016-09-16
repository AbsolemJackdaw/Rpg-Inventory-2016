package subaraki.rpginventory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgItems.LocalizeEnum;

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
			RENDER3D_TEXTURE = new ResourceLocation(le.getRenderTexture());
		else
			RENDER3D_TEXTURE = null;

		modelLocation = le.getModelPath();

		colorState = color;
		this.armorType = armorType;
		this.maxStackSize = 1;
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player,
			EnumHand hand) {

		if(stack != null)
			if(stack.getItem() instanceof RpgInventoryItem){
				RpgPlayerInventory inventory = player.getCapability(RpgInventoryCapability.CAPABILITY,null);
				switch(((RpgInventoryItem)stack.getItem()).armorType){
				case NECKLACE :
					if(inventory.getNecklace() == null){
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
						player.setHeldItem(hand, null);
					}else{
						player.setHeldItem(hand, inventory.getNecklace());
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
					}
					break;
				case CAPE:
					if(inventory.getCloak() == null){
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
						player.setHeldItem(hand, null);
					}else{
						player.setHeldItem(hand, inventory.getCloak());
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
					}
					break;
				case CRYSTAL:
					if(inventory.getCrystal() == null){
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
						player.setHeldItem(hand, null);
					}else{
						player.setHeldItem(hand, inventory.getCrystal());
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
					}
					break;
				case GLOVES:
					if(inventory.getGloves() == null){
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
						player.setHeldItem(hand, null);
					}else{
						player.setHeldItem(hand, inventory.getGloves());
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
					}
					break;
				case RING:
					if(inventory.getRing_1() == null){
						inventory.setJewel(SlotIndex.SLOT_RING1, stack);
						player.setHeldItem(hand, null);
					}else if(inventory.getRing_2() == null){
						inventory.setJewel(SlotIndex.SLOT_RING2, stack);
						player.setHeldItem(hand, null);
					}else{ //only switches out the first worn ring
						player.setHeldItem(hand, inventory.getRing_1());
						inventory.setJewel(SlotIndex.SLOT_RING1, stack);
					}
					break;
				default:
					break;
				}
			}
		return super.onItemRightClick(stack, world, player, hand);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.getItem().equals(RpgItems.cloak_Invisible);
	}

	public ResourceLocation getRenderOnPlayerTexture() {
		return RENDER3D_TEXTURE;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);

		if (stack.getItem() == RpgItems.emerald_ring) {
			tooltip.add(("Left: Dispell Negative Effects"));
			tooltip.add(("Right: Increased Mining Speed x4"));
		}

		if (stack.getItem() == RpgItems.emerald_necklace) {
			tooltip.add(("1/4th Exp Bonus on Kill"));
		}

		if (stack.getItem() == RpgItems.emerald_gloves) {
			tooltip.add(("Resistance"));
			tooltip.add(("Damage reduced by 20%"));
		}

		if ((stack.getItem() == RpgItems.diamond_ring)
				|| (stack.getItem() == RpgItems.diamond_gloves)
				|| (stack.getItem() == RpgItems.diamond_necklace)) {
			tooltip.add(("Healing"));
			tooltip.add(("+15% Heal Speed"));
		}

		if ((stack.getItem() == RpgItems.gold_ring)
				|| (stack.getItem() == RpgItems.gold_gloves)
				|| (stack.getItem() == RpgItems.gold_necklace)) {
			tooltip.add(("Speed + 12.5%"));
		}

		if ((stack.getItem() == RpgItems.lapis_ring)
				|| (stack.getItem() == RpgItems.lapis_gloves)
				|| (stack.getItem() == RpgItems.lapis_necklace)) {
			tooltip.add(("Strength"));
				tooltip.add(("+0.875"));
		}
	}
}
