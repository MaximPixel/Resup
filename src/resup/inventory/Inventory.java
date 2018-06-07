package resup.inventory;

import java.util.ArrayList;

import resup.util.ItemStack;

public abstract class Inventory {
	
	public ArrayList<Slot> slots = new ArrayList();
	
	public Inventory(String name) {
		
	}
	
	public ItemStack addToInventory(ItemStack stack) {
		if (!stack.isEmpty()) {
			int count = stack.count;
			int si = 0;
			while (count > 0 && si < slots.size()) {
				Slot slot = slots.get(si);
				if (slot.canPut(stack) && slot.stack.item == stack.item) {
					count = slot.stack.addCount(count);
				}
				si++;
			}
			
			si = 0;
			while (count > 0 && si < slots.size()) {
				Slot slot = slots.get(si);
				if (slot.canPut(stack) && slot.stack.isEmpty()) {
					slot.stack.item = stack.item;
					count = slot.stack.addCount(count);
				}
				si++;
			}
			
			if (count > 0) {
				return new ItemStack(stack.item, count);
			}
		}
		return ItemStack.getEmpty();
	}
}
