package resup.item;

import mp.math.TilePos;
import resup.util.ItemStack;
import resup.world.World;

public class Item {
	
	public int id;
	public String name;
	public int maxStackSize;
	
	public Item(String name) {
		this.name = name;
		this.maxStackSize = 10;
	}
	
	public boolean onUse(World world, TilePos pos, ItemStack stack) {
		return false;
	}
}
