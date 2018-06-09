package resup;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import mp.math.TilePos;
import mpengine.EngineCamera;
import mpengine.EngineFiles;
import mpengine.EngineInput;
import mpengine.EngineMath;
import mpengine.IEngineInterface;
import mpengine.MPEngineObject;
import resup.entity.Entity;
import resup.entity.EntityPlayer;
import resup.init.Items;
import resup.init.Tiles;
import resup.inventory.InventoryPlayer;
import resup.item.Item;
import resup.tile.Tile;
import resup.tileentity.TileEntity;
import resup.util.ItemStack;
import resup.world.Chunk;
import resup.world.World;

public class Resup implements IEngineInterface {
	
	public static MPEngineObject mpe = new MPEngineObject();
	public static EngineInput input;
	public static EngineCamera camera = new EngineCamera(mpe, 0F, 0F);
	
	public static HashMap<String, BufferedImage> images = new HashMap();
	
	public static World world;
	
	public static EntityPlayer player;
	public static int currentSlot = 0;
	
	public static ItemStack cursorSlot;
	
	public static void main(String... args) {
		
		images.put("pickaxe", EngineFiles.loadImage("res/resup/images/items/pickaxe.png"));
		images.put("brick", EngineFiles.loadImage("res/resup/images/items/brick.png"));
		
		Tiles.init();
		Items.init();
		
		init();
		
		mpe.start(new Resup());
		input = mpe.thread.getEngineInput();
		
		mpe.frame.getCanvas().requestFocus();
	}
	
	
	public static void init() {
		cursorSlot = ItemStack.getEmpty();
		
		world = new World();
		world.addEntity(player = new EntityPlayer(), 0D, 0D);
		
		player.inventory.slots.get(1).stack = new ItemStack(Items.PICKAXE, 1);
		player.inventory.slots.get(0).stack = new ItemStack(Items.BRICK_TILE, 2);
		player.inventory.slots.get(2).stack = new ItemStack(Items.BRICK_TILE, 9);
	}
	
	@Override
	public void updateLoop() {
		Point mp = input.getMousePos();
		
		for (int a = 0; a < EngineInput.BUTTONS_COUNT; a++) {
			if (input.isButtonDown(a)) {
				boolean f = clickGui(mp, a);
				
				if (a == 1 && !f) {
					ItemStack stack = player.inventory.slots.get(currentSlot).stack;
					
					if (stack != null && !stack.isEmpty()) {
						Point mpp = camera.screenToWorldPoint(new Point(mp.x, mp.y));
						TilePos pos = new TilePos(mpp.x / 32, mpp.y / 32);
						stack.item.onUse(player, world, pos, stack);
					}
				}
			}
		}
		
		if (input.isKeyReal(KeyEvent.VK_E)) {
			currentSlot++;
			if (currentSlot >= 10) {
				currentSlot = 0;
			}
		}
		
		for (int a = 0; a < 10; a++) {
			if (Settings.CURRENT_SLOT_ARRAY[a].isKeyDown(input)) {
				if (input.isKey(KeyEvent.VK_SHIFT)) {
					ItemStack s = player.inventory.slots.get(a).stack;
					player.inventory.slots.get(a).stack = player.inventory.slots.get(currentSlot).stack;
					player.inventory.slots.get(currentSlot).stack = s;
				} else if (input.isKey(KeyEvent.VK_CONTROL)) {
					if (!player.inventory.slots.get(currentSlot).stack.isEmpty() && (player.inventory.slots.get(a).stack.isEmpty() || player.inventory.slots.get(a).stack.item == player.inventory.slots.get(currentSlot).stack.item)) {
						player.inventory.slots.get(a).stack.item = player.inventory.slots.get(currentSlot).stack.item;
						
						if (player.inventory.slots.get(a).stack.count + 1 <= player.inventory.slots.get(a).stack.item.maxStackSize) {
							player.inventory.slots.get(a).stack.addCount(1);
							player.inventory.slots.get(currentSlot).stack.addCount(-1);
						}
					}
				} else {
					currentSlot = a;
				}
				break;
			}
		}
		
		player.controll();
		
		camera.cameraX = (float) player.xPos;
		camera.cameraY = (float) player.yPos;
		
		world.update();
	}

	@Override
	public void drawLoop(Canvas canvas, Graphics2D graphics) {
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		Point mp = input.getMousePos();
		
		graphics.setFont(new Font(null, 16, 20));
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, width, height);
		
