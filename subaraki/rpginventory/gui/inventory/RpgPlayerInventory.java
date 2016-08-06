package subaraki.rpginventory.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.items.ItemStackHandler;

public class RpgPlayerInventory {

	private RpgStackHandler ish = new RpgStackHandler();
	
	@CapabilityInject(RpgPlayerInventory.class)
	public static Capability<RpgPlayerInventory> INSTANCE;

	/**only accounts for the string.*/
	public static final ResourceLocation PROP_KEY = new ResourceLocation("subaraki:rpgInventoryPlayer");

	public RpgStackHandler getInventory(){
		return ish;
	}

	public static void register(){
		CapabilityManager.INSTANCE.register(RpgPlayerInventory.class, new InventoryStorage(), () -> null );
	}

	public static final RpgPlayerInventory get(EntityPlayer p){
		return p.getCapability(INSTANCE, null);
	}
	
	public static RpgStackHandler getInventory(EntityPlayer p){
		return p.getCapability(INSTANCE, null).getInventory();
	}

	public class RpgStackHandler extends ItemStackHandler{
		public RpgStackHandler() {
			super(new ItemStack[5]);
		}
	}
}
