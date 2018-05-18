package resup.util;

import resup.init.Tiles;
import resup.item.Item;
import resup.tile.Tile;

public class ItemStack {
	
	public static final ItemStack EMPTY = new ItemStack(Tiles.AIR, 1);
	
	public Item item;
	public int count;
	
	public ItemStack(Item item, int count) {
		
		if (count > 999) {
			count = 999;
		}
		
		if (count <= 0) {
			item = Tiles.AIR.getTileItem();
		}
		
		if (item == Tiles.AIR.getTileItem()) {
			count = 0;
		}
		
		this.item = item;
		this.count = count;
	}
	
	public ItemStack(Tile tile, int count) {
		this(tile.getTileItem(), count);
	}
}
