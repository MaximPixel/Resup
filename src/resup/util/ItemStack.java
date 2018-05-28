package resup.util;

import resup.init.Items;
import resup.init.Tiles;
import resup.item.Item;
import resup.tile.Tile;

public class ItemStack {
	
	public Item item;
	public int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public ItemStack(Tile tile, int count) {
		this(tile.getTileItem(), count);
	}
	
	public static ItemStack getEmpty() {
		return new ItemStack(Items.AIR_TILE, 0);
	}
	
	public void addCount(int count) {
		setCount(this.count + count);
	}
	
	public void setCount(int count) {
		this.count = count;
		if (count <= 0) {
			count = 0;
			item = getEmpty().item;
		}
	}
	
	public boolean isEmpty() {
		return item == getEmpty().item;
	}
	
	public void setEmpty() {
		item = getEmpty().item;
		count = 0;
	}
	
	public void shrink() {
		count--;
		if (count < 0) {
			count = 0;
		}
		if (count == 0) {
			item = getEmpty().item;
		}
	}
	
	public String toString() {
		return item.name + " X " + count;
	}
}
