package subaraki.rpginventory.render.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;

public class RenderCapeLayer implements LayerRenderer<AbstractClientPlayer>{

    private final RenderPlayer playerRenderer;

    public RenderCapeLayer(RenderPlayer playerRendererIn)
    {
        this.playerRenderer = playerRendererIn;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	/**copy of @Link LayerCape*/
    	RpgInventoryData inventory = RpgInventoryData.get(player);
    	ItemStack cloak = inventory.getCloak();
    	
    	
    	if (cloak == ItemStack.EMPTY)
			return;

		if (cloak.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)cloak.getItem();			
		ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (itemstack == ItemStack.EMPTY || itemstack.getItem() != Items.ELYTRA){

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			playerRenderer.bindTexture(item.getRenderOnPlayerTexture());
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 0.125F);
			double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
			double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
			double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
			float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
			double d3 = (double)MathHelper.sin(f * 0.017453292F);
			double d4 = (double)(-MathHelper.cos(f * 0.017453292F));
			float xRotation = (float)d1 * 10.0F;
			xRotation = MathHelper.clamp(xRotation, -6.0F, 32.0F);
			float yRotation = (float)(d0 * d3 + d2 * d4) * 100.0F;
			float zRotation = (float)(d0 * d4 - d2 * d3) * 100.0F;

			if (yRotation < 0.0F)
			{
				yRotation = 0.0F;
			}
			
			//added a check so the cape doesnt fly over your head when going to fast !
			if (yRotation > 178.0F)
			{
				yRotation = 178.0F;
			}

			float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
			xRotation = xRotation + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

			if (player.isSneaking())
			{
				xRotation += 25.0F;
			}

			GlStateManager.rotate(6.0F + yRotation / 2.0F + xRotation, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(zRotation / 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-zRotation / 2.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			if (player.isSneaking())//this isn't necessarily needed, but the space between cape and player while sneaking was killing me !
	            GlStateManager.translate(0.0F, 0.0F, 0.1F);
			playerRenderer.getMainModel().renderCape(0.0625F);
			GlStateManager.popMatrix();
		}
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}
