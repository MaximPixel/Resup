package resup.item;

import resup.tile.Tile;

public class ItemTile extends Item {

	public Tile tile;
	
	public ItemTile(Tile tile) {
		super(tile.name);
		this.tile = tile;
	}
}
