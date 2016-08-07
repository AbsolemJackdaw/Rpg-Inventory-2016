package subaraki.rpginventory.capability.playerinventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import subaraki.rpginventory.mod.RpgInventory;

public class CapabilityInventoryProvider implements ICapabilitySerializable<NBTTagCompound>
{
    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(RpgInventory.MODID, "rpg_player_inventory");

    /**
     * The instance that we are providing
     */
    final RpgPlayerInventory slots = new RpgPlayerInventory();

    /**gets called before world is initiated. player.worldObj will return null here !*/
    public CapabilityInventoryProvider(EntityPlayer player){
        slots.setPlayer(player);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == RpgInventoryCapability.CAPABILITY)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing){
        if (capability == RpgInventoryCapability.CAPABILITY)
            return (T)slots;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT(){
        return (NBTTagCompound) RpgInventoryCapability.CAPABILITY.writeNBT(slots, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt){
    	RpgInventoryCapability.CAPABILITY.readNBT(slots, null, nbt);
    }
}
