package subaraki.rpginventory.item;

import static lib.item.ItemRegistry.registerItem;

import static lib.item.ItemRegistry.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.mod.RpgInventory;

public class RpgItems {

	private static String mod = RpgInventory.MODID;

	public static RpgInventoryItem 
	/* ====jewels==== */
	gold_necklace, diamond_necklace, emerald_necklace, lapis_necklace,
	gold_gloves, diamond_gloves, emerald_gloves, lapis_gloves,
	gold_ring, diamond_ring, emerald_ring, lapis_ring,

	/* ====cloaks==== */
	cloak

	;

	public static CreativeTabs tab;

	public static void init(){
		tab = new RpgInventoryTab();

		gold_gloves = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.GLOVES)
				.set3DTexture("armor/jewels/textures/gold_gloves.png")
				.setCreativeTab(tab).setRegistryName("gold_glove").setUnlocalizedName(mod+".gold_glove");	
		diamond_gloves = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.GLOVES)
				.set3DTexture("armor/jewels/textures/diamond_gloves.png")
				.setCreativeTab(tab).setRegistryName("diamond_glove").setUnlocalizedName(mod+".diamond_glove");	
		emerald_gloves = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.GLOVES)
				.set3DTexture("armor/jewels/textures/emerald_gloves.png")
				.setCreativeTab(tab).setRegistryName("emerald_glove").setUnlocalizedName(mod+".emerald_glove");	
		lapis_gloves = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.GLOVES)
				.set3DTexture("armor/jewels/textures/lapis_gloves.png")
				.setCreativeTab(tab).setRegistryName("lapis_glove").setUnlocalizedName(mod+".lapis_glove");	

		emerald_necklace = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.NECKLACE)
				.set3DTexture("armor/jewels/textures/emerald_necklace.png")
				.setCreativeTab(tab).setRegistryName("emerald_necklace").setUnlocalizedName(mod+".emerald_necklace");	
		diamond_necklace = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.NECKLACE)
				.set3DTexture("armor/jewels/textures/diamond_necklace.png")
				.setCreativeTab(tab).setRegistryName("diamond_necklace").setUnlocalizedName(mod+".diamond_necklace");	
		lapis_necklace = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.NECKLACE)
				.set3DTexture("armor/jewels/textures/lapis_necklace.png")
				.setCreativeTab(tab).setRegistryName("lapis_necklace").setUnlocalizedName(mod+".lapis_necklace");	
		gold_necklace = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.NECKLACE)
				.set3DTexture("armor/jewels/textures/gold_necklace.png")
				.setCreativeTab(tab).setRegistryName("gold_necklace").setUnlocalizedName(mod+".gold_necklace");	

		gold_ring = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.RING)
				.setCreativeTab(tab).setRegistryName("gold_ring").setUnlocalizedName(mod+".gold_ring");	
		diamond_ring = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.RING)
				.setCreativeTab(tab).setRegistryName("diamond_ring").setUnlocalizedName(mod+".diamond_ring");	
		emerald_ring = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.RING)
				.setCreativeTab(tab).setRegistryName("emerald_ring").setUnlocalizedName(mod+".emerald_ring");	
		lapis_ring = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.RING)
				.setCreativeTab(tab).setRegistryName("lapis_ring").setUnlocalizedName(mod+".lapis_ring");	
		
		cloak = (ItemCloak) new ItemCloak(JewelTypes.CLOAK)
				.set3DTexture("blocks/wool_colored_white")
				.setCreativeTab(tab).setRegistryName("cloak").setUnlocalizedName(mod+".cloak");	
		
		register();
	}

	private static void register(){
		registerItem(gold_gloves);
		registerItem(diamond_gloves);
		registerItem(emerald_gloves);
		registerItem(lapis_gloves);

		registerItem(gold_necklace);
		registerItem(diamond_necklace);
		registerItem(emerald_necklace);
		registerItem(lapis_necklace);

		registerItem(gold_ring);
		registerItem(diamond_ring);
		registerItem(emerald_ring);
		registerItem(lapis_ring);

		registerItem(cloak);

	}

	private static final String NECKLACE = "jewels/endented_necklace";
	private static final String NECKLACE_G = "jewels/gold_necklace";

	private static final String GLOVE = "jewels/endented_glove";
	private static final String GLOVE_G = "jewels/gold_glove";
	
	private static final String RING = "jewels/endented_ring";
	private static final String RING_G = "jewels/gold_ring";
	
	public static void registerRenders(){
		registerRender(gold_gloves, GLOVE_G, mod);
		registerRender(diamond_gloves, GLOVE, mod);
		registerRender(emerald_gloves, GLOVE, mod);
		registerRender(lapis_gloves, GLOVE, mod);

		registerRender(gold_necklace, NECKLACE_G, mod);
		registerRender(diamond_necklace, NECKLACE, mod);
		registerRender(emerald_necklace, NECKLACE, mod);
		registerRender(lapis_necklace, NECKLACE, mod);

		registerRender(gold_ring, RING_G, mod);
		registerRender(diamond_ring, RING, mod);
		registerRender(emerald_ring, RING, mod);
		registerRender(lapis_ring, RING, mod);

		for(int i=0;i<16;i++)
			registerRender(cloak, "jewels/cloak", mod, i);
	}
}
