package subaraki.rpginventory.render.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.render.models.ModelGloveLeft;
import subaraki.rpginventory.render.models.ModelGloveRight;

public class RenderGloveLayer implements LayerRenderer<AbstractClientPlayer>{

	private static final ModelGloveLeft GLOVE_MODEL_LEFT = new ModelGloveLeft();
	private static final ModelGloveRight GLOVE_MODEL_RIGHT = new ModelGloveRight();

	RenderPlayer rp;

	public RenderGloveLayer(RenderPlayer rp) {
		this.rp = rp;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		RpgPlayerInventory inventory = entitylivingbaseIn.getCapability(RpgInventoryCapability.CAPABILITY, null);

		ItemStack glove = inventory.getGloves();

		if(glove == null)
			return;
		if (glove.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)glove.getItem();	

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		rp.bindTexture(item.getRenderOnPlayerTexture());
		
		GlStateManager.pushMatrix();
		for (int i = 0; i < GLOVE_MODEL_LEFT.parts.size(); i++) {
			GLOVE_MODEL_LEFT.parts.get(i).rotateAngleX = rp.getMainModel().bipedLeftArm.rotateAngleX;
			GLOVE_MODEL_LEFT.parts.get(i).rotateAngleY = rp.getMainModel().bipedLeftArm.rotateAngleY;
			GLOVE_MODEL_LEFT.parts.get(i).rotateAngleZ = rp.getMainModel().bipedLeftArm.rotateAngleZ;
			GLOVE_MODEL_LEFT.parts.get(i).rotationPointX = rp.getMainModel().bipedLeftArm.rotationPointX;
			GLOVE_MODEL_LEFT.parts.get(i).rotationPointY = rp.getMainModel().bipedLeftArm.rotationPointY;
			GLOVE_MODEL_LEFT.parts.get(i).rotationPointZ = rp.getMainModel().bipedLeftArm.rotationPointZ;
		}
		GlStateManager.color(1,1,1,1);
		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
		GLOVE_MODEL_LEFT.renderLeftGlove(0.0625f);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		for (int i = 0; i < GLOVE_MODEL_RIGHT.parts.size(); i++) {
			GLOVE_MODEL_RIGHT.parts.get(i).rotateAngleX = rp.getMainModel().bipedRightArm.rotateAngleX;
			GLOVE_MODEL_RIGHT.parts.get(i).rotateAngleY = rp.getMainModel().bipedRightArm.rotateAngleY;
			GLOVE_MODEL_RIGHT.parts.get(i).rotateAngleZ = rp.getMainModel().bipedRightArm.rotateAngleZ;
			GLOVE_MODEL_RIGHT.parts.get(i).rotationPointX = rp.getMainModel().bipedRightArm.rotationPointX;
			GLOVE_MODEL_RIGHT.parts.get(i).rotationPointY = rp.getMainModel().bipedRightArm.rotationPointY;
			GLOVE_MODEL_RIGHT.parts.get(i).rotationPointZ = rp.getMainModel().bipedRightArm.rotationPointZ;
		}
		GlStateManager.color(1,1,1,1);
		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
		GLOVE_MODEL_RIGHT.renderRightGlove(0.0625f);
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
