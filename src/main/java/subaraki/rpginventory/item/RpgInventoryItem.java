package subaraki.rpginventory.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.mod.RpgInventory;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketInventoryToClient;
import subaraki.rpginventory.network.PacketInventoryToTrackedPlayer;

public class RpgInventoryItem extends Item {

	public JewelTypes armorType;

	private ResourceLocation RENDER3D_TEXTURE;

	public RpgInventoryItem(JewelTypes armorType) {
		super();

		this.armorType = armorType;
		this.maxStackSize = 1;
	}
	
	public RpgInventoryItem set3DTexture(String path){

		if(path.length() > 0)
			RENDER3D_TEXTURE = new ResourceLocation(RpgInventory.MODID, path);
		else
			RENDER3D_TEXTURE = null;

		return this;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack stack = player.getHeldItem(hand);

		if(stack.isEmpty())
		{
			if(stack.getItem() instanceof RpgInventoryItem)
			{
				RpgInventoryData inventory = RpgInventoryData.get(player);
				switch(((RpgInventoryItem)stack.getItem()).armorType){
				case NECKLACE :
					if(inventory.getNecklace().isEmpty()){
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getNecklace());
						inventory.setJewel(SlotIndex.SLOT_NECKLACE, stack);
					}
					break;
				case CLOAK:
					if(inventory.getCloak().isEmpty()){
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getCloak());
						inventory.setJewel(SlotIndex.SLOT_CLOAK, stack);
					}
					break;
				case CRYSTAL:
					if(inventory.getCrystal().isEmpty()){
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getCrystal());
						inventory.setJewel(SlotIndex.SLOT_CRYSTAL, stack);
					}
					break;
				case GLOVES:
					if(inventory.getGloves().isEmpty()){
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else{
						player.setHeldItem(hand, inventory.getGloves());
						inventory.setJewel(SlotIndex.SLOT_GLOVES, stack);
					}
					break;
				case RING:
					if(inventory.getRing_1().isEmpty()){
						inventory.setJewel(SlotIndex.SLOT_RING1, stack);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}else if(inventory.getRing_2().isEmpty()){
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
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}

		return super.onItemRightClick(world, player, hand);
	}

	public ResourceLocation getRenderOnPlayerTexture(ItemStack stack) {
		return RENDER3D_TEXTURE;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, world, tooltip, advanced);

		if (stack.getItem() == RpgItems.emerald_ring) {
			tooltip.add(I18n.format("dispell.ring"));
			tooltip.add(I18n.format("mining.ring"));
		}

		else if (stack.getItem() == RpgItems.emerald_necklace) {
			tooltip.add(I18n.format("exp.necklace"));
		}

		else if (stack.getItem() == RpgItems.emerald_gloves) {
			tooltip.add(I18n.format("effect.resistance")+ " +20%");
		}

		else if (stack.getItem().getUnlocalizedName().contains("diamond")) {
			tooltip.add(I18n.format("diamond.healing"));
			tooltip.add("+15% "+I18n.format("diamond.info"));
		}

		else if (stack.getItem().getUnlocalizedName().contains("gold")) {
			tooltip.add(I18n.format("effect.moveSpeed") + " +12.5%");
		}

		else if (stack.getItem().getUnlocalizedName().contains("lapis")){
			tooltip.add(I18n.format("effect.damageBoost"));
			tooltip.add("+3"+ " " + I18n.format("attribute.name.generic.attackDamage"));
		}
	}
}
