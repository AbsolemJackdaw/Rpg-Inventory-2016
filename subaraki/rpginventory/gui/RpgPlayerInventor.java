package subaraki.rpginventory.gui;
//package subaraki.rpginventory.gui;
//
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.EnumFacing;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.capabilities.ICapabilityProvider;
//import net.minecraftforge.common.util.INBTSerializable;
//import net.minecraftforge.items.ItemStackHandler;
//import subaraki.rpginventory.enums.JewelTypes;
//import subaraki.rpginventory.item.RpgInventoryItem;
//import subaraki.rpginventory.mod.RpgInventory;
//
//public class RpgPlayerInventory extends ItemStackHandler implements ICapabilityProvider, INBTSerializable<NBTTagCompound>{
//
//	/////////////////////////////////////////////////////////////////////////////////////
//	//////////////////////////////////ItemStackHandler///////////////////////////////////
//	/////////////////////////////////////////////////////////////////////////////////////
//
//	private ItemStack[] inventory = new ItemStack[5];
//	@Override
//	public int getSlots() {
//		return inventory.length;
//	}
//
//	@Override
//	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
//		ItemStack newStack = stack.copy();
//
//		if(isItemValidForSlot(slot, newStack)) //these items cannot be stacked
//			if(inventory[slot] == null)
//				newStack.stackSize--;
//			else{
//				ItemStack stackFromSlot = inventory[slot];
//				//switch held item and item in slot
//				setStackInSlot(slot, newStack);
//				return stackFromSlot;
//			}
//
//		if(newStack.stackSize <= 0)
//			newStack = null;
//
//		return newStack;
//	}
//
//	@Override
//	public ItemStack extractItem(int slot, int amount, boolean simulate) {
//		//hand is empty
//		//give hand stack from slot
//		//Set slot empty
//		ItemStack stack = inventory[slot];
//		inventory[slot] = null;
//		return stack;
//	}
//
//	@Override
//	public ItemStack getStackInSlot(int slot) {
//		return inventory[slot];
//	}
//
//	/////////////////////////////////////////////////////////////////////////////////////
//	//////////////////////////////////Interface CapabilityProvider//////////////////////////////
//	/////////////////////////////////////////////////////////////////////////////////////
//
//	@Override
//	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
//		return true;
//	}
//
//	@Override
//	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
//		return null;
//	}
//
//	/////////////////////////////////////////////////////////////////////////////////////
//	//////////////////////////////////Interface INBTSerializable//////////////////////////////
//	/////////////////////////////////////////////////////////////////////////////////////
//
//	@Override
//	public void deserializeNBT(NBTTagCompound nbt) {
//		super.deserializeNBT(nbt);
//	}
//
//	@Override
//	public NBTTagCompound serializeNBT() {
//		return super.serializeNBT();
//	}
//
//	/////////////////////////////////////////////////////////////////////////////////////
//	///////////////////////////////////////////Extra/////////////////////////////////////
//	/////////////////////////////////////////////////////////////////////////////////////
//
//	public boolean isItemValidForSlot(int slotID, ItemStack itemstack) {
//		if ((itemstack.getItem() instanceof RpgInventoryItem)) {
//			RpgInventoryItem tmp = (RpgInventoryItem) itemstack.getItem();
//			switch (slotID) {
//			case 0:
//				if (tmp.armorType == JewelTypes.NECKLACE) {
//					return true;
//				}
//				return false;
//			case 1:
//				if (tmp.armorType == JewelTypes.CAPE) {
//					return true;
//				}
//				return false;
//			case 2:
//				if (tmp.armorType == JewelTypes.GLOVES) {
//					return true;
//				}
//				return false;
//			case 3:
//				if (tmp.armorType == JewelTypes.RING) {
//					return true;
//				}
//				return false;
//			case 4:
//				if (tmp.armorType == JewelTypes.RING) {
//					return true;
//				}
//				return false;
//			case 5:
//				if (tmp.armorType == JewelTypes.CRYSTAL) {
//					if (itemstack.getItemDamage() > 0) {
//						return true;
//					}
//				}
//				return false;
//			default:
//				// slotIndex);
//				return false;
//			}
//		}
//		return false;
//	}
//}
