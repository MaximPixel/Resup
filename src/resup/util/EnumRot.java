package resup.util;

public enum EnumRot {
	LEFT	(0, new int[] {3, 0, 1, 2}, 270, -1, 0),
	UP		(1, new int[] {0, 0, 0, 0}, 180, 0, -1),
	RIGHT	(2, new int[] {1, 2, 3, 0}, 90, 1, 0),
	DOWN	(3, new int[] {2, 3, 0, 1}, 0, 0, 1);
	
	public int id, deg, x, y;
	public int[] relative;
	
	EnumRot(int id, int[] relative, int deg, int x, int y) {
		this.id = id;
		this.relative = relative;
		this.deg = deg;
		this.x = x;
		this.y = y;
	}
	
	public EnumRot getRelative(EnumRot rot) {
		return EnumRot.values()[EnumRot.values()[rot.id].relative[id]];
	}
	
	public double getRadians() {
		return Math.toRadians(deg);
	}
}
