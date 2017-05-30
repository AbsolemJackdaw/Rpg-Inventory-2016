package subaraki.rpginventory.handler.proxy;

import static subaraki.rpginventory.item.RpgItems.cloakBlack;
import static subaraki.rpginventory.item.RpgItems.cloakBlue;
import static subaraki.rpginventory.item.RpgItems.cloakBrown;
import static subaraki.rpginventory.item.RpgItems.cloakCyan;
import static subaraki.rpginventory.item.RpgItems.cloakGray;
import static subaraki.rpginventory.item.RpgItems.cloakGreen;
import static subaraki.rpginventory.item.RpgItems.cloakLightblue;
import static subaraki.rpginventory.item.RpgItems.cloakLime;
import static subaraki.rpginventory.item.RpgItems.cloakMagenta;
import static subaraki.rpginventory.item.RpgItems.cloakOrange;
import static subaraki.rpginventory.item.RpgItems.cloakPink;
import static subaraki.rpginventory.item.RpgItems.cloakPurple;
import static subaraki.rpginventory.item.RpgItems.cloakRed;
import static subaraki.rpginventory.item.RpgItems.cloakSilver;
import static subaraki.rpginventory.item.RpgItems.cloakWhite;
import static subaraki.rpginventory.item.RpgItems.cloakYellow;
import static subaraki.rpginventory.item.RpgItems.diamond_gloves;
import static subaraki.rpginventory.item.RpgItems.diamond_necklace;
import static subaraki.rpginventory.item.RpgItems.diamond_ring;
import static subaraki.rpginventory.item.RpgItems.emerald_gloves;
import static subaraki.rpginventory.item.RpgItems.emerald_necklace;
import static subaraki.rpginventory.item.RpgItems.emerald_ring;
import static subaraki.rpginventory.item.RpgItems.lapis_gloves;
import static subaraki.rpginventory.item.RpgItems.lapis_necklace;
import static subaraki.rpginventory.item.RpgItems.lapis_ring;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import subaraki.rpginventory.handler.client.RenderHandler;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.render.player.LayerRpgCape;
import subaraki.rpginventory.render.player.LayerRpgGlove;
import subaraki.rpginventory.render.player.LayerRpgNecklace;

public class ClientProxy extends ServerProxy {

	public static KeyBinding keyInventory;

	@Override
	public void registerKey() {
		keyInventory = new KeyBinding("RPG Inventory Key", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode(), "Rpg Inventory");
		ClientRegistry.registerKeyBinding(keyInventory);
	}
	@Override
	public void registerRenders(){
		RpgItems.registerRenders();
	}

	@Override
	public void registerClientEvents(){
		new RenderHandler();
	}

	@Override
	public void registerColors(){
		ItemColors ic = Minecraft.getMinecraft().getItemColors();

		//for capes
		ic.registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				if(stack.getItem() != null)
					if(stack.getItem() instanceof RpgInventoryItem){
						RpgInventoryItem i = (RpgInventoryItem)stack.getItem();
						if((i.getColorState() < 16) && (i.getColorState() >= 0)){
							return ItemDye.DYE_COLORS[i.getColorState()];
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

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public void addRenderLayers(){

		String types[] = new String[]{"default","slim"};

		for(String type : types){
			RenderPlayer renderer = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get(type));
			renderer.addLayer(new LayerRpgNecklace(renderer));
			renderer.addLayer(new LayerRpgGlove(renderer));
			
			LayerRpgCape cape = new LayerRpgCape(renderer);
			renderer.addLayer(cape);
		}
	}
}
