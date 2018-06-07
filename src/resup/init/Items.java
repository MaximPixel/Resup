package resup.init;

import java.util.ArrayList;

import resup.item.Item;
import resup.item.ItemPickaxe;
import resup.item.ItemTile;

public class Items {
	
	public static final Item PICKAXE = new ItemPickaxe("pickaxe");
	
	public static final ItemTile AIR_TILE = new ItemTile(Tiles.AIR);
	public static final ItemTile BRICK_TILE = new ItemTile(Tiles.BRICK);
	
	private static ArrayList<Item> items = new ArrayList();
	
	public static void init() {
		addToReg(PICKAXE, AIR_TILE, BRICK_TILE);
	}
	
	public static void preInit() {
		
	}
	
	public static void addToReg(Item... items) {
		for (Item i : items) {
			addToReg(i);
		}
	}
	
	public static void addToReg(Item item) {
		item.id = items.size();
		items.add(item);
	}
	
	public static Item getItemByName(String name) {
		for (Item i : items) {
			if (name.equalsIgnoreCase(i.name)) {
				return i;
			}
		}
		return null;
	}
}