		int tx = (int)Math.floor(camera.getCameraTranslateX());
		int ty = (int)Math.floor(camera.getCameraTranslateY());
		
		graphics.translate(tx, ty);
		
		for (Chunk ch : world.chunks.values()) {
			graphics.setColor(Color.BLACK);
			int ttx = ch.pos.chunkX * 32 * 16;
			int tty = ch.pos.chunkY * 32 * 16;
			graphics.translate(ttx, tty);
			graphics.drawLine(0, 0, 512, 0);
			graphics.drawLine(0, 0, 0, 512);
			graphics.drawLine(512, 0, 512, 512);
			graphics.drawLine(0, 512, 512, 512);
			graphics.translate(-ttx, -tty);
		}
		
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
		
		for (Entity ent : world.entities) {
			graphics.setColor(Color.CYAN);
			graphics.fillRect((int)ent.xPos, (int)ent.yPos, 32, 32);
		}
		
		graphics.translate(-tx, -ty);
		
		for (int a = 0; a < 10; a++) {
			graphics.setColor(Color.DARK_GRAY);
			if (currentSlot == a) {
				graphics.fillRect(4 + a * 36 - 2, 2, 36, 36);
			} else {
				graphics.fillRect(4 + a * 36, 4, 32, 32);
			}
			ItemStack stack = player.inventory.slots.get(a).stack;
			
			if (stack != null && stack.item != Items.AIR_TILE) {
				
				drawItem(graphics, 4 + a * 36, 4, stack);
			}
		}
		
		if (!cursorSlot.isEmpty()) {
			drawItem(graphics, mp.x, mp.y, cursorSlot);
		}
	}
	
	public static boolean clickGui(Point mp, int button) {
		for (int a = 0; a < 10; a++) {
			if (EngineMath.collide(mp.x, mp.y, 4 + a * 36, 4, 32, 32)) {
				
				ItemStack s = player.inventory.slots.get(a).stack;
				
				if (button == 1) {
					if (!cursorSlot.isEmpty() && !s.isEmpty() && cursorSlot.item == s.item) {
						
						if (s.count + cursorSlot.count > s.item.maxStackSize) {
							int c = s.count + cursorSlot.count;
							player.inventory.slots.get(a).stack.count = s.item.maxStackSize;
							cursorSlot.count = c - s.item.maxStackSize;
						} else {
							s.addCount(cursorSlot.count);
							
							cursorSlot.setEmpty();
						}
					} else {
						player.inventory.slots.get(a).stack = cursorSlot;
						cursorSlot = s;
					}
				}
				
				if (button == 3) {
					if (cursorSlot.isEmpty() && !s.isEmpty()) {
						int c1 = (int) Math.floor(s.count / 2D);
						int c2 = (int) Math.ceil(s.count / 2D);
						
						s.setCount(c1);
						
						cursorSlot.setCount(c2);
						cursorSlot.item = s.item;
					}
					if (!cursorSlot.isEmpty() && (s.isEmpty() || cursorSlot.item == s.item)) {
						s.item = cursorSlot.item;
						
						if (s.count + 1 <= s.item.maxStackSize) {
							s.addCount(1);
							cursorSlot.addCount(-1);
						}
					}
				}
				
				return true;
			}
		}
		return false;
	}
	
	public static void onAA(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	public static void offAA(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_DEFAULT);
	}
	
	public static void drawItem(Graphics2D graphics, int x, int y, ItemStack stack) {
		Item item = stack.item;
		
		BufferedImage img = images.get(item.name);
		
		if (img != null) {
			graphics.drawImage(img, x, y, 32, 32, null);
		}
		
		onAA(graphics);
		String count = "" + stack.count;
		if (stack.count >= 100) {
			count = "+";
		}
		int ww = graphics.getFontMetrics().stringWidth(count);
		drawText(graphics, x + 32 - ww, y + 32, count);
		offAA(graphics);
	}
	
	public static void drawText(Graphics2D graphics, int x, int y, String text) {
		FontRenderContext frc = graphics.getFontRenderContext();
        TextLayout textTl = new TextLayout(text, graphics.getFont(), frc);
        Shape outline = textTl.getOutline(null);
        
        graphics.setStroke(new BasicStroke(0.9999F));
        graphics.translate(x, y);
        graphics.setColor(Color.WHITE);
        graphics.fill(outline);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.translate(-x, -y);
	}
}
