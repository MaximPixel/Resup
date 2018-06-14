package resup.item;

import resup.util.TilePos;
import resup.entity.EntityPlayer;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.util.ItemStack;
import resup.world.World;

public class ItemPickaxe extends Item {

	public ItemPickaxe(String name) {
		super(name);
	}
	
	@Override
	public boolean onUse(EntityPlayer player, World world, TilePos pos, ItemStack stack) {
		if (world.getTile(pos) != Tiles.AIR) {
			Tile tile = world.getTile(pos);
			if (world.setTile(pos, Tiles.AIR)) {
				player.inventory.addToInventory(new ItemStack(tile.getTileItem(), 1));
				return true;
			}
		}
		return false;
	}
}
