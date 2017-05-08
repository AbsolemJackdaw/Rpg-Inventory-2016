package subaraki.rpginventory.handler;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems;

public class JeweleryEffectsHandler {

	public static final UUID speedUuid_low = UUID.randomUUID();
	public static final UUID speedUuid_mid = UUID.randomUUID();
	public static final UUID speedUuid_high = UUID.randomUUID();
	public static final UUID speedUuid_highest = UUID.randomUUID();

	public static final AttributeModifier speed_low = new AttributeModifier(speedUuid_low, "speedMod_low", 0.15, 2).setSaved(false);
	public static final AttributeModifier speed_mid = new AttributeModifier(speedUuid_mid, "speedMod_mid", 0.2, 2).setSaved(false);
	public static final AttributeModifier speed_high = new AttributeModifier(speedUuid_high, "speedMod_high", 0.3, 2).setSaved(false);
	public static final AttributeModifier speed_highest = new AttributeModifier(speedUuid_highest, "speedMod_highest", 0.4, 2).setSaved(false);

	public JeweleryEffectsHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onHurtEvent(LivingHurtEvent event){
		getLapisJeweleryStrenghtBonus(event);
		getEmeraldGlovesDamageReduction(event);
	}

	@SubscribeEvent
	public void onBreakBlock(BreakSpeed event){
		getEmeraldRingEffect1(event);
	}

	@SubscribeEvent
	public void onPlayerUpdate(PlayerTickEvent event){

		getGoldenSpeedBoost(event.player);
		getEmeraldRingEffect2(event);
		getRegenFromDiamondJewelry(event);
	}

	@SubscribeEvent
	public void onExpEvent(LivingExperienceDropEvent event){
		getEmeraldNecklaceEffect(event);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////End Of Subscribed Events///////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	private void getEmeraldNecklaceEffect(LivingExperienceDropEvent event) {
		EntityPlayer player = event.getAttackingPlayer();

		if(player == null || RpgInventoryData.get(player).getNecklace() == ItemStack.EMPTY)
			return;

		ItemStack necklace = RpgInventoryData.get(player).getNecklace();

		if(necklace.getItem() == null|| !necklace.getItem().equals(RpgItems.emerald_necklace))
			return;

		float exp = (float)event.getOriginalExperience();
		float bonus = (float)exp/4f;

		event.setDroppedExperience((int)(exp+bonus));
	}

