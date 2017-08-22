package subaraki.rpginventory.handler.loot;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.handler.loot.LootCloakFunction.Serializer;
import subaraki.rpginventory.mod.RpgInventory;

public class LootEvent {

	public static final String CLOAK_SPEED = "golden.threads";
	public static final String CLOAK_HEALING = "diamond.incrustation";
	public static final String CLOAK_STRENGHT = "lapis.coloring";
	
	public static final String CLOAK_EXP = "emerald.exp.threads";
	public static final String CLOAK_MILK = "emerald.cure.incrustation";
	public static final String CLOAK_MINING = "emerald.mining.side.lining";
	public static final String CLOAK_DMG_REDUCTION = "emerald.defense.incrustation";

	public static final String TAG_AFFINITY = "affinity";
	public static final String TAG_AMOUNT= "affinities";
	public static final String TAG_LVL = "affinity_level";

	public static enum CloakAffinity{

		SPEED(CLOAK_SPEED),
		HEALING(CLOAK_HEALING),
		STRENGTH(CLOAK_STRENGHT),
		EXP(CLOAK_EXP),
		MILK(CLOAK_MILK),
		MINING(CLOAK_MINING),
		REDUCTION(CLOAK_DMG_REDUCTION);
		
		private String name;
		
		CloakAffinity(String name){
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public LootEvent() {
		
		LootTableList.register(new ResourceLocation(RpgInventory.MODID, "cloak_loot"));
		LootFunctionManager.registerFunction(new LootCloakFunction.Serializer());
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {
		if (evt.getName().toString().startsWith("minecraft:chests/")) {
			evt.getTable().addPool(getInjectPool("cloak_loot"));
		}
	}

	private LootPool getInjectPool(String entryName) {
		return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0],
				new RandomValueRange(1), new RandomValueRange(0, 1), "injected_cloak_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(RpgInventory.MODID, name), weight, 0,
				new LootCondition[0], "injected_cloak_pool");
	}
}
