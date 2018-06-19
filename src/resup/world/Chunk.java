package resup.world;

import resup.util.TilePos;

import java.util.Random;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.util.ChunkPos;
import resup.util.DataInput;
import resup.util.DataOutput;
import resup.util.PerlinNoise;

public class Chunk {
	
	public World world;
	public ChunkPos pos;
	public int[] tiles = new int[16 * 16];
	public boolean remove = false;
	
	public Chunk(World world, ChunkPos pos) {
		this.world = world;
		this.pos = pos;
		
		PerlinNoise noise = new PerlinNoise(world.seed);
		for (int a = 0; a < 16; a++) {
			for (int b = 0; b < 16; b++) {
				int x = pos.chunkX * 16 + a;
				int y = pos.chunkY * 16 + b;
				float c = noise.getNoise(x / 100F, y / 100F, 8, 0.5F);
				if (c > 0) {
					setTile(a, b, Tiles.BRICK);
				}
			}
		}
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
		tile.onPlace(world, new TilePos(pos.chunkX * 16 + x, pos.chunkY * 16 + y));
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
	
	@Override
	public String toString() {
		return pos + " " + world;
	}
}
