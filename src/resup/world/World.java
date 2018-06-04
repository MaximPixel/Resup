package resup.world;

import java.util.ArrayList;
import java.util.HashMap;

import mp.math.TilePos;
import resup.entity.Entity;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.tileentity.TileEntity;

public class World {
	
	public Chunk chunk = new Chunk(this, 0, 0);
	
	public ArrayList<Entity> entities = new ArrayList();
	public ArrayList<Entity> newentities = new ArrayList();
	public HashMap<TilePos, TileEntity> newtileentities = new HashMap();
	public HashMap<TilePos, TileEntity> tileentities = new HashMap();
	
	public World() {
		chunk.setTile(10, 10, Tiles.BRICK);
	}
	
	public void update() {
		
		ArrayList<Entity> deleteEnt = new ArrayList();
		for (Entity ent : entities) {
			if (ent.remove) {
				deleteEnt.add(ent);
			}
		}
		entities.removeAll(deleteEnt);
		
		ArrayList<TileEntity> deleteTE = new ArrayList();
		for (TileEntity te : tileentities.values()) {
			if (te.remove) {
				deleteTE.add(te);
			}
		}
		tileentities.values().removeAll(deleteTE);
		
		entities.addAll(newentities);
		newentities.clear();
		tileentities.putAll(newtileentities);
		newtileentities.clear();
	}
	
	public void addEntity(Entity ent, double x, double y) {
		ent.world = this;
		ent.xPos = x;
		ent.yPos = y;
		newentities.add(ent);
	}
	
	public void addTileEntity(TileEntity te, TilePos pos) {
		te.pos = pos;
		te.world = this;
		newtileentities.put(pos, te);
	}
	
	public void removeTileEntity(TilePos pos) {
		TileEntity te = tileentities.get(pos);
		if (te != null) {
			te.remove = true;
			return;
		}
		te = newtileentities.get(pos);
		if (te != null) {
			te.remove = true;
		}
	}
	
	public TileEntity getTileEntity(TilePos pos) {
		TileEntity te = tileentities.get(pos);
		if (te != null) {
			return te;
		}
		return newtileentities.get(pos);
	}
	
	public Tile getTile(TilePos pos) {
		return chunk.getTile(pos.x, pos.y);
	}
	
	public boolean setTile(TilePos pos, Tile tile) {
		return chunk.setTile(pos.x, pos.y, tile);
	}
	
	public Tile getTile(double x, double y) {
		int tx = (int) (x / 32D);
		int ty = (int) (y / 32D);
		
		return getTile(new TilePos(tx, ty));
	}
}
