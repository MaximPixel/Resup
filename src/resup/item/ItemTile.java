package resup.item;

import mp.math.TilePos;
import resup.tile.Tile;
import resup.util.ItemStack;
import resup.world.World;

public class ItemTile extends Item {

	public Tile tile;
	
	public ItemTile(Tile tile) {
		super(tile.name);
		this.tile = tile;
	}
	
	public boolean onUse(World world, TilePos pos, ItemStack stack) {
		return world.setTile(pos, tile);
	}
}
