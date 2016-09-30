package subaraki.rpginventory.capability.playerinventory;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class RpgInventoryCapability {

	/*
	 * This field will contain the forge-allocated Capability class.
	 * This instance will be initialized internally by Forge, upon calling register.
	 */
	@CapabilityInject(RpgPlayerInventory.class)
	public static Capability<RpgPlayerInventory> CAPABILITY;

	/*
	 * This registers our capability to the manager
	 */
	public void register(){
		CapabilityManager.INSTANCE.register(

				// This is the class the capability works with
				RpgPlayerInventory.class,

				// This is a helper for users to save and load
				new StorageHelper(),

				// This is a factory for default instances
				new DefaultInstanceFactory()
				);
	}

	/*
	 * This class handles saving and loading the data.
	 */
	public static class StorageHelper implements Capability.IStorage<RpgPlayerInventory>{

		@Override
		public NBTBase writeNBT(Capability<RpgPlayerInventory> capability, RpgPlayerInventory instance, EnumFacing side)		{
			//return instance.getTheRpgInventory().serializeNBT();
			return instance.writeData();
		}

		@Override
		public void readNBT(Capability<RpgPlayerInventory> capability, RpgPlayerInventory instance, EnumFacing side, NBTBase nbt){
//			instance.getTheRpgInventory().deserializeNBT((NBTTagCompound)nbt);
			instance.readData(nbt);
		}
	}

	/*
	 * This class handles constructing new instances for this capability
	 */
	public static class DefaultInstanceFactory implements Callable<RpgPlayerInventory>{
		@Override
		public RpgPlayerInventory call() throws Exception{
			return new RpgPlayerInventory();
		}
	}
}
