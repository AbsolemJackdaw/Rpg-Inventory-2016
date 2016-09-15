package subaraki.rpginventory.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.mod.RpgInventory;

public class PacketSyncOwnInventory implements IMessage {

	public ItemStack stack[] = new ItemStack[6];

	public PacketSyncOwnInventory() {
	}

	public PacketSyncOwnInventory(EntityPlayer player) {
		RpgPlayerInventory inv = player.getCapability(RpgInventoryCapability.CAPABILITY, null);
		
		for(int i = 0; i < stack.length; i ++)
			stack[i] = inv.getTheRpgInventory().getStackInSlot(i);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		for (int i = 0; i < stack.length; i++){
			stack[i] = ByteBufUtils.readItemStack(buf);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		for (int i = 0; i < stack.length; i++) {
			ByteBufUtils.writeItemStack(buf, stack[i]);
		}
	}

	public static class HandlerSyncOwnInventory implements IMessageHandler<PacketSyncOwnInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOwnInventory message,MessageContext ctx) {
			EntityPlayer player = RpgInventory.proxy.getClientPlayer();

			if(player == null)
				return null;
			
			RpgPlayerInventory rpg = 
					player.
					getCapability(
							RpgInventoryCapability.CAPABILITY, null);

			for (int i = 0; i < message.stack.length; i++){
				rpg.getTheRpgInventory().setStackInSlot(i,message.stack[i]);
			}
			return null;
		}
	}


}
