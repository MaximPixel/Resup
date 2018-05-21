package resup.util;

import resup.init.Tiles;
import resup.item.Item;
import resup.tile.Tile;

public class ItemStack {
	
	public static final ItemStack NULL = new ItemStack(Tiles.AIR, 0);
	
	public Item item;
	public int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public ItemStack(Tile tile, int count) {
		this(tile.getTileItem(), count);
	}
	
	public boolean isEmpty() {
		return item == NULL.item;
	}
	
	public void setEmpty() {
		item = NULL.item;
		count = 0;
	}
	
	public void shrink() {
		count--;
		if (count < 0) {
			count = 0;
		}
		if (count == 0) {
			item = NULL.item;
		}
	}
}
