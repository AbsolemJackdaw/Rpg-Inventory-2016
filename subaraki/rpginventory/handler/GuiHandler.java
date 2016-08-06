package subaraki.rpginventory.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import subaraki.rpginventory.capability.RpgInventoryCapability;
import subaraki.rpginventory.gui.GuiRpg;
import subaraki.rpginventory.gui.container.ContainerRpg;

public class GuiHandler implements IGuiHandler {

	public static final int RPG_PLAYER_INVENTORY = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == RPG_PLAYER_INVENTORY){
			return new ContainerRpg(player, player.getCapability(RpgInventoryCapability.CAPABILITY, null));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == RPG_PLAYER_INVENTORY){
			return new GuiRpg(player, player.getCapability(RpgInventoryCapability.CAPABILITY, null));
		}

		return null;
	}
}