	private void getLapisJeweleryStrenghtBonus(LivingHurtEvent event){
		Entity damager = event.getSource().getSourceOfDamage();
		if(!(damager instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer)damager;

		RpgInventoryData inventory = RpgInventoryData.get(player);

		float extraDamage = 0;

		if(inventory.getNecklace()!= ItemStack.EMPTY && inventory.getNecklace().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.75;
		if(inventory.getGloves()  != ItemStack.EMPTY && inventory.getGloves().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.75;
		if(inventory.getRing_1()  != ItemStack.EMPTY && inventory.getRing_1().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.75;
		if(inventory.getRing_2()  != ItemStack.EMPTY && inventory.getRing_2().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.75;

		if(extraDamage > 0)
			event.setAmount(event.getAmount()+extraDamage);
	}

	private void getEmeraldGlovesDamageReduction(LivingHurtEvent event){
		if(!(event.getEntityLiving() instanceof EntityPlayer))
			return;

		RpgInventoryData inventory = RpgInventoryData.get(((EntityPlayer)event.getEntityLiving()));

		if(inventory.getGloves() == ItemStack.EMPTY || inventory.getGloves().getItem()== null)
			return;

		if(inventory.getGloves().getItem().getUnlocalizedName().contains("emerald")){
			//reduce damage by one fifth of the damage
			//e.g : 5 damage is reduced to 4.
			event.setAmount(event.getAmount() - MathHelper.floor(event.getAmount()/5f));
		}
	}

	private void getRegenFromDiamondJewelry(PlayerTickEvent event){

		EntityPlayer player = event.player;
		RpgInventoryData data = RpgInventoryData.get(player);
		
		if(data.getHealCounter() > 0)
		{
			data.tickHealCounter();
			return; //continue till it reaches 0
		}
		
		//only calculate new timer when the player needs healing !
		if (player.getHealth() >= player.getMaxHealth())
			return;

		//if the player has lost health and counter = 0 : calculate time, and put it in map.

		int delay = 75;

		if(data.getNecklace()!= ItemStack.EMPTY && data.getNecklace().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(data.getGloves()  != ItemStack.EMPTY && data.getGloves().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(data.getRing_1()  != ItemStack.EMPTY && data.getRing_1().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(data.getRing_2()  != ItemStack.EMPTY && data.getRing_2().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;

		if(delay == 75)
			return; // no need for healing if nothing is equipped

		//at this point, the player needs healing, and the timer is zero 
		player.heal(1);

		//reset timer to calculated delay
		data.setHealCounter(delay);
	}

	private void getEmeraldRingEffect1(BreakSpeed event) {
		if(event.getEntityPlayer() != null)
		{
			RpgInventoryData inventory = RpgInventoryData.get(event.getEntityPlayer());
			if(inventory== null || inventory.getRing_2() == ItemStack.EMPTY || !(inventory.getRing_2().getItem() instanceof RpgInventoryItem))
				return;

			if(inventory.getRing_2().getItem().getUnlocalizedName().contains("emerald"))
			{
				float speed = event.getOriginalSpeed();
				event.setNewSpeed(speed*4);
			}
		}
	}

	private void getEmeraldRingEffect2(PlayerTickEvent event) {
		if(event.player.getActivePotionEffects().isEmpty())
			return;

		RpgInventoryData inventory = RpgInventoryData.get(event.player);

		if(inventory.getRing_1() == ItemStack.EMPTY || (inventory.getRing_1().getItem() == null || !inventory.getRing_1().getItem().getUnlocalizedName().contains("emerald")))
			return;

		for(PotionEffect pe : event.player.getActivePotionEffects()){
			if(pe.getPotion().isBadEffect()){
				event.player.removePotionEffect(pe.getPotion());
				break;//prevent concurrent modification
			}
		}
	}

	private void getGoldenSpeedBoost(EntityPlayer player){
		RpgInventoryData inventory = RpgInventoryData.get(player);
		int numberofgoldjewels = 0;

		if(inventory.getGloves() != ItemStack.EMPTY && inventory.getGloves().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(inventory.getNecklace() != ItemStack.EMPTY && inventory.getNecklace().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(inventory.getRing_1() != ItemStack.EMPTY && inventory.getRing_1().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(inventory.getRing_2() != ItemStack.EMPTY && inventory.getRing_2().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;

		if(numberofgoldjewels == 0)
			return;

		else{
			IAttributeInstance speedAttribute = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

			//reset if number of gold objects worn doesn't match the speed boost
			if(speedAttribute.getModifier(speedUuid_low)!=null && numberofgoldjewels != 1)
				speedAttribute.removeModifier(speedUuid_low);
			if(speedAttribute.getModifier(speedUuid_mid)!= null && numberofgoldjewels != 2)
				speedAttribute.removeModifier(speedUuid_mid);
			if(speedAttribute.getModifier(speedUuid_high)!=null && numberofgoldjewels != 3)
				speedAttribute.removeModifier(speedUuid_high);
			if(speedAttribute.getModifier(speedUuid_highest)!=null && numberofgoldjewels != 4)
				speedAttribute.removeModifier(speedUuid_highest);

			//apply speed boost if needed
			switch (numberofgoldjewels){
			case 1:
				if(speedAttribute.getModifier(speedUuid_low)==null)
					speedAttribute.applyModifier(speed_low);
				break;
			case 2:
				if(speedAttribute.getModifier(speedUuid_mid)==null)
					speedAttribute.applyModifier(speed_mid);
				break;
			case 3:
				if(speedAttribute.getModifier(speedUuid_high)==null)
					speedAttribute.applyModifier(speed_high);
				break;
			case 4:
				if(speedAttribute.getModifier(speedUuid_highest)==null)
					speedAttribute.applyModifier(speed_highest);
				break;
			}
		}
	}
}
