package subaraki.rpginventory.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import subaraki.rpginventory.gui.inventory.RpgPlayerInventory;
import subaraki.rpginventory.gui.inventory.container.ContainerRpg;

public class GuiRpg extends GuiContainer {

	public GuiRpg(EntityPlayer player, RpgPlayerInventory inv) {
		super(new ContainerRpg(player, inv));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

	}

}
