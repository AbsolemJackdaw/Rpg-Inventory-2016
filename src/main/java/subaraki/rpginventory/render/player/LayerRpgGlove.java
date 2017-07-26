package subaraki.rpginventory.render.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.render.models.ModelGloveLeft;
import subaraki.rpginventory.render.models.ModelGloveRight;

public class LayerRpgGlove implements LayerRenderer<AbstractClientPlayer>{

	private static final ModelGloveLeft GLOVE_MODEL_LEFT = new ModelGloveLeft();
	private static final ModelGloveRight GLOVE_MODEL_RIGHT = new ModelGloveRight();

	RenderPlayer rp;

	public LayerRpgGlove(RenderPlayer rp) {
		this.rp = rp;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		RpgInventoryData inventory = RpgInventoryData.get(entitylivingbaseIn);

		ItemStack glove = inventory.getGloves();

		if(glove == ItemStack.EMPTY)
			return;
		if (glove.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)glove.getItem();	

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		rp.bindTexture(item.getRenderOnPlayerTexture());

		GlStateManager.pushMatrix();

		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
			GlStateManager.translate(0.0F, 0.2F, 0.0F);
		rp.getMainModel().bipedLeftArm.postRender(0.0625f);

		//small arms / alex arms
		if(!entitylivingbaseIn.getSkinType().equals("default"))
		{
			GlStateManager.scale(0.8F, 1F, 1F);
			GlStateManager.translate(-0.015, 0, 0);
		}

		GlStateManager.translate(-0.315, -0.1, 0);
		GlStateManager.color(1,1,1,1);
		GLOVE_MODEL_LEFT.renderLeftGlove(0.0625f);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		/**offset when sneaking. copy of @Link ModelPlayer.render*/ 
		if (entitylivingbaseIn.isSneaking())
			GlStateManager.translate(0.0F, 0.2F, 0.0F);
		rp.getMainModel().bipedRightArm.postRender(0.0625f);

		//small arms / alex arms
		if(!entitylivingbaseIn.getSkinType().equals("default"))
		{
			GlStateManager.scale(0.8F, 1F, 1F);
			GlStateManager.translate(0.015, 0, 0);
		}

		GlStateManager.translate(0.315, -0.1, 0);
		GlStateManager.color(1,1,1,1);
		GLOVE_MODEL_RIGHT.renderRightGlove(0.0625f);
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
