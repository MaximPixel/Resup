package resup.item;

import mp.math.TilePos;
import resup.util.ItemStack;
import resup.world.World;

public class Item {
	
	public int id;
	public String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public boolean onUse(World world, TilePos pos, ItemStack stack) {
		return false;
	}
}
