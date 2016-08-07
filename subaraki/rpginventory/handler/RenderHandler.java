package subaraki.rpginventory.handler;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.enums.SlotIndex;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems;

public class RenderHandler {

	private ModelBiped main;

	public RenderHandler(){
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerPreRendering(RenderPlayerEvent.Pre evt){

		RpgPlayerInventory inventory = evt.getEntityPlayer().getCapability(RpgInventoryCapability.CAPABILITY, null);

		ItemStack cloak = inventory.getTheRpgInventory().getStackInSlot(SlotIndex.SLOT_CLOAK.ordinal());

		if(cloak != null){
			if(cloak.getItem() == RpgItems.cloak_Invisible) {
				evt.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void PlayerRender(RenderPlayerEvent.SetArmorModel evt) {

		if(main == null){
			RenderPlayer r = evt.getRenderer();
			//yay, my pull request :3 modelBipedMain got set to public
			main = r.getMainModel();
		}

		RpgPlayerInventory inventory = evt.getEntityPlayer().getCapability(RpgInventoryCapability.CAPABILITY, null);
		ItemStack cloak = inventory.getCloak();
		ItemStack gloves = inventory.getGloves();
		ItemStack necklace = inventory.getNecklace();

		/* ===== RENDERING CLOAK===== */
		if ((cloak != null) && (cloak.getItem() != RpgItems.cloak_Invisible)) {
			rendercape(cloak, evt.getRenderer(), evt.getEntityPlayer(), evt.getPartialRenderTick());
		}

		//		/* ===== RENDERING GLOVES===== */
		//		if (gloves != null) {
		//			mc.renderEngine.bindTexture(((ItemRpgInvArmor) gloves.getItem()).getTexture());
		//
		//			renderGloves();
		//		}
		//
		//		/* ===== RENDERING NECKLACE===== */
		//		if (necklace != null) {
		//			mc.renderEngine.bindTexture(((ItemRpgInvArmor) necklace.getItem()).getTexture());
		//			renderNecklace(evt.entityPlayer);
		//		}
		//
		//
		//		/* ===== RENDERING SHIELDS===== */
		//		if (shield != null) {
		//			mc.renderEngine.bindTexture(((ItemRpgInvArmor) shield.getItem()).getTexture());
		//			if((main != null) && shield.getItem() != null) {
		//				renderShield(shield, evt.entityPlayer, inventory);
		//			}
		//		}
	}

	private void rendercape(ItemStack cloak, RenderPlayer rp, EntityPlayer player, float partialTicks) {

		if (cloak == null)
			return;

		if (cloak.getItem() == null)
			return;

		RpgInventoryItem item = (RpgInventoryItem)cloak.getItem();			
		ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (itemstack.getItem() != Items.ELYTRA){

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			rp.bindTexture(item.getRenderPlayerTexture());
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 0.125F);
			double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
			double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
			double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
			float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
			double d3 = (double)MathHelper.sin(f * 0.017453292F);
			double d4 = (double)(-MathHelper.cos(f * 0.017453292F));
			float f1 = (float)d1 * 10.0F;
			f1 = MathHelper.clamp_float(f1, -6.0F, 32.0F);
			float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
			float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;

			if (f2 < 0.0F)
			{
				f2 = 0.0F;
			}

			float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
			f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

			if (player.isSneaking())
			{
				f1 += 25.0F;
			}

			GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			rp.getMainModel().renderCape(0.0625F);
			GlStateManager.popMatrix();
		}
	}
}
