package resup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import mp.math.TilePos;
import mpengine.EngineInput;
import mpengine.IEngineInterface;
import mpengine.MPEngineObject;
import resup.entity.EntityPlayer;
import resup.init.Items;
import resup.init.Tiles;
import resup.inventory.InventoryPlayer;
import resup.tile.Tile;
import resup.tileentity.TileEntity;
import resup.util.ItemStack;
import resup.world.World;

public class Resup implements IEngineInterface {
	
	public static MPEngineObject mpe = new MPEngineObject();
	public static EngineInput input;
	
	public static World world;
	
	public static int currentTile = 1;
	
	public static InventoryPlayer playerInventory = new InventoryPlayer();
	
	public static void main(String... args) {
		
		Items.registerItems();
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
			
		}
		
		if (Settings.SWITCH_TILE.isKeyReal(input)) {
			currentTile++;
			if (currentTile >= Tiles.getTilesCount()) {
				currentTile = 1;
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
				TilePos p = new TilePos(a, b);
				
				Tile t = world.getTile(p);
				if (t != Tiles.AIR) {
					graphics.setColor(t.color);
					graphics.fillRect(a * 32, b * 32, 32, 32);
				}
				
				TileEntity te = world.getTileEntity(p);
				if (te != null) {
					graphics.setColor(Color.YELLOW);
					graphics.drawOval(a * 32, b * 32, 32, 32);
				}
			}
		}
		
		graphics.setColor(Color.DARK_GRAY);
		for (int a = 0; a < 10; a++) {
			graphics.fillRect(4 + a * 36, 4, 32, 32);
			ItemStack stack = playerInventory.slots.get(a).stack;
			if (stack.item != Items.getItemByName("air")) {
				
			}
		}
	}
}
