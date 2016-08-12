package subaraki.rpginventory.render.player;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.render.models.ModelNecklace;

public class RenderNecklaceLayer implements LayerRenderer<AbstractClientPlayer>{

	private static final ModelNecklace NECKLACE_MODEL = new ModelNecklace();

	RenderPlayer rp;
	
	public RenderNecklaceLayer(RenderPlayer rp) {
		this.rp = rp;
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		RpgPlayerInventory inventory = entitylivingbaseIn.getCapability(RpgInventoryCapability.CAPABILITY, null);
		
		ItemStack necklace = inventory.getNecklace();
		
		if(necklace == null)
			return;
		if (necklace.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)necklace.getItem();	
		
		ItemStack chest = entitylivingbaseIn.inventory.armorItemInSlot(2);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		rp.bindTexture(item.getRenderOnPlayerTexture());
		
		GlStateManager.pushMatrix();
		for (int i = 0; i < NECKLACE_MODEL.parts.size(); i++) {
			NECKLACE_MODEL.parts.get(i).rotateAngleX = rp.getMainModel().bipedBody.rotateAngleX;
			NECKLACE_MODEL.parts.get(i).rotateAngleY = rp.getMainModel().bipedBody.rotateAngleY;
			NECKLACE_MODEL.parts.get(i).rotateAngleZ = rp.getMainModel().bipedBody.rotateAngleZ;
			NECKLACE_MODEL.parts.get(i).rotationPointX = rp.getMainModel().bipedBody.rotationPointX;
			NECKLACE_MODEL.parts.get(i).rotationPointY = rp.getMainModel().bipedBody.rotationPointY;
			NECKLACE_MODEL.parts.get(i).rotationPointZ = rp.getMainModel().bipedBody.rotationPointZ;
		}
		if (chest != null)
			GlStateManager.translate(0F, 0F, -0.06F);
		else
			GlStateManager.translate(0F, 0F, 0.0F);

		GlStateManager.color(1,1,1,1);
		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
		NECKLACE_MODEL.renderNecklace(0.0625f);
		GlStateManager.popMatrix();

	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
