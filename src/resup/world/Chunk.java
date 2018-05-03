package resup.world;

import mp.math.TilePos;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.util.DataInput;
import resup.util.DataOutput;

public class Chunk {
	
	public World world;
	public int chunkX, chunkY;
	public int[] tiles = new int[16 * 16];
	
	public Chunk(World world, int chunkX, int chunkY) {
		this.world = world;
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}
	
	public Tile getTile(int x, int y) {
		int id = getTileId(x, y);
		if (id == -1) {
			return null;
		}
		return Tiles.getTileFromId(id);
	}
	
	private int getTileId(int x, int y) {
		if (x < 0 || y < 0 || x >= 16 || y >= 16) {
			return -1;
		}
		return tiles[x * 16 + y];
	}
	
	public boolean setTile(int x, int y, Tile tile) {
		int id = tile.id;
		if (id == -1) {
			return false;
		}
		tile.onPlace(world, new TilePos(chunkX * 16 + x, chunkY * 16 + y));
		return setTileId(x, y, id);
	}
	
	private boolean setTileId(int x, int y, int id) {
		if (x < 0 || y < 0 || x >= 16 || y >= 16) {
			return false;
		}
		tiles[x * 16 + y] = id;
		return true;
	}
	
	public DataOutput saveTiles() {
		DataOutput out = new DataOutput();
		for (int a = 0; a < 16; a++) {
			for (int b = 0; b < 16; b++) {
				out.writeInt(tiles[a * 16 + b]);
			}
		}
		return out;
	}
	
	public void loadTiles(DataInput input) {
		for (int a = 0; a < 16; a++) {
			for (int b = 0; b < 16; b++) {
				tiles[a * 16 + b] = input.readInt();
			}
		}
	}
}
