package subaraki.rpginventory.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.mod.RpgInventory;

public class PacketInventoryToClient implements IMessage {

	public ItemStack stack[] = new ItemStack[6];

	public PacketInventoryToClient() {
	}

	public PacketInventoryToClient(EntityPlayer player) {
		for(int i = 0; i < stack.length; i ++)
			stack[i] = RpgInventoryData.get(player).getInventory().getStackInSlot(i);
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

	public static class PacketInventoryToClientHandler implements IMessageHandler<PacketInventoryToClient, IMessage>{

		@Override
		public IMessage onMessage(PacketInventoryToClient message,MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask( ()->{
				EntityPlayer player = RpgInventory.proxy.getClientPlayer();

				if(player == null)
				{
					RpgInventory.log.error("could not sync client with inventory !");
					return;
				}
				
				for (int i = 0; i < message.stack.length; i++){
					RpgInventoryData.get(player).getInventory().setStackInSlot(i,message.stack[i]);
				}
			});
			return null;
		}
	}
}
