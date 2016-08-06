package subaraki.rpginventory.gui.inventory;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class InventoryStorage implements IStorage<RpgPlayerInventory>{

	@Override
	public NBTBase writeNBT(Capability<RpgPlayerInventory> capability, RpgPlayerInventory instance, EnumFacing side) {

		if (!(instance instanceof RpgPlayerInventory))
			return null;

		NBTTagCompound data = new NBTTagCompound();
		//data.writeX(x, instance.stuff());
		return data;
	}

	@Override
	public void readNBT(Capability<RpgPlayerInventory> capability, RpgPlayerInventory instance, EnumFacing side,
			NBTBase nbt) {

		if (!(instance instanceof RpgPlayerInventory))
			return;

		NBTTagCompound data = (NBTTagCompound) nbt;
		//instance.setX(data.get bla);
	}

}
