package resup.item;

import mp.math.TilePos;
import resup.init.Tiles;
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
		if (world.getTile(pos) == Tiles.AIR) {
			if (world.setTile(pos, tile)) {
				stack.shrink();
				return true;
			}
		}
		return false;
	}
}
