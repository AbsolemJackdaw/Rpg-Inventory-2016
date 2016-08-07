package subaraki.rpginventory.handler.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.render.RenderCapeLayer;
import subaraki.rpginventory.render.RenderNecklaceLayer;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenders(){
		RpgItems.registerRenders();
	}
	
	@Override
	public void registerColors(){
		RpgItems.registerItemColor();
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	public void addRenderLayers(){
		RenderPlayer p = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
		RenderPlayer p2 = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));

		//add one layer for both skins (steve and alex)
		p.addLayer(new RenderCapeLayer(p));
		p.addLayer(new RenderCapeLayer(p));
		
		p.addLayer(new RenderNecklaceLayer(p));
		p.addLayer(new RenderNecklaceLayer(p));

	}
}
