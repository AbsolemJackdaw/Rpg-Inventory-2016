package subaraki.rpginventory.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketInventoryToClient;
import subaraki.rpginventory.network.PacketInventoryToTrackedPlayer;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerLogin(PlayerLoggedInEvent event){
		if (!event.player.world.isRemote)
		{
			EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
			PacketHandler.NETWORK.sendTo(new PacketInventoryToClient(event.player), playerMP);
		}
	}

	@SubscribeEvent
	public void playerChangedDimension(PlayerChangedDimensionEvent event){
		if (!event.player.world.isRemote)
			PacketHandler.NETWORK.sendTo(new PacketInventoryToClient((EntityPlayerMP)event.player), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void incomingPlayer(PlayerEvent.StartTracking event){
		if(event.getTarget() instanceof EntityPlayer && event.getEntityPlayer() != null)
			PacketHandler.NETWORK.sendTo(new PacketInventoryToTrackedPlayer((EntityPlayer) event.getTarget()), (EntityPlayerMP) event.getEntityPlayer());
	}
}
