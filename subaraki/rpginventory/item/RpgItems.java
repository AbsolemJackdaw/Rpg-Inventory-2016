package subaraki.rpginventory.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.mod.RpgInventory;

public class RpgItems {

	public static Item 
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

		gold_gloves = new RpgInventoryItem(
				JewelTypes.GLOVES,
				LocalizeEnum.gold_gloves).setCreativeTab(tab);
		diamond_gloves = new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.diamond_gloves).setCreativeTab(tab);
		emerald_gloves = new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.emerald_gloves).setCreativeTab(tab);
		lapis_gloves = new RpgInventoryItem(
				JewelTypes.GLOVES, 
				LocalizeEnum.lapis_gloves).setCreativeTab(tab);

		emerald_necklace = new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.emerald_necklace).setCreativeTab(tab);
		diamond_necklace = new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.diamond_necklace).setCreativeTab(tab);
		lapis_necklace = new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.lapis_necklace).setCreativeTab(tab);
		gold_necklace = new RpgInventoryItem(
				JewelTypes.NECKLACE, 
				LocalizeEnum.gold_necklace).setCreativeTab(tab);

		gold_ring = new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.gold_ring).setCreativeTab(tab);
		diamond_ring = new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.diamond_ring).setCreativeTab(tab);
		emerald_ring = new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.emerald_ring).setCreativeTab(tab);
		lapis_ring = new RpgInventoryItem(
				JewelTypes.RING,
				LocalizeEnum.lapis_ring).setCreativeTab(tab);

		cloakBlack = new RpgInventoryItem(JewelTypes.CAPE,0,LocalizeEnum.cape_black).setCreativeTab(tab);
		cloakRed = new RpgInventoryItem(JewelTypes.CAPE,1,LocalizeEnum.cape_red).setCreativeTab(tab);
		cloakGreen = new RpgInventoryItem(JewelTypes.CAPE,2,LocalizeEnum.cape_green).setCreativeTab(tab);
		cloakBrown = new RpgInventoryItem(JewelTypes.CAPE,3,LocalizeEnum.cape_brown).setCreativeTab(tab);
		cloakBlue = new RpgInventoryItem(JewelTypes.CAPE,4,LocalizeEnum.cape_blue).setCreativeTab(tab);
		cloakPurple = new RpgInventoryItem(JewelTypes.CAPE,5,LocalizeEnum.cape_purple).setCreativeTab(tab);
		cloakCyan = new RpgInventoryItem(JewelTypes.CAPE,6,LocalizeEnum.cape_cyan).setCreativeTab(tab);
		cloakSilver = new RpgInventoryItem(JewelTypes.CAPE,7,LocalizeEnum.cape_silver).setCreativeTab(tab);
		cloakGray = new RpgInventoryItem(JewelTypes.CAPE,8,LocalizeEnum.cape_grey).setCreativeTab(tab);
		cloakPink = new RpgInventoryItem(JewelTypes.CAPE,9,LocalizeEnum.cape_pink).setCreativeTab(tab);
		cloakLime = new RpgInventoryItem(JewelTypes.CAPE,10,LocalizeEnum.cape_lime).setCreativeTab(tab);
		cloakYellow = new RpgInventoryItem(JewelTypes.CAPE,11,LocalizeEnum.cape_yellow).setCreativeTab(tab);
		cloakLightblue = new RpgInventoryItem(JewelTypes.CAPE,12,LocalizeEnum.cape_lightblue).setCreativeTab(tab);
		cloakMagenta = new RpgInventoryItem(JewelTypes.CAPE,13,LocalizeEnum.cape_magenta).setCreativeTab(tab);
		cloakOrange = new RpgInventoryItem(JewelTypes.CAPE,14,LocalizeEnum.cape_orange).setCreativeTab(tab);
		cloakWhite = new RpgInventoryItem(JewelTypes.CAPE,15,LocalizeEnum.cape_white).setCreativeTab(tab);

		cloak_Invisible = new RpgInventoryItem(JewelTypes.CAPE,LocalizeEnum.cape_invisible).setCreativeTab(tab);
	}

	public static void register(){
		GameRegistry.register(gold_gloves);
		GameRegistry.register(diamond_gloves);
		GameRegistry.register(emerald_gloves);
		GameRegistry.register(lapis_gloves);

		GameRegistry.register(gold_necklace);
		GameRegistry.register(diamond_necklace);
		GameRegistry.register(emerald_necklace);
		GameRegistry.register(lapis_necklace);

		GameRegistry.register(gold_ring);
		GameRegistry.register(diamond_ring);
		GameRegistry.register(emerald_ring);
		GameRegistry.register(lapis_ring);

		GameRegistry.register(cloakBlack);
		GameRegistry.register(cloakBlue);
		GameRegistry.register(cloakBrown);
		GameRegistry.register(cloakCyan);
		GameRegistry.register(cloakGray);
		GameRegistry.register(cloakGreen);
		GameRegistry.register(cloakLightblue);
		GameRegistry.register(cloakLime);
		GameRegistry.register(cloakMagenta);
		GameRegistry.register(cloakOrange);
		GameRegistry.register(cloakPink);
		GameRegistry.register(cloakPurple);
		GameRegistry.register(cloakRed);
		GameRegistry.register(cloakSilver);
		GameRegistry.register(cloakWhite);
		GameRegistry.register(cloakYellow);
		GameRegistry.register(cloak_Invisible);

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
		ModelLoader.setCustomModelResourceLocation(
				item,
				0,
				new ModelResourceLocation(RpgInventory.MODID+":"+
						((RpgInventoryItem)item).getModelLocation()));
	}

	public static enum LocalizeEnum{
		gold_gloves("gold_glove","jewels/gold_glove", "armor/textures/jewels/gold_glove.png"),
		diamond_gloves("diamond_glove","jewels/endented_glove", "armor/textures/jewels/diamond_glove.png"),
		emerald_gloves("emerald_glove","jewels/endented_glove", "armor/textures/jewels/emerald_gloves.png"),
		lapis_gloves("lapis_glove","jewels/endented_glove", "armor/textures/jewels/lapis_gloves.png"),

		gold_necklace("gold_necklace","jewels/gold_necklace","armor/textures/jewels/gold_necklace.png"),
		diamond_necklace("diamond_necklace","jewels/endented_necklace","armor/textures/jewels/diamond_necklace.png"),
		emerald_necklace("emerald_necklace","jewels/endented_necklace","armor/textures/jewels/emerald_necklace.png"),
		lapis_necklace("lapis_necklace","jewels/endented_necklace","armor/textures/jewels/lapis_necklace.png"),

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
		cape_grey("grey_cape","jewels/cape","textures/blocks/wool_colored_grey.png"),
		cape_pink("pink_cape","jewels/cape","textures/blocks/wool_colored_pink.png"),
		cape_lime("lime_cape","jewels/cape","textures/blocks/wool_colored_lime.png"),
		cape_yellow("yellow_cape","jewels/cape","textures/blocks/wool_colored_yellow.png"),
		cape_lightblue("lightblue_cape","jewels/cape","textures/blocks/wool_colored_light_blue.png"),
		cape_magenta("magenta_cape","jewels/cape","textures/blocks/wool_colored_magenta.png"),
		cape_orange("orange_cape","jewels/cape","textures/blocks/wool_colored_orange.png"),
		cape_white("white_cape","jewels/cape","textures/blocks/wool_colored_white.png"),

		cape_invisible("invisible_cape","jewels/cape","textures/blocks/wool_colored_white.png"),

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

	public static void registerItemColor(){
		ItemColors ic = Minecraft.getMinecraft().getItemColors();

		//for capes
		ic.registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				if(stack.getItem() != null)
					if(stack.getItem() instanceof RpgInventoryItem){
						RpgInventoryItem i = (RpgInventoryItem)stack.getItem();
						if((i.colorState < 16) && (i.colorState >= 0)){
							return ItemDye.DYE_COLORS[i.colorState];
						}
					}
				return 0xffffff;
			}
		}, 
				cloakBlack,
				cloakBlue,
				cloakBrown,
				cloakCyan,
				cloakGray,
				cloakGreen,
				cloakLightblue,
				cloakLime,
				cloakMagenta,
				cloakOrange,
				cloakPink,
				cloakPurple,
				cloakRed,
				cloakSilver,
				cloakWhite,
				cloakYellow);

		ic.registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				if(stack.getItem() != null)
					if(stack.getItem() instanceof RpgInventoryItem){
						RpgInventoryItem i = (RpgInventoryItem)stack.getItem();
						if(tintIndex == 1){
							if(i.getUnlocalizedName().contains("diamond"))
								return 0x37b0a5;
							else if (i.getUnlocalizedName().contains("lapis"))
								return 0x0e3dad;
							else if (i.getUnlocalizedName().contains("emerald"))
								return 0x5cb55c;
						}
					}
				return 0xffffff; //white
			}
		},
				lapis_gloves,
				diamond_gloves,
				emerald_gloves,
				lapis_necklace,
				diamond_necklace,
				emerald_necklace,
				lapis_ring,
				diamond_ring,
				emerald_ring
				);
	}		
}
