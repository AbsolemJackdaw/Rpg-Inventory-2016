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
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.mod.RpgInventory;

/**
 * This packet is viewed by another player,
 * who is getting your data to know what you have, so otherUser is you
 */
public class PacketSyncOtherInventory implements IMessage {


	public int otherUser;
	public ItemStack stack[] = new ItemStack[6];

	public PacketSyncOtherInventory() {
		//default constructor is needed else the game crashes
	}

	public PacketSyncOtherInventory(EntityPlayer player) {
		RpgPlayerInventory inv = player.getCapability(RpgInventoryCapability.CAPABILITY, null);

		otherUser = player.getEntityId();

		for (int i = 0; i < stack.length; i++){
			stack[i] = inv.getTheRpgInventory().getStackInSlot(i);
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

	public static class HandlerSyncOtherInventory implements IMessageHandler<PacketSyncOtherInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOtherInventory message,MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask( ()->{
				EntityPlayer other = (EntityPlayer)RpgInventory.proxy.getClientWorld().getEntityByID(message.otherUser);

				if(other != null){
					RpgPlayerInventory rpg = other.getCapability(RpgInventoryCapability.CAPABILITY, null);
					if(rpg != null)
						for (int i = 0; i < message.stack.length; i++)
							rpg.getTheRpgInventory().setStackInSlot(i,message.stack[i]);
					else
						FMLLog.getLogger().info("packet info. 'inventory' was null. dropping packet");
				}else
					FMLLog.getLogger().info("packet info. 'other' was null. dropping packet");
			});
			return null;
		}
	}
}
