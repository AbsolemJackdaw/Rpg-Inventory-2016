package subaraki.rpginventory.render.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.render.models.ModelNecklace;

public class LayerRpgNecklace implements LayerRenderer<AbstractClientPlayer>{

	private static final ModelNecklace NECKLACE_MODEL = new ModelNecklace();

	RenderPlayer rp;
	
	public LayerRpgNecklace(RenderPlayer rp) {
		this.rp = rp;
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		RpgInventoryData inventory = RpgInventoryData.get(entitylivingbaseIn);
		
		ItemStack necklace = inventory.getNecklace();
		
		if(necklace == ItemStack.EMPTY)
			return;
		if (necklace.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)necklace.getItem();	
		
		ItemStack chest = entitylivingbaseIn.inventory.armorItemInSlot(2);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		rp.bindTexture(item.getRenderOnPlayerTexture());
		
		GlStateManager.pushMatrix();
		
		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
		
		rp.getMainModel().bipedBody.postRender(0.0625f);
	
		if (chest != ItemStack.EMPTY)
			GlStateManager.translate(0F, -0.005F, -0.06F);
		else
			GlStateManager.translate(0F, -0.005F, 0.0F);

		GlStateManager.color(1,1,1,1);
		
		NECKLACE_MODEL.renderNecklace(0.0625f);
		GlStateManager.popMatrix();

	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
