package subaraki.rpginventory.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import subaraki.rpginventory.network.PacketHandler;
import subaraki.rpginventory.network.PacketSyncOtherInventory;
import subaraki.rpginventory.network.PacketSyncOwnInventory;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerLogin(PlayerLoggedInEvent event){
		if(event.player != null)
			JeweleryEffectsHandler.healEffectMap.put(event.player.getName(), 0);

		if (!event.player.worldObj.isRemote)
			PacketHandler.NETWORK.sendTo(new PacketSyncOwnInventory((EntityPlayerMP)event.player), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void playerChangedDimension(PlayerChangedDimensionEvent event){
		if (!event.player.worldObj.isRemote)
			PacketHandler.NETWORK.sendTo(new PacketSyncOwnInventory((EntityPlayerMP)event.player), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void incomingPlayer(PlayerEvent.StartTracking e){
		if(e.getTarget() instanceof EntityPlayer && e.getEntityPlayer() != null)
			PacketHandler.NETWORK.sendTo(new PacketSyncOtherInventory((EntityPlayer) e.getTarget()), (EntityPlayerMP) e.getEntityPlayer());
	}
}
