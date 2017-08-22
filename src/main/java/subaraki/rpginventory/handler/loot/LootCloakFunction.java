package subaraki.rpginventory.handler.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.mod.RpgInventory;

public class LootCloakFunction extends LootFunction{

	private static enum Rarity{
		COMMON(5),
		RARE(8),
		EPIC(9);

		public static int maxWeight = 10;

		int weight = 0;

		private Rarity(int theWeight) {
			this.weight = theWeight;
		}

		public static Rarity get (int aWeight){

			return EPIC.weight <= aWeight ? EPIC : RARE.weight <= aWeight ? RARE : COMMON;
		}

	}

	protected LootCloakFunction(LootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {

		boolean isInvisibilityCloak = rand.nextInt(100) == 1;

		if(isInvisibilityCloak)
		{
			ItemStack cloak = new ItemStack(RpgItems.cloak, 1 , 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("invisible", true);
			cloak.setTagCompound(tag);
			return cloak;
		}

		int nr_enchants = rand.nextInt(Rarity.maxWeight);
		Rarity enchants = Rarity.get(nr_enchants);
		int loop = enchants.ordinal()+1;

		//list with enchants that gets filled regardless of loops done later on
		int[] UEID = new int[]{0,0,0};

		for(int i = 0 ; i < UEID.length; i ++){
			UEID[i] = getUniqueNumber(rand);
		}
		RpgInventory.log.debug("loops:"+loop + " " + "enchants: " + UEID[0] + " " + UEID[1] + " " + UEID[2]);

		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger(LootEvent.TAG_AMOUNT, loop);

		//discern the needed enchants out of the UEID with a loop
		for(int i = 0; i < loop; i++)
		{
			if(UEID[i] == LootEvent.CloakAffinity.MILK.ordinal())//cure
				tag.setInteger(LootEvent.TAG_LVL+i, 1);
			else if(UEID[i] == LootEvent.CloakAffinity.REDUCTION.ordinal())
				tag.setInteger(LootEvent.TAG_LVL+i, rand.nextInt(2)+1);
			else
				tag.setInteger(LootEvent.TAG_LVL+i, rand.nextInt(3)+1);

			tag.setString(LootEvent.TAG_AFFINITY+i, LootEvent.CloakAffinity.values()[UEID[i]].getName());
		}

		stack.setTagCompound(tag);	

		stack.setItemDamage(rand.nextInt(16));

		return stack;
	}

	private List<Integer> memory = new ArrayList<Integer>();
	/**stores up to 3 integers and returns a different integer than the previously stored ones*/
	private int getUniqueNumber(Random rand)
	{
		//if the memory has 3 entries, clear it
		if(memory.size() == 3)
		{
			RpgInventory.log.debug("stored memory was " + memory.toString());
			memory.clear();
		}

		int id = rand.nextInt(LootEvent.CloakAffinity.values().length);

		if(memory.isEmpty())
			memory.add(id);

		else
		{
			while(memory.contains(id))
				id = rand.nextInt(LootEvent.CloakAffinity.values().length);
			memory.add(id);
		}

		return id;
	}

	public static class Serializer extends LootFunction.Serializer<LootCloakFunction>{

		public Serializer()
		{
			super(new ResourceLocation("rpginventory:affine"), LootCloakFunction.class);
		}

		@Override
		public void serialize(JsonObject object, LootCloakFunction functionClazz,
				JsonSerializationContext serializationContext) {

		}

		@Override
		public LootCloakFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
				LootCondition[] conditionsIn) {
			return new LootCloakFunction(conditionsIn);
		}
	}
}
