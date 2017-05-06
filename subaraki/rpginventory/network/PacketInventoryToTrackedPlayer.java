package subaraki.rpginventory.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.mod.RpgInventory;

/**
 * This packet is viewed by another player,
 * who is getting your data to know what you have, so otherUser is you
 */
public class PacketInventoryToTrackedPlayer implements IMessage {


	public int otherUser;
	public ItemStack stack[] = new ItemStack[6];

	public PacketInventoryToTrackedPlayer() {
		//default constructor is needed else the game crashes
	}

	public PacketInventoryToTrackedPlayer(EntityPlayer player) {
		RpgInventoryData inv = RpgInventoryData.get(player);

		otherUser = player.getEntityId();

		for (int i = 0; i < stack.length; i++){
			stack[i] = inv.getInventory().getStackInSlot(i);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		otherUser = buf.readInt();

		for (int i = 0; i < stack.length; i++)
			stack[i] = ByteBufUtils.readItemStack(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(otherUser);

		for (int i = 0; i < stack.length; i++)
			ByteBufUtils.writeItemStack(buf, stack[i]);


	}

	public static class PacketInventoryToTrackedPlayerHandler implements IMessageHandler<PacketInventoryToTrackedPlayer, IMessage>{

		@Override
		public IMessage onMessage(PacketInventoryToTrackedPlayer message,MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask( ()->{
				EntityPlayer other = (EntityPlayer)RpgInventory.proxy.getClientWorld().getEntityByID(message.otherUser);

				if(other != null){
					RpgInventoryData rpg = RpgInventoryData.get(other);
					if(rpg != null)
						for (int i = 0; i < message.stack.length; i++)
							rpg.getInventory().setStackInSlot(i,message.stack[i]);
					else
						FMLLog.getLogger().info("packet info. 'inventory' was null. dropping packet");
				}else
					FMLLog.getLogger().info("packet info. 'other' was null. dropping packet");
			});
			return null;
		}
	}
}
