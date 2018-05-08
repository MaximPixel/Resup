package resup.init;

import java.awt.Color;
import java.util.ArrayList;

import resup.tile.Tile;
import resup.tile.TileComputer;

public class Tiles {
	
	public static final Tile AIR = new Tile(Color.BLUE, "air").addItem();
	public static final Tile BRICK = new Tile(Color.RED, "brick").addItem();
	public static final TileComputer COMPUTER = new TileComputer();
	
	private static ArrayList<Tile> tiles = new ArrayList();
	
	public static void registerTiles() {
		register(AIR, BRICK, COMPUTER);
	}
	
	public static void register(Tile... tiles) {
		for (Tile t : tiles) {
			register(t);
		}
	}
	
	public static void register(Tile tile) {
		tile.id = tiles.size();
		tiles.add(tile);
	}
	
	public static Tile getTileFromId(int id) {
		return tiles.get(id);
	}
	
	public static int getTilesCount() {
		return tiles.size();
	}
}
