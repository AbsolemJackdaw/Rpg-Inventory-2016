package subaraki.rpginventory.api;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.rpginventory.mod.RpgInventory;

public abstract class ModeledArmor extends ItemArmor {

	private ModelBiped armorModel;
	private String tex_loc;

	public ModeledArmor(EntityEquipmentSlot slot, ItemArmor.ArmorMaterial mats, String texture_name) {
		super(mats, 4, slot);

		setRegistryName(texture_name);
		setUnlocalizedName(texture_name);
		tex_loc="armor/"+texture_name;
	}

	public String getModeltextureLocation() {
		return tex_loc;
	}
	/** returns the name of the class from this full set of armor */
	public abstract String armorClassName();

	/** 
	 * if full armor is worn, and the item given in this method is worn in the offhandslot,
	 * then the wielder will get the status of shielded class
	 */
	public abstract Item getLinkedShieldItem();
	
	/**
	 * Called to set the 3D armor model. set models here, not in
	 * getArmorModel(...) !*/
	@SideOnly(Side.CLIENT)
	protected abstract void get3DArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot);

	@Override 	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped defaultModel) {

		if (itemStack != null) {
			get3DArmorModel(entityLiving, itemStack, armorSlot);
			if (armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
						|| (armorSlot == EntityEquipmentSlot.CHEST);
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
						|| (armorSlot == EntityEquipmentSlot.FEET);
				armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
						|| (armorSlot == EntityEquipmentSlot.FEET);

				armorModel.isSneak = defaultModel.isSneak;
				armorModel.isRiding = defaultModel.isRiding;
				armorModel.isChild = defaultModel.isChild;
				armorModel.rightArmPose = defaultModel.rightArmPose;
				armorModel.leftArmPose = defaultModel.leftArmPose;

				return armorModel;
			}
		}
		return null;
	}

	public void setArmorModel(ModelBiped armorModel) {
		//do not add a null check here.
		this.armorModel = armorModel;
	}
}
