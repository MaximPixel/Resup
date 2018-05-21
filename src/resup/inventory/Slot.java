package resup.inventory;

import resup.util.ItemStack;

public class Slot {
	
	public ItemStack stack = null;
	
	public boolean canPut(ItemStack stack) {
		return true;
	}
}
