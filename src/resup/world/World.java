package resup.world;

import java.util.ArrayList;
import java.util.HashMap;

import mp.math.TilePos;
import resup.entity.Entity;
import resup.entity.EntityPlayer;
import resup.init.Tiles;
import resup.tile.Tile;
import resup.tileentity.TileEntity;
import resup.util.ChunkPos;

public class World {
	
	public HashMap<ChunkPos, Chunk> chunks = new HashMap();
	
	public ArrayList<Entity> entities = new ArrayList();
	public HashMap<TilePos, TileEntity> tileentities = new HashMap();
	
	public World() {
		
	}
	
	public void update() {
		
		for (Chunk chunk : ((HashMap<ChunkPos, Chunk>)chunks.clone()).values()) {
			if (chunk.remove) {
				chunks.remove(chunk);
			}
		}
		
		for (Entity ent : (ArrayList<Entity>)entities.clone()) {
			if (ent.remove) {
				entities.remove(ent);
			} else {
				ent.update();
			}
		}
		
		for (TileEntity te : ((HashMap<TilePos, TileEntity>)tileentities.clone()).values()) {
			if (te.remove) {
				tileentities.remove(te);
			} else {
				te.update();
			}
		}
	}
	
	public void addEntity(Entity ent, double x, double y) {
		ent.world = this;
		ent.xPos = x;
		ent.yPos = y;
		entities.add(ent);
	}
	
	public void addTileEntity(TileEntity te, TilePos pos) {
		te.pos = pos;
		te.world = this;
		tileentities.put(pos, te);
	}
	
	public void removeTileEntity(TilePos pos) {
		TileEntity te = tileentities.get(pos);
		if (te != null) {
			te.remove = true;
			return;
		}
		te = tileentities.get(pos);
		if (te != null) {
			te.remove = true;
		}
	}
	
	public TileEntity getTileEntity(TilePos pos) {
		TileEntity te = tileentities.get(pos);
		if (te != null) {
			return te;
		}
		return tileentities.get(pos);
	}
	
	public Tile getTile(TilePos pos) {
		Chunk chunk = getChunk(pos);
		if (chunk == null) {
			return Tiles.AIR;
		}
		return chunk.getTile(pos.x, pos.y);
	}
	
	public boolean setTile(TilePos pos, Tile tile) {
		Chunk chunk = getChunk(pos);
		if (chunk == null) {
			return false;
		}
		return chunk.setTile(pos.x, pos.y, tile);
	}
	
	public Tile getTile(double x, double y) {
		int tx = (int) (x / 32D);
		int ty = (int) (y / 32D);
		
		return getTile(new TilePos(tx, ty));
	}
	
	public Chunk getChunk(TilePos pos) {
		return getChunk(getChunkPos(pos));
	}
	
	public Chunk getChunk(ChunkPos cp) {
		return chunks.get(cp);
	}
	
	public ChunkPos getChunkPos(TilePos pos) {
		return new ChunkPos(pos.x / 16, pos.y / 16);
	}
	
	public Chunk loadChunk(ChunkPos cp) {
		if (chunks.get(cp) == null) {
			Chunk chunk = new Chunk(this, cp);
			chunks.put(cp, chunk);
			return chunk;
		}
		return null;
	}
	
	public Chunk unloadChunk(ChunkPos cp) {
		Chunk chunk = chunks.get(cp);
		if (chunk != null || chunks.containsKey(cp)) {
			chunk.remove = true;
			onChunkUnload(chunk);
		}
		return null;
	}
	
	public void onChunkUnload(Chunk chunk) {
		System.out.println(chunk.pos);
	}
	
	public void onPlayerChunkJump(EntityPlayer player, ChunkPos cp) {
		for (ChunkPos cpp : chunks.keySet()) {
			if (cpp.distance(cp) >= 3) {
				Chunk chunk = chunks.get(cpp);
				chunk.remove = true;
				onChunkUnload(chunk);
			}
		}
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				ChunkPos cpp = new ChunkPos(cp.chunkX - 1 + a, cp.chunkY - 1 + b);
				System.out.println("" + loadChunk(cp));
				//System.out.println(cpp);
			}
		}
	}
}
