package resup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import mp.math.TilePos;
import mpengine.EngineFiles;
import mpengine.EngineInput;
import mpengine.IEngineInterface;
import mpengine.MPEngineObject;
import resup.entity.EntityPlayer;
import resup.init.Items;
import resup.init.Tiles;
import resup.inventory.InventoryPlayer;
import resup.item.Item;
import resup.tile.Tile;
import resup.tileentity.TileEntity;
import resup.util.ItemStack;
import resup.world.World;

public class Resup implements IEngineInterface {
	
	public static MPEngineObject mpe = new MPEngineObject();
	public static EngineInput input;
	
	public static HashMap<String, BufferedImage> images = new HashMap();
	
	public static World world;
	
	public static int currentTile = 1;
	
	public static InventoryPlayer playerInventory = new InventoryPlayer();
	public static int currentSlot = 0;
	
	public static void main(String... args) {
		
		playerInventory.slots.get(0).stack = new ItemStack(Tiles.BRICK, 2);
		playerInventory.slots.get(1).stack = new ItemStack(Items.PICKAXE, 1);
		
		images.put("pickaxe", EngineFiles.loadImage("res/resup/images/pickaxe.png"));
		images.put("brick", EngineFiles.loadImage("res/resup/images/brick.png"));
		
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
		
		if (input.isKeyReal(KeyEvent.VK_E)) {
			currentSlot++;
			if (currentSlot >= 10) {
				currentSlot = 0;
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
		
		for (int a = 0; a < 10; a++) {
			graphics.setColor(Color.DARK_GRAY);
			if (currentSlot == a) {
				graphics.fillRect(4 + a * 36 - 2, 2, 36, 36);
			} else {
				graphics.fillRect(4 + a * 36, 4, 32, 32);
			}
			ItemStack stack = playerInventory.slots.get(a).stack;
			
			drawItem(graphics, 4 + a * 36, 4, stack);
			
			if (stack.item != Items.getItemByName("air") && stack.count > 1) {
				graphics.setColor(Color.RED);
				graphics.drawString("" + stack.count, 4 + a * 36, 16);
			}
		}
	}
	
	public static void drawItem(Graphics2D graphics, int x, int y, ItemStack stack) {
		Item item = stack.item;
		
		BufferedImage img = images.get(item.name);
		
		if (img != null) {
			graphics.drawImage(img, x, y, 32, 32, null);
		}
	}
}
