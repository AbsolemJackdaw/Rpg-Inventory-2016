package subaraki.rpginventory.handler.proxy;

import static subaraki.rpginventory.item.RpgItems.cloak;
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
import subaraki.rpginventory.render.player.LayerRpgCloak;
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

		//for cloaks
		ic.registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return ItemDye.DYE_COLORS[15-stack.getMetadata()];
			}
		}, 
				cloak);

		ic.registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
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

			LayerRpgCloak cloak = new LayerRpgCloak(renderer);
			renderer.addLayer(cloak);
		}
	}
}
