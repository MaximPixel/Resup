package resup.util;

import resup.init.Tiles;
import resup.item.Item;
import resup.tile.Tile;

public class ItemStack {
	
	public static final ItemStack EMPTY = new ItemStack(Tiles.AIR, 1);
	
	public Item item;
	public int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public ItemStack(Tile tile, int count) {
		this(tile.getTileItem(), count);
	}
}
