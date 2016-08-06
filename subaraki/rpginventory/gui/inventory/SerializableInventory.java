package subaraki.rpginventory.gui.inventory;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SerializableInventory implements ICapabilitySerializable<NBTTagCompound>{

	RpgPlayerInventory inventory = new RpgPlayerInventory();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == RpgPlayerInventory.INSTANCE)
			return true;
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == RpgPlayerInventory.INSTANCE)
			return (T)RpgPlayerInventory.INSTANCE;
		return null;
	}

	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) inventory.INSTANCE.writeNBT(inventory, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		inventory.INSTANCE.readNBT(inventory, null, nbt);
	}
}
