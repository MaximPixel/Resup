package resup.util;

import java.io.Serializable;

import mpengine.EngineMath;

public class ChunkPos implements Serializable {
	
	public int chunkX, chunkY;
	
	public ChunkPos(double x, double y) {
		this((int)Math.floor(x / 32D / 16D), (int)Math.floor(y / 32D / 16D));
	}
	
	public ChunkPos(int chunkX, int chunkY) {
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}
	
	public double distance(ChunkPos cp) {
		return EngineMath.distance(chunkX, chunkY, cp.chunkX, cp.chunkY);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chunkX;
		result = prime * result + chunkY;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunkPos other = (ChunkPos) obj;
		if (chunkX != other.chunkX)
			return false;
		if (chunkY != other.chunkY)
			return false;
		return true;
	}
	
	@Override
	public ChunkPos clone() {
		return new ChunkPos(chunkX, chunkY);
	}
	
	@Override
	public String toString() {
		return chunkX + " " + chunkY;
	}
}
