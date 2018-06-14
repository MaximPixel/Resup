package resup.util;

import java.io.Serializable;

public class TilePos implements Serializable {
	
	public int x, y;
	
	public TilePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public TilePos() {
		this(0, 0);
	}
	
	public int toChunkTileX() {
		int x = this.x % 16;
		if (x < 0) {
			x += 16;
		}
		return x;
	}
	
	public int toChunkTileY() {
		int y = this.y % 16;
		if (y < 0) {
			y += 16;
		}
		return y;
	}
	
	public TilePos left(int v) {
		return rot(EnumRot.LEFT, v);
	}
	public TilePos left() {
		return rot(EnumRot.LEFT);
	}
	
	public TilePos up(int v) {
		return rot(EnumRot.UP, v);
	}
	public TilePos up() {
		return rot(EnumRot.UP);
	}
	
	public TilePos right(int v) {
		return rot(EnumRot.RIGHT, v);
	}
	public TilePos right() {
		return rot(EnumRot.RIGHT);
	}
	
	public TilePos down(int v) {
		return rot(EnumRot.DOWN, v);
	}
	public TilePos down() {
		return rot(EnumRot.DOWN);
	}
	
	public TilePos rot(EnumRot rot) {
		return rot(rot, 1);
	}
	
	public TilePos rot(EnumRot rot, int v) {
		return add(rot.x * v, rot.y * v);
	}
	
	public TilePos add(int x, int y) {
		return new TilePos(this.x + x, this.y + y);
	}
	
	public TilePos negative() {
		return new TilePos(-x, -y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		TilePos other = (TilePos) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}
	
	@Override
	public TilePos clone() {
		return new TilePos(x, y);
	}
}
