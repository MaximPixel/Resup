package resup.inventory;

import resup.util.ItemStack;

public class Slot {
	
	public ItemStack stack = ItemStack.EMPTY;
	
	public boolean canPut(ItemStack stack) {
		return true;
	}
}
