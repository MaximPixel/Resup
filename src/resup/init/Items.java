package resup.init;

import java.util.ArrayList;

import resup.item.Item;

public class Items {
	
	public static final Item PICKAXE = new Item("pickaxe");
	
	private static ArrayList<Item> items = new ArrayList();
	
	public static void registerItems() {
		register(PICKAXE);
	}
	
	public static void register(Item... items) {
		for (Item i : items) {
			register(i);
		}
	}
	
	public static void register(Item item) {
		item.id = items.size();
		items.add(item);
	}
}
