package subaraki.rpginventory.handler;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.handler.loot.LootEvent;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.mod.RpgInventory;

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

		float bonus = 0f;
		float exp = (float)event.getOriginalExperience();
		RpgInventoryData inventory = RpgInventoryData.get(player);

		ItemStack necklace = inventory.getNecklace();

		if(!necklace.isEmpty() && necklace.getItem().getUnlocalizedName().contains("emerald"))
			bonus += (float)exp/4f;

		bonus += (float)exp/(4f-(float)cloakLevel(inventory, LootEvent.CLOAK_EXP)) ;

		event.setDroppedExperience((int)(exp+bonus));
	}

	private void getLapisJeweleryStrenghtBonus(LivingHurtEvent event){
		Entity damager = event.getSource().getTrueSource();
		if(!(damager instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer)damager;

		RpgInventoryData inventory = RpgInventoryData.get(player);

		float extraDamage = 0;

		if(!inventory.getNecklace().isEmpty() && inventory.getNecklace().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getGloves().isEmpty() && inventory.getGloves().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getRing_1().isEmpty() && inventory.getRing_1().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getRing_2().isEmpty() && inventory.getRing_2().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;

		extraDamage += 1.5*(float)cloakLevel(inventory, LootEvent.CLOAK_STRENGHT);

		if(extraDamage > 0)
			event.setAmount(event.getAmount()+extraDamage);
	}

	private void getEmeraldGlovesDamageReduction(LivingHurtEvent event){
		if(!(event.getEntityLiving() instanceof EntityPlayer))
			return;

		RpgInventoryData inventory = RpgInventoryData.get(((EntityPlayer)event.getEntityLiving()));

		ItemStack gloves = inventory.getGloves();

		float received = event.getAmount();
		float reduction = -1;

		if(!gloves.isEmpty() && gloves.getItem().getUnlocalizedName().contains("emerald")){
			//reduce damage by one fifth of the damage
			//e.g : 5 damage is reduced to 4.
			reduction = 5f;
		}

		float level = (float)cloakLevel(inventory, LootEvent.CLOAK_DMG_REDUCTION);
		if(level > 0)
		{
			if (reduction > 0)
				reduction -= level;
			else
				reduction = 6-level; // 5 at level 1 , 4 at 2, and 3 at level 3.
		}

		//reduces damage by half if level 3 cloak and gloves are equiped.
		if(reduction > 0)
			event.setAmount(received - MathHelper.floor(received/reduction));
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

		if(!data.getNecklace().isEmpty() && data.getNecklace().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getGloves().isEmpty()   && data.getGloves().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getRing_1().isEmpty()   && data.getRing_1().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getRing_2().isEmpty()   && data.getRing_2().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;

		float level = (float)cloakLevel(data, LootEvent.CLOAK_HEALING);

		if(level > 0)
			delay -= 10 * level;  

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

			ItemStack ring = inventory.getRing_2();

			float modifier = 1;
			float speed = event.getOriginalSpeed();

			if (!ring.isEmpty())				
				if(inventory.getRing_2().getItem().getUnlocalizedName().contains("emerald"))
					modifier = 4;

			float level = (float)cloakLevel(inventory, LootEvent.CLOAK_MINING);

			if(level > 0)
				modifier = modifier == 1 ? level*1.35f : 2 + level * 1.35f;

			event.setNewSpeed(speed*modifier);
		}
	}

	private void getEmeraldRingEffect2(PlayerTickEvent event) {
		if(event.player.getActivePotionEffects().isEmpty())
			return;

		RpgInventoryData inventory = RpgInventoryData.get(event.player);
		ItemStack ring = inventory.getRing_1();

		if(!ring.isEmpty() && ring.getItem().getUnlocalizedName().contains("emerald") 
				|| cloakLevel(inventory, LootEvent.CLOAK_MILK) > 0)	

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

		if(!inventory.getGloves().isEmpty() && inventory.getGloves().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(!inventory.getNecklace().isEmpty() && inventory.getNecklace().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(!inventory.getRing_1().isEmpty() && inventory.getRing_1().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;
		if(!inventory.getRing_2().isEmpty() && inventory.getRing_2().getItem().getUnlocalizedName().contains("gold"))
			numberofgoldjewels++;

		int level = cloakLevel(inventory, LootEvent.CLOAK_SPEED);

		numberofgoldjewels+=level;

		if(numberofgoldjewels > 4)
			numberofgoldjewels = 4;

		if(numberofgoldjewels == 0)
		{
			IAttributeInstance speedAttribute = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			
			//reset if number of gold objects worn doesn't match the speed boost
			if(speedAttribute.getModifier(speedUuid_low)!=null)
				speedAttribute.removeModifier(speedUuid_low);
			if(speedAttribute.getModifier(speedUuid_mid)!= null)
				speedAttribute.removeModifier(speedUuid_mid);
			if(speedAttribute.getModifier(speedUuid_high)!=null)
				speedAttribute.removeModifier(speedUuid_high);
			if(speedAttribute.getModifier(speedUuid_highest)!=null)
				speedAttribute.removeModifier(speedUuid_highest);
			
			return;
		}

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

	private int cloakLevel(RpgInventoryData inventory, String cloaktype){
		ItemStack cloak = inventory.getCloak();

		if(!cloak.isEmpty() && cloak.getItem().equals(RpgItems.cloak))
		{
			if(cloak.hasTagCompound())
			{
				NBTTagCompound tag = cloak.getTagCompound();

				if(tag.hasKey(LootEvent.TAG_AMOUNT))
				{
					int loop = tag.getInteger(LootEvent.TAG_AMOUNT);
					boolean hasCloak = false;
					int entry = 0;
					for(int i = 0; i < loop; i++)
					{
						if(tag.getString(LootEvent.TAG_AFFINITY+i).equals(cloaktype))
						{
							hasCloak=true;
							entry = i;
							break;
						}
					}
					if(hasCloak)
					{
						int level = tag.getInteger(LootEvent.TAG_LVL+entry);
						return level;
					}
				}
			}
		}
		return 0;
	}
}
