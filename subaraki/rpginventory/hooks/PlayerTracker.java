package subaraki.rpginventory.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
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

		//set mage index always, no matter what, so if the player changes name, his wand will change
		//and this way I do not need to save it to a data file, which could be altered.
		setMageIndex(event.player);
		
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
	
	/**sets the index for the player that determines what kind of mage wand he has*/
	private void setMageIndex(EntityPlayer player){
		String playerName = player.getDisplayNameString();
		String name = playerName.substring(0, 4); //only get the four first letters
		//a reference alphabet for index retrieving
		final String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		//make an itterable array out of the 4 letter name
		char[] nameArray = name.toCharArray();
		 
		String pre_mageIndex = "";

		for(int i = 0; i < name.length(); i++){
			if(alphabet.indexOf(nameArray[i]) > -1)
				pre_mageIndex += alphabet.indexOf(nameArray[i]);
			else 
				pre_mageIndex+=1; //add 1 for special characters
		}
		//convert string to long (int could be/has been to short)
		long mageIdentifier = Long.parseLong(pre_mageIndex);
		//convert that long into hexadecimal
		String mid_mageIndex = Long.toHexString(mageIdentifier);
		//get the last index of the hexadecimal string
		String post_mageIndex = mid_mageIndex.substring(mid_mageIndex.length()-1, mid_mageIndex.length());
		//parse it back int an int to get an index that can be used as metadata
		int mageIndex = Integer.parseInt(post_mageIndex, 16); //16 stands for hex

		System.out.println("player name : "+name);
		System.out.println("converts to : "+pre_mageIndex);
		System.out.println("to hex gives: "+mid_mageIndex);
		System.out.println("last digit converts to metadata : "+post_mageIndex);

		player.getCapability(RpgInventoryCapability.CAPABILITY, null).setMageIndex(mageIndex);
	}
}
