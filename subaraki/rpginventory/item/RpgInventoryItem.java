package subaraki.rpginventory.item;

import java.util.List;

import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgItems.InventoryItem;
import subaraki.rpginventory.mod.RpgInventory;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketInventoryToClient;
import subaraki.rpginventory.network.PacketInventoryToTrackedPlayer;

public class RpgInventoryItem extends Item {

	public JewelTypes armorType;

	private final ResourceLocation RENDER3D_TEXTURE;

	/**for capes*/
	private int colorState;

	private String modelLocation;
	
	/**Constructor for external items*/
	public RpgInventoryItem(JewelTypes armortype, String registerylocalizeName){
		super();
		setUnlocalizedName(registerylocalizeName);
		setRegistryName(registerylocalizeName);
		RENDER3D_TEXTURE = null;
		this.armorType = armortype;
		this.maxStackSize = 1;
	}
	
	public RpgInventoryItem(JewelTypes armorType, InventoryItem le) {
		super();
		setUnlocalizedName(RpgInventory.MODID+"."+le.getLocalName());
		setRegistryName(le.getLocalName());
		
		if(le.getRenderTexture().length() > 0)
			RENDER3D_TEXTURE = new ResourceLocation(le.getRenderTexture());
		else
			RENDER3D_TEXTURE = null;

		modelLocation = le.getModelPath();

		this.armorType = armorType;
		this.maxStackSize = 1;
	}
	
	public RpgInventoryItem setColorState(int colorState) {
		this.colorState = colorState;
		return this;
	}
	
	public int getColorState() {
		return colorState;
	}
	
	public String getModelLocation() {
		return modelLocation;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(stack != ItemStack.EMPTY)
			if(stack.getItem() instanceof RpgInventoryItem){
				RpgInventoryData inventory = RpgInventoryData.get(player);
				switch(((RpgInventoryItem)stack.getItem()).armorType){
				case NECKLACE :
					if(inventory.getNecklace() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getNecklace());
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
					}
					break;
				case CAPE:
					if(inventory.getCloak() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getCloak());
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
					}
					break;
				case CRYSTAL:
					if(inventory.getCrystal() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getCrystal());
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
					}
					break;
				case GLOVES:
					if(inventory.getGloves() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getGloves());
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
					}
					break;
				case RING:
					if(inventory.getRing_1() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_RING1, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else if(inventory.getRing_2() == ItemStack.EMPTY){
						inventory.setJewel(SlotIndex.SLOT_RING2, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{ //only switches out the first worn ring
						player.setHeldItem(hand, inventory.getRing_1());
						inventory.setJewel(SlotIndex.SLOT_RING1, stack);
					}
					break;
				default:
					break;
				}
				
				if(!world.isRemote){
					//sync own inventory
					PacketHandler.NETWORK.sendTo(new PacketInventoryToClient(player), (EntityPlayerMP) player);
					//sync around
					EntityTracker tracker = ((WorldServer)world).getEntityTracker();
					for (EntityPlayer entityPlayer : tracker.getTrackingPlayers(player)){
						IMessage packet = new PacketInventoryToTrackedPlayer(player);
						PacketHandler.NETWORK.sendTo(packet, (EntityPlayerMP) entityPlayer);
					}
				}
			}
		
		return super.onItemRightClick(world, player, hand);
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
