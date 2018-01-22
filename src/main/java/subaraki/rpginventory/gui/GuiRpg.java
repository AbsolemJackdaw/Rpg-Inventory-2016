package subaraki.rpginventory.gui;

import lib.Lib;
import lib.util.DrawEntityOnScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.gui.container.ContainerRpg;
import subaraki.rpginventory.handler.loot.LootEvent;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.mod.RpgInventory;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketInventoryToServerAndTrackedPlayers;

public class GuiRpg extends GuiContainer {

	public static final ResourceLocation gui = 
			new ResourceLocation(RpgInventory.MODID,"textures/gui/gui_inventory.png");
	private float oldMouseX;
	private float oldMouseY;

	private ItemStack heldItem;

	private EntityPlayer player;

	public GuiRpg(EntityPlayer player, RpgInventoryData inv) {
		super(new ContainerRpg(player, inv));

		this.player = Lib.proxy.clientPlayer();

		heldItem = player.getHeldItemMainhand();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRenderer, "Rpg Inventory", guiLeft+5, guiTop-5, 0xffffff);

		DrawEntityOnScreen.drawEntityOnScreen(posX + 51, posY + 75, 30, (float)(posX + 51)-oldMouseX, (float)(posY + 75 - 50)-oldMouseY, player);

		double heartsMax = player.getMaxHealth() + player.getAbsorptionAmount();
		double hearts = player.getHealth() + player.getAbsorptionAmount();

		fontRenderer.drawStringWithShadow((int)hearts + "/" +(int)heartsMax, guiLeft+this.xSize - 39, guiTop+14, 0xffffff);

		double armor = player.getTotalArmorValue();
		fontRenderer.drawStringWithShadow(Double.toString(armor), guiLeft+this.xSize - 25 - (fontRenderer.getStringWidth(Double.toString(armor)))/2, guiTop + 27, 0xffffff);

		double strength = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		if(!heldItem.isEmpty())
		{
			float mat = 0f;
			if(heldItem.getItem()  instanceof ItemSword)
				mat = ((ItemSword)heldItem.getItem()).getAttackDamage() + 3.0F;
			if(heldItem .getItem() instanceof ItemTool)
				mat = ToolMaterial.valueOf(((ItemTool)heldItem.getItem()).getToolMaterialName()).getAttackDamage();
			strength+=mat;
		}

		PotionEffect active = player.getActivePotionEffect(MobEffects.STRENGTH);
		if(active != null)
			for(AttributeModifier modifier :active.getPotion().getAttributeModifierMap().values())
				strength+=(active.getPotion().getAttributeModifierAmount(active.getAmplifier(), modifier));

		strength+= jewelDamage();

		fontRenderer.drawStringWithShadow(Double.toString(strength), guiLeft+this.xSize-32, guiTop+38, 0xffffff);

		double speed = player.getAIMoveSpeed();
		speed *=10;
		fontRenderer.drawStringWithShadow(Double.toString(speed).substring(0, 4), guiLeft+this.xSize-32, guiTop+64, 0xffffff);

		/////////////////////////////////////////
		int averageHealRate = RpgInventoryData.get(player).getAverageHealingRate();
		double seconds = (double)averageHealRate/20;
		double health = 1d / seconds;
		String s = Double.toString(health);
		fontRenderer.drawStringWithShadow((s.length() > 3 ? s.substring(0, 3) : s)+"/s", guiLeft+this.xSize-35, guiTop+51, 0xffffff);
//		fontRenderer.drawStringWithShadow("1/"+Double.toString(seconds).substring(0, Math.min(Double.toString(seconds).length(), 3))+"s", guiLeft+this.xSize-40, guiTop+51, 0xffffff);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.oldMouseX = (float)mouseX;
		this.oldMouseY = (float)mouseY;
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		PacketHandler.NETWORK.sendToServer(new PacketInventoryToServerAndTrackedPlayers(mc.player));
	}

	/////////////////////////////////////////////////////

	private float jewelDamage(){
		RpgInventoryData inventory = RpgInventoryData.get(player);
		float extraDamage = 0;

		if(!inventory.getNecklace().isEmpty() && inventory.getNecklace().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getGloves().isEmpty() && inventory.getGloves().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getRing_1().isEmpty() && inventory.getRing_1().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;
		if(!inventory.getRing_2().isEmpty() && inventory.getRing_2().getItem().getUnlocalizedName().contains("lapis"))
			extraDamage +=1.5;

		extraDamage += 1.5*(float)cloakLevel(inventory, LootEvent.CLOAK_STRENGHT);

		return extraDamage;
	}

	private int healRateJewelry(RpgInventoryData data)
	{
		int delay = 75;

		if(!data.getNecklace().isEmpty() && data.getNecklace().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getGloves().isEmpty()   && data.getGloves().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getRing_1().isEmpty()   && data.getRing_1().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;
		if(!data.getRing_2().isEmpty()   && data.getRing_2().getItem().getUnlocalizedName().contains("diamond"))
			delay -=10;

		float level = (float)cloakLevel(data, LootEvent.CLOAK_HEALING);

		if(level > 0)
			delay -= 10 * level;

		return delay;
	}

	private int cloakLevel(RpgInventoryData inventory, String cloaktype){
		ItemStack cloak = inventory.getCloak();

		if(!cloak.isEmpty() && cloak.getItem().equals(RpgItems.cloak))
		{
			if(cloak.hasTagCompound())
			{
				NBTTagCompound tag = cloak.getTagCompound();

				if(tag.hasKey(LootEvent.TAG_AMOUNT))
				{
					int loop = tag.getInteger(LootEvent.TAG_AMOUNT);
					boolean hasCloak = false;
					int entry = 0;
					for(int i = 0; i < loop; i++)
					{
						if(tag.getString(LootEvent.TAG_AFFINITY+i).equals(cloaktype))
						{
							hasCloak=true;
							entry = i;
							break;
						}
					}
					if(hasCloak)
					{
						int level = tag.getInteger(LootEvent.TAG_LVL+entry);
						return level;
					}
				}
			}
		}
		return 0;
	}
}
