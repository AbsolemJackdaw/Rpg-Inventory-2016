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
import subaraki.rpginventory.handler.loot.LootCapeFunction.Serializer;
import subaraki.rpginventory.mod.RpgInventory;

public class LootEvent {

	public static ArrayList<String> cape_affinity = new ArrayList<String>();
	
	public static final String CAPE_SPEED = "Golden Affinity";
	public static final String CAPE_HEALING = "Diamond Affinity";
	public static final String CAPE_STRENGHT = "Lapis Affinity";
	
	public static final String CAPE_EXP = "Emerald Exp Affinity";
	public static final String CAPE_MILK = "Emerald Cure Affinity";
	public static final String CAPE_MINING = "Emerald Mining Affinity";
	public static final String CAPE_DMG_REDUCTION = "Emerald Defense Affinity";

	public static final String TAG_AFFINITY = "affinity";
	public static final String TAG_AMOUNT= "affinities";
	public static final String TAG_LVL = "affinity_level";

	public LootEvent() {
		cape_affinity.add(CAPE_SPEED);
		cape_affinity.add(CAPE_HEALING);
		cape_affinity.add(CAPE_STRENGHT);
		cape_affinity.add(CAPE_EXP);
		cape_affinity.add(CAPE_MILK);
		cape_affinity.add(CAPE_MINING);
		cape_affinity.add(CAPE_DMG_REDUCTION);
		
		LootTableList.register(new ResourceLocation(RpgInventory.MODID, "cape_loot"));
		LootFunctionManager.registerFunction(new LootCapeFunction.Serializer());
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {
		if (evt.getName().toString().startsWith("minecraft:chests/")) {
			System.out.println(evt.getName().toString());
			evt.getTable().addPool(getInjectPool("cape_loot"));
		}
	}

	private LootPool getInjectPool(String entryName) {
		return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0],
				new RandomValueRange(1), new RandomValueRange(0, 1), "injected_cape_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(RpgInventory.MODID, name), weight, 0,
				new LootCondition[0], "injected_cape_pool");
	}
}
