package resup.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import resup.util.TilePos;
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
		
		ArrayList<ChunkPos> deleteChunks = new ArrayList();
		for (Entry<ChunkPos, Chunk> c : chunks.entrySet()) {
			if (c.getValue().remove) {
				deleteChunks.add(c.getKey());
			}
		}
		for (ChunkPos cp : deleteChunks) {
			chunks.remove(cp);
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
			return null;
		}
		return chunk.getTile(pos.toChunkTileX(), pos.toChunkTileX());
	}
	
	public boolean setTile(TilePos pos, Tile tile) {
		Chunk chunk = getChunk(pos);
		if (chunk == null) {
			return false;
		}
		return chunk.setTile(pos.toChunkTileX(), pos.toChunkTileY(), tile);
	}
	
	public Chunk getChunk(TilePos pos) {
		return getChunk(getChunkPos(pos));
	}
	
	public Chunk getChunk(ChunkPos cp) {
		return chunks.get(cp);
	}
	
	public ChunkPos getChunkPos(TilePos pos) {
		return new ChunkPos((int)Math.floor(pos.x / 16D), (int)Math.floor(pos.y / 16D));
	}
	
	public Chunk loadChunk(ChunkPos cp) {
		if (chunks.get(cp) == null) {
			Chunk chunk = new Chunk(this, cp);
			chunks.put(cp, chunk);
			chunk.remove = false;
			return chunk;
		}
		return null;
	}
	
	public void unloadChunk(Chunk chunk) {
		chunk.remove = true;
		onChunkUnload(chunk);
	}
	
	public void onChunkUnload(Chunk chunk) {
		
	}
	
	public void onPlayerChunkJump(EntityPlayer player, ChunkPos cp) {
		for (Chunk chunk : chunks.values()) {
			if (chunk.pos.distance(cp) >= 3) {
				unloadChunk(chunk);
			}
		}
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				ChunkPos cpp = new ChunkPos(cp.chunkX - 1 + a, cp.chunkY - 1 + b);
				loadChunk(cpp);
			}
		}
	}
}
