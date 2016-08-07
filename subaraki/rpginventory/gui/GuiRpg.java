package subaraki.rpginventory.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.gui.container.ContainerRpg;
import subaraki.rpginventory.mod.RpgInventory;
import subaraki.rpginventory.utility.Util;

public class GuiRpg extends GuiContainer {

	public static final ResourceLocation gui = 
			new ResourceLocation(RpgInventory.MODID,"textures/gui/gui_inventory.png");

	public GuiRpg(EntityPlayer player, RpgPlayerInventory inv) {
		super(new ContainerRpg(player, inv));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, "Rpg", (this.width / 2) + 39,(this.height / 2) - 23, 0xffffff);
		drawString(fontRendererObj, "Inventory", (this.width / 2) + 39,(this.height / 2) - 15, 0xffffff);

		Util.drawEntityOnScreen(posX + 51, posY + 75, 30, (float)(posX + 51), (float)(posY + 75 - 50), this.mc.thePlayer);

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
