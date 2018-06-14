package resup.item;

import resup.util.TilePos;
import resup.entity.EntityPlayer;
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
	
	@Override
	public boolean onUse(EntityPlayer player, World world, TilePos pos, ItemStack stack) {
		if (world.getTile(pos) == Tiles.AIR) {
			if (world.setTile(pos, tile)) {
				stack.shrink();
				System.out.println(pos);
				return true;
			}
		}
		return false;
	}
}
