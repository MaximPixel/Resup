package resup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import mp.math.TilePos;
import mpengine.EngineInput;
import mpengine.IEngineInterface;
import mpengine.MPEngineObject;
import resup.entity.EntityPlayer;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.world.World;

public class Resup implements IEngineInterface {
	
	public static MPEngineObject mpe = new MPEngineObject();
	public static EngineInput input;
	
	public static World world;
	
	public static int currentTile = 0;
	
	public static void main(String... args) {
		
		Tiles.registerTiles();
		
		world = new World();
		world.addEntity(new EntityPlayer(), 0D, 0D);
		
		//System.out.println(Tiles.getTileFromId(1));
		
		mpe.start(new Resup());
		input = mpe.thread.getEngineInput();
	}

	@Override
	public void updateLoop() {
		Point mp = input.getMousePos();
		if (input.isButton(1)) {
			int tx = mp.x / 32;
			int ty = mp.y / 32;
			
			if (tx >= 0 && ty >= 0 && tx < 16 && ty < 16) {
				world.setTile(new TilePos(tx, ty), Tiles.getTileFromId(currentTile));
			}
		}
		
		if (input.isKeyReal(KeyEvent.VK_EQUALS)) {
			currentTile++;
			if (currentTile >= Tiles.getTilesCount()) {
				currentTile = 0;
			}
		}
	}

	@Override
	public void drawLoop(Canvas canvas, Graphics2D graphics) {
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, width, height);
		
		for (int a = 0; a < 16; a++) {
			for (int b = 0; b < 16; b++) {
				Tile t = world.getTile(new TilePos(a, b));
				if (t != Tiles.AIR) {
					graphics.setColor(t.color);
					graphics.fillRect(a * 32, b * 32, 32, 32);
				}
			}
		}
		
		Tile current = Tiles.getTileFromId(currentTile);
		if (current != null) {
			graphics.setColor(current.color);
			graphics.fillRect(0, 0, 16, 16);
		}
	}
}
