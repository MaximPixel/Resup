package resup.inventory;

import resup.util.ItemStack;

public class Slot {
	
	public ItemStack stack = ItemStack.getEmpty();
	
	public boolean canPut(ItemStack stack) {
		return true;
	}
}
