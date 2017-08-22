package subaraki.rpginventory.render.player;

import lib.modelloader.ModelHandle;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.mod.RpgInventory;

public class LayerRpgCloak implements LayerRenderer<AbstractClientPlayer>{

	private final RenderPlayer playerRenderer;

	private ModelHandle cloak_static = ModelHandle.of(RpgInventory.MODID+":render/cloak");
	private ModelHandle cloak_moving= ModelHandle.of(RpgInventory.MODID+":render/cloak_moving");
	private ModelHandle cloak_shoulder= ModelHandle.of(RpgInventory.MODID+":render/cloak_shoulder");


	public LayerRpgCloak(RenderPlayer playerRendererIn)
	{
		this.playerRenderer = playerRendererIn;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		/**copy of @Link LayerCloak*/
		RpgInventoryData inventory = RpgInventoryData.get(player);
		ItemStack cloak = inventory.getCloak();

		if (cloak.isEmpty())
			return;

		RpgInventoryItem item = (RpgInventoryItem)cloak.getItem();			
		ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (itemstack.isEmpty() || itemstack.getItem() != Items.ELYTRA){

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			String cloak_texture = item.getRenderOnPlayerTexture(cloak).getResourcePath();

			//replace json texture path with corresponding wool path from equipped cloak
			if (!cloak_static.getTextureReplacements().containsKey("0") || cloak_static.getTextureReplacements().containsKey("0") && !cloak_static.getTextureReplacements().get("0").equals(cloak_texture))
				cloak_static = cloak_static.replace("0", cloak_texture );

			if (!cloak_moving.getTextureReplacements().containsKey("0") || cloak_moving.getTextureReplacements().containsKey("0") && !cloak_moving.getTextureReplacements().get("0").equals(cloak_texture))
				cloak_moving = cloak_moving.replace("0", cloak_texture );

			if (!cloak_shoulder.getTextureReplacements().containsKey("0") || cloak_shoulder.getTextureReplacements().containsKey("0") && !cloak_shoulder.getTextureReplacements().get("0").equals(cloak_texture))
				cloak_shoulder = cloak_shoulder.replace("0", cloak_texture );

			playerRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

			renderCloakPart(player, cloak_moving, partialTicks, true);

			ItemStack chest = player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex());
			
			if(chest.isEmpty())
			{
				renderCloakPart(player, cloak_static, partialTicks, false);

				renderCloakShoulder(player, cloak_shoulder, EnumHandSide.RIGHT, partialTicks, true);
				renderCloakShoulder(player, cloak_shoulder, EnumHandSide.RIGHT, partialTicks, false);

				renderCloakShoulder(player, cloak_shoulder, EnumHandSide.LEFT, partialTicks, true);
				renderCloakShoulder(player, cloak_shoulder, EnumHandSide.LEFT, partialTicks, false);
			}
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

	private void renderCloakPart(AbstractClientPlayer player, ModelHandle model, float partialticks, boolean shouldMove){

		GlStateManager.pushMatrix();

		GlStateManager.scale(1.01, 1.01, 1.01);

		float offsetX = 0f;
		float offsetZ = (1f/16f) * 0.5f;
		float offsetY = 1f/16f * 0.5f;

		GlStateManager.translate(offsetX, offsetY, offsetZ);//rotate to centre

		//if(arm == null || arm != null && !shouldMove)
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);

		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);

		ItemStack chest = player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex());
		float chestOffset = 0f;
		if(!chest.isEmpty())
			chestOffset = 1f;
		
		GlStateManager.translate(-0.5f, -(1f/16f)*11, -(1f/16f)*(1.5 + chestOffset));

		if(player.isSneaking())
		{
			GlStateManager.translate(0, -(1f/16f), -(1f/16f)*5);
			GlStateManager.rotate(playerRenderer.getMainModel().bipedBody.rotateAngleX * (180F / (float)Math.PI), 1, 0, 0);
		}

		if(shouldMove)
			rotateCloak(player, null, partialticks);

		//shift back to centre
		GlStateManager.translate(-offsetX, -offsetY, -offsetZ);

		model.render();

		GlStateManager.popMatrix();
	}

	private void renderCloakShoulder(AbstractClientPlayer player, ModelHandle model,
			EnumHandSide handSide, float partialticks, boolean shouldMove){

		GlStateManager.pushMatrix();

		float offsetX = player.isSneaking() ? 0 : 0;
		float offsetZ = player.isSneaking() ? 0 : 0;
		float offsetY = player.isSneaking() ? 0.0625f*2.5f : 0;
		
		GlStateManager.translate(offsetX, offsetY, offsetZ);
		
		//post render / rotate with arm
		playerRenderer.getMainModel().postRenderArm(1f/16f, handSide);

		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);

		if(shouldMove)
		{
			GlStateManager.translate(0,0.0001,0);
			GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
		}
		else
		{
			float r = handSide == EnumHandSide.RIGHT ? 1 : -1;
			GlStateManager.translate((1f/16f)*2f*r, 0, 0);
		}

		float offset = handSide == EnumHandSide.RIGHT ? 3.5f : 1.5f;
		GlStateManager.translate(-(1f/16f)*offset, -(1f/16f)*3 , -(1f/16f)*3);

		if(player.isSneaking())
		{
			GlStateManager.translate(0, 0.0625f*2f, (1f/16f)/ 2.5f);
		}
		GlStateManager.translate(-offsetX, -offsetY, -offsetZ);

		//scale slightly bigger
		GlStateManager.scale(1.01, 1.01, 1.01);

		model.render();
		GlStateManager.popMatrix();
	}

	private void rotateCloak(AbstractClientPlayer player, EnumHandSide handSide, float partialTicks){

		double xMoving = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
		float yaw = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
		double sinRotationYaw = (double)MathHelper.sin(yaw * 0.017453292F);

		double zMoving = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
		double cosRotationYaw = (double)(-MathHelper.cos(yaw * 0.017453292F));

		float yRotation = (float)(xMoving * sinRotationYaw + zMoving * cosRotationYaw) * 100.0F;

		double yMoving = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
		float xRotation = (float)yMoving * 10.0F;

		if (yRotation < 0.0F)
		{
			yRotation = 0.0F;
		}

		//added a check so the cloak doesn't fly over your head when going to fast !
		if (yRotation > 178.0F)
		{
			yRotation = 178.0F;
		}

		float y = 0.7f;
		GlStateManager.translate(0, y, 0);

		GlStateManager.rotate((6.0F + yRotation / 2.0F + xRotation), 1.0F, 0.0F, 0.0F);

		GlStateManager.translate(0, -y, -0);

	}
}
