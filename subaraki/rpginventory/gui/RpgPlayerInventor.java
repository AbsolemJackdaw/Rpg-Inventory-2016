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
