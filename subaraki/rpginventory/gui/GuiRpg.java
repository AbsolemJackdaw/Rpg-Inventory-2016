package subaraki.rpginventory.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiRpg extends GuiContainer {

	public GuiRpg(EntityPlayer player, RpgPlayerInventory inv) {
		super(new ContainerRpg(player, inv));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

	}

}
