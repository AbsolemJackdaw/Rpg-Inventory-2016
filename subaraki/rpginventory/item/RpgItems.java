package subaraki.rpginventory.item;

import static lib.item.ItemRegistry.registerItem;

import lib.item.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.mod.RpgInventory;

public class RpgItems {

	public static RpgInventoryItem 
	/* ====jewels==== */
	gold_necklace, diamond_necklace, emerald_necklace, lapis_necklace,
	gold_gloves, diamond_gloves, emerald_gloves, lapis_gloves,
	gold_ring, diamond_ring, emerald_ring, lapis_ring,

	/* ====cloaks==== */
	cloakWhite, cloakBlack,	cloakRed, cloakGreen, cloakBrown, cloakBlue,
	cloakPurple, cloakCyan, cloakSilver, cloakGray, cloakPink, cloakLime, 
	cloakYellow, cloakLightblue, cloakMagenta, cloakOrange, cloak_Invisible,

	/* ====molds==== */
	mold_necklace, mold_ring, mold_gloves

	;

	public static CreativeTabs tab;

	public static void init(){
		tab = new RpgInventoryTab();

		gold_gloves = (RpgInventoryItem) (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.GLOVES,
				LocalizeEnum.gold_gloves).setCreativeTab(tab);
		diamond_gloves = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.diamond_gloves).setCreativeTab(tab);
		emerald_gloves = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.emerald_gloves).setCreativeTab(tab);
		lapis_gloves = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.lapis_gloves).setCreativeTab(tab);

		emerald_necklace = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.emerald_necklace).setCreativeTab(tab);
		diamond_necklace = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.diamond_necklace).setCreativeTab(tab);
		lapis_necklace = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.lapis_necklace).setCreativeTab(tab);
		gold_necklace = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.gold_necklace).setCreativeTab(tab);

		gold_ring = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.gold_ring).setCreativeTab(tab);
		diamond_ring = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.diamond_ring).setCreativeTab(tab);
		emerald_ring = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.emerald_ring).setCreativeTab(tab);
		lapis_ring = (RpgInventoryItem) new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.lapis_ring).setCreativeTab(tab);

		cloakBlack = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,0,LocalizeEnum.cape_black).setCreativeTab(tab);
		cloakRed = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,1,LocalizeEnum.cape_red).setCreativeTab(tab);
		cloakGreen = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,2,LocalizeEnum.cape_green).setCreativeTab(tab);
		cloakBrown = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,3,LocalizeEnum.cape_brown).setCreativeTab(tab);
		cloakBlue = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,4,LocalizeEnum.cape_blue).setCreativeTab(tab);
		cloakPurple = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,5,LocalizeEnum.cape_purple).setCreativeTab(tab);
		cloakCyan = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,6,LocalizeEnum.cape_cyan).setCreativeTab(tab);
		cloakSilver = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,7,LocalizeEnum.cape_silver).setCreativeTab(tab);
		cloakGray = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,8,LocalizeEnum.cape_grey).setCreativeTab(tab);
		cloakPink = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,9,LocalizeEnum.cape_pink).setCreativeTab(tab);
		cloakLime = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,10,LocalizeEnum.cape_lime).setCreativeTab(tab);
		cloakYellow = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,11,LocalizeEnum.cape_yellow).setCreativeTab(tab);
		cloakLightblue = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,12,LocalizeEnum.cape_lightblue).setCreativeTab(tab);
		cloakMagenta = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,13,LocalizeEnum.cape_magenta).setCreativeTab(tab);
		cloakOrange = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,14,LocalizeEnum.cape_orange).setCreativeTab(tab);
		cloakWhite = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,15,LocalizeEnum.cape_white).setCreativeTab(tab);

		cloak_Invisible = (RpgInventoryItem) new RpgInventoryItem(JewelTypes.CAPE,LocalizeEnum.cape_invisible).setCreativeTab(tab);

	}

	public static void register(){
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

		registerItem(cloakBlack);
		registerItem(cloakBlue);
		registerItem(cloakBrown);
		registerItem(cloakCyan);
		registerItem(cloakGray);
		registerItem(cloakGreen);
		registerItem(cloakLightblue);
		registerItem(cloakLime);
		registerItem(cloakMagenta);
		registerItem(cloakOrange);
		registerItem(cloakPink);
		registerItem(cloakPurple);
		registerItem(cloakRed);
		registerItem(cloakSilver);
		registerItem(cloakWhite);
		registerItem(cloakYellow);
		registerItem(cloak_Invisible);

	}

	public static void registerRenders(){
		registerRender(gold_gloves);
		registerRender(diamond_gloves);
		registerRender(emerald_gloves);
		registerRender(lapis_gloves);

		registerRender(gold_necklace);
		registerRender(diamond_necklace);
		registerRender(emerald_necklace);
		registerRender(lapis_necklace);

		registerRender(gold_ring);
		registerRender(diamond_ring);
		registerRender(emerald_ring);
		registerRender(lapis_ring);

		registerRender(cloakBlack);
		registerRender(cloakBlue);
		registerRender(cloakBrown);
		registerRender(cloakCyan);
		registerRender(cloakGray);
		registerRender(cloakGreen);
		registerRender(cloakLightblue);
		registerRender(cloakLime);
		registerRender(cloakMagenta);
		registerRender(cloakOrange);
		registerRender(cloakPink);
		registerRender(cloakPurple);
		registerRender(cloakRed);
		registerRender(cloakSilver);
		registerRender(cloakWhite);
		registerRender(cloakYellow);
		registerRender(cloak_Invisible);
	}

	public static void registerRender(Item item){
		String mod = RpgInventory.MODID;
		ItemRegistry.registerRender(item, ((RpgInventoryItem)item).getModelLocation(), mod);
	}

	public static enum LocalizeEnum{
		gold_gloves("gold_glove","jewels/gold_glove", RpgInventory.MODID+":"+"armor/jewels/textures/gold_gloves.png"),
		diamond_gloves("diamond_glove","jewels/endented_glove", RpgInventory.MODID+":"+"armor/jewels/textures/diamond_gloves.png"),
		emerald_gloves("emerald_glove","jewels/endented_glove", RpgInventory.MODID+":"+"armor/jewels/textures/emerald_gloves.png"),
		lapis_gloves("lapis_glove","jewels/endented_glove", RpgInventory.MODID+":"+"armor/jewels/textures/lapis_gloves.png"),

		gold_necklace("gold_necklace","jewels/gold_necklace",RpgInventory.MODID+":"+"armor/jewels/textures/gold_necklace.png"),
		diamond_necklace("diamond_necklace","jewels/endented_necklace",RpgInventory.MODID+":"+"armor/jewels/textures/diamond_necklace.png"),
		emerald_necklace("emerald_necklace","jewels/endented_necklace",RpgInventory.MODID+":"+"armor/jewels/textures/emerald_necklace.png"),
		lapis_necklace("lapis_necklace","jewels/endented_necklace",RpgInventory.MODID+":"+"armor/jewels/textures/lapis_necklace.png"),

		gold_ring("gold_ring","jewels/gold_ring",""),
		diamond_ring("diamond_ring","jewels/endented_ring",""),
		emerald_ring("emerald_ring","jewels/endented_ring",""),
		lapis_ring("lapis_ring","jewels/endented_ring",""),

		cape_black("black_cape","jewels/cape","textures/blocks/wool_colored_black.png"),
		cape_red("red_cape","jewels/cape","textures/blocks/wool_colored_red.png"),
		cape_green("green_cape","jewels/cape","textures/blocks/wool_colored_green.png"),
		cape_brown("brown_cape","jewels/cape","textures/blocks/wool_colored_brown.png"),
		cape_blue("blue_cape","jewels/cape","textures/blocks/wool_colored_blue.png"),
		cape_purple("purple_cape","jewels/cape","textures/blocks/wool_colored_purple.png"),
		cape_cyan("cyan_cape","jewels/cape","textures/blocks/wool_colored_cyan.png"),
		cape_silver("silver_cape","jewels/cape","textures/blocks/wool_colored_silver.png"),
		cape_grey("grey_cape","jewels/cape","textures/blocks/wool_colored_gray.png"),
		cape_pink("pink_cape","jewels/cape","textures/blocks/wool_colored_pink.png"),
		cape_lime("lime_cape","jewels/cape","textures/blocks/wool_colored_lime.png"),
		cape_yellow("yellow_cape","jewels/cape","textures/blocks/wool_colored_yellow.png"),
		cape_lightblue("lightblue_cape","jewels/cape","textures/blocks/wool_colored_light_blue.png"),
		cape_magenta("magenta_cape","jewels/cape","textures/blocks/wool_colored_magenta.png"),
		cape_orange("orange_cape","jewels/cape","textures/blocks/wool_colored_orange.png"),
		cape_white("white_cape","jewels/cape","textures/blocks/wool_colored_white.png"),

		cape_invisible("invisible_cape","jewels/cape","textures/blocks/wool_colored_white.png"),

		shield("test_shield","","textures/blocks/wool_colored_white.png"),
		;

		private final String name;
		private final String modelLocation;
		private final String renderTexture;

		/**
		 * @param
		 * renderTexture : string related to texture for model rendering on player
		 * */
		LocalizeEnum(String name, String modelLocation, String renderTexture){
			this.name = name;
			this.modelLocation = modelLocation;
			this.renderTexture = renderTexture;
		}

		public String getLocalName() {
			return name;
		}

		public String getModelPath() {
			return modelLocation;
		}

		public String getRenderTexture() {
			return renderTexture;
		}
	}

	public static void registerRecipes(){
		RpgInventoryItem cloaks[] = new RpgInventoryItem[]{
				cloakBlack,cloakRed,cloakGreen,cloakBrown,cloakBlue,
				cloakPurple,cloakCyan,cloakSilver,cloakGray,cloakPink,cloakLime,
				cloakYellow,cloakLightblue,cloakMagenta,cloakOrange,cloakWhite
		};

		for(int i = 0; i <16;i++)
			GameRegistry.addRecipe(new ItemStack(cloaks[i], 1),
					new Object[] {"SS", "WW", "WW", 
							'S', Items.STRING,
							'W', new ItemStack(Blocks.WOOL, 1, 15-i) });

		for(int i = 0; i <15;i++)
			GameRegistry.addRecipe(new ItemStack(cloaks[i], 1),
					new Object[] {"DDD", "DCD", "DDD", 
							'C', cloakWhite,
							'D', new ItemStack(Items.DYE, 1, i) });

		ItemStack component[] = new ItemStack[]{new ItemStack(Items.GOLD_NUGGET),new ItemStack(Items.EMERALD),new ItemStack(Items.DIAMOND),new ItemStack(Items.DYE, 4)};
		String recipe[][] = new String[][]{{"#IM","I#I","#I#"},/*ring*/{"SS#","SI#","##M"},/*necklace*/{"II#","IMI","#II"}/*gloves*/};
		ItemStack jewelry[][] = new ItemStack[][]{{new ItemStack(gold_ring),new ItemStack(emerald_ring),new ItemStack(diamond_ring),new ItemStack(lapis_ring)},
			{new ItemStack(gold_necklace),new ItemStack(emerald_necklace),new ItemStack(diamond_necklace),new ItemStack(lapis_necklace)},
			{new ItemStack(gold_gloves),new ItemStack(emerald_gloves),new ItemStack(diamond_gloves),new ItemStack(lapis_gloves)},
		};

		for(int i = 0; i < 3 ; i++)
			for(int j = 0 ; j < 4 ;j++){
				GameRegistry.addRecipe(jewelry[i][j],
						new Object[]{
								recipe[i][0],recipe[i][1],recipe[i][2],
								'I',Items.GOLD_INGOT,
								'S',Items.STRING,
								'M',component[j]
				});
			}
	}

}
