package subaraki.rpginventory.handler.loot;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.EnchantRandomly;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class LootCapeFunction extends LootFunction{

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

	protected LootCapeFunction(LootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		int nr_enchants = rand.nextInt(Rarity.maxWeight);
		Rarity enchants = Rarity.get(nr_enchants);
		int loop = enchants.ordinal()+1;

		int[] UEID = new int[]{0,0,0};

		for(int i = 0 ; i < UEID.length; i ++){
			if(i == 0)
				UEID[0] = rand.nextInt(LootEvent.cape_affinity.size());
			else
			{
				int id = rand.nextInt(LootEvent.cape_affinity.size());
				while(id == UEID[i-1])
				{
					id = rand.nextInt(LootEvent.cape_affinity.size());
				}
				UEID[i] = id;
			}
		}

		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger(LootEvent.TAG_AMOUNT, loop);

		for(int i = 0; i < loop; i++)
		{
			if(UEID[i] == 4)//cure
				tag.setInteger(LootEvent.TAG_LVL+i, 1);
			if(UEID[i] == 6)//damage reduction
				tag.setInteger(LootEvent.TAG_LVL+i, rand.nextInt(2)+1);
			else
				tag.setInteger(LootEvent.TAG_LVL+i, rand.nextInt(3)+1);

			tag.setString(LootEvent.TAG_AFFINITY+i, LootEvent.cape_affinity.get(UEID[i]));
		}

		//		ItemStack cape = stack.copy();
		//		cape.setTagCompound(tag);

		stack.setTagCompound(tag);

		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<LootCapeFunction>{

		public Serializer()
		{
			super(new ResourceLocation("rpginventory:affine"), LootCapeFunction.class);
		}

		@Override
		public void serialize(JsonObject object, LootCapeFunction functionClazz,
				JsonSerializationContext serializationContext) {

		}

		@Override
		public LootCapeFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
				LootCondition[] conditionsIn) {
			return new LootCapeFunction(conditionsIn);
		}

	}
}
