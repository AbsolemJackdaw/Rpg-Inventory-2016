package subaraki.rpginventory.capability.playerinventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import subaraki.rpginventory.enums.SlotIndex;

public class RpgInventoryData {

	private RpgStackHandler inventory;
	
	private int healingRateTracker = 0;
	private int averageHealingRate = 0;
	private double prevHealth = 0;
	
	private int healCounter = 0;
	
	private EntityPlayer player;

	public RpgInventoryData(){
		inventory = new RpgStackHandler();
	}

	public EntityPlayer getPlayer() { 
		return player; 
	}

	public void setPlayer(EntityPlayer newPlayer){
		this.player = newPlayer;
	}

	public static RpgInventoryData get(EntityPlayer player)
	{
		return player.getCapability(RpgInventoryCapability.CAPABILITY, null);
	}
	/*
	 * Internal method used by IStorage in the capability
	 */
	public RpgStackHandler getInventory(){
		return inventory;
	}

	public NBTBase writeData(){
		//hook into the tagcompound of the ItemStackHandler
		NBTTagCompound tag = getInventory().serializeNBT();
		//add our own tags
		//none here
		//save mix of itemstacks and personal tags
		return tag;
	}
	
	public void readData(NBTBase nbt){
		getInventory().deserializeNBT((NBTTagCompound)nbt);

	}
	
	public ItemStack getCloak(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_CLOAK.ordinal());
	}
	
	public ItemStack getNecklace(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_NECKLACE.ordinal());
	}
	
	public ItemStack getCrystal(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_CRYSTAL.ordinal());
	}
	
	public ItemStack getRing_1(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_RING1.ordinal());
	}
	
	public ItemStack getRing_2(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_RING2.ordinal());
	}
	
	public ItemStack getGloves(){
		return getInventory().getStackInSlot(SlotIndex.SLOT_GLOVES.ordinal());
	}
	
	public void setJewel(SlotIndex type, ItemStack stack){
		getInventory().setStackInSlot(type.ordinal(), stack);
	}
	
	public int getHealCounter() {
		return healCounter;
	}
	
	public void setHealCounter(int healCounter) {
		this.healCounter = healCounter;
	}
	
	public void tickHealCounter(){
		this.healCounter--;
	}
	
	public double getPrevHealth() {
		return prevHealth;
	}
	
	public void setPrevHealth(double prevHealth) {
		this.prevHealth = prevHealth;
	}
	
	public void resetTracker(){
		averageHealingRate = healingRateTracker;
		healingRateTracker = 0;
	}
	public void countTracker()
	{
		this.healingRateTracker++;
	}
	
	public int getAverageHealingRate() {
		return averageHealingRate;
	}	
}
