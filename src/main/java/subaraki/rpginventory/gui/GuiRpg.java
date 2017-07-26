package subaraki.rpginventory.gui;

import lib.util.DrawEntityOnScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.gui.container.ContainerRpg;
import subaraki.rpginventory.mod.RpgInventory;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketInventoryToServerAndTrackedPlayers;

public class GuiRpg extends GuiContainer {

	public static final ResourceLocation gui = 
			new ResourceLocation(RpgInventory.MODID,"textures/gui/gui_inventory.png");
	private float oldMouseX;
	private float oldMouseY;

	public GuiRpg(EntityPlayer player, RpgInventoryData inv) {
		super(new ContainerRpg(player, inv));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRenderer, "Rpg", (this.width / 2) + 39,(this.height / 2) - 23, 0xffffff);
		drawString(fontRenderer, "Inventory", (this.width / 2) + 39,(this.height / 2) - 15, 0xffffff);

		DrawEntityOnScreen.drawEntityOnScreen(posX + 51, posY + 75, 30, (float)(posX + 51)-oldMouseX, (float)(posY + 75 - 50)-oldMouseY, this.mc.player);

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		PacketHandler.NETWORK.sendToServer(new PacketInventoryToServerAndTrackedPlayers(mc.player));
	}
}
