package subaraki.rpginventory.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.rpginventory.handler.GuiHandler;
import subaraki.rpginventory.mod.RpgInventory;

public class PacketOpenRpgInventory implements IMessage{

	public PacketOpenRpgInventory() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class PacketOpenRpgInventoryHandler implements IMessageHandler<PacketOpenRpgInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketOpenRpgInventory message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.worldObj).addScheduledTask(() -> {
				EntityPlayerMP player_mp = ctx.getServerHandler().playerEntity;
				World world = player_mp.worldObj;

				FMLNetworkHandler.openGui(
						player_mp, 
						RpgInventory.INSTANCE, 
						GuiHandler.RPG_PLAYER_INVENTORY,
						world, 
						(int)player_mp.posX, (int)player_mp.posY, (int)player_mp.posZ);
			});
			return null;
		}
	}
}