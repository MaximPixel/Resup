package resup.util;

import java.awt.geom.Point2D;
import java.util.Random;

public class PerlinNoise {
	
	private byte[] permutationTable;
	
	public PerlinNoise(long seed) {
		Random random = new Random(seed);
		permutationTable = new byte[1024];
		random.nextBytes(permutationTable);
	}
	
	public float getNoise(float fx, float fy, int octaves, float persistence) {
		float amplitude = 1;
		float max = 0; 
		float result = 0; 
		
		while (octaves-- > 0)
		{
			max += amplitude;
			result += getNoise(fx, fy) * amplitude;
			amplitude *= persistence;
			fx *= 2;
			fy *= 2;
		}
		
		return result/max;
	}
	
	public float getNoise(float x, float y) {
		int left = (int)x;
		int top = (int)y;
		
		float localX = x - left;
		float localY = y - top;
		
		Point2D.Float topLeftGradient     = getPseudoRandomGradientVector(left,   top  );
		Point2D.Float topRightGradient    = getPseudoRandomGradientVector(left+1, top  );
		Point2D.Float bottomLeftGradient  = getPseudoRandomGradientVector(left,   top+1);
		Point2D.Float bottomRightGradient = getPseudoRandomGradientVector(left+1, top+1);
		
		Point2D.Float distanceToTopLeft     = new Point2D.Float(localX,   localY);
		Point2D.Float distanceToTopRight    = new Point2D.Float(localX-1, localY);
		Point2D.Float distanceToBottomLeft  = new Point2D.Float(localX,   localY-1);
		Point2D.Float distanceToBottomRight = new Point2D.Float(localX-1, localY-1);
		
		float tx1 = dot(distanceToTopLeft,     topLeftGradient);
		float tx2 = dot(distanceToTopRight,    topRightGradient);
		float bx1 = dot(distanceToBottomLeft,  bottomLeftGradient);
		float bx2 = dot(distanceToBottomRight, bottomRightGradient);
		
		// интерполяция:
		float tx = lerp(tx1, tx2, qunticCurve(localX));
		float bx = lerp(bx1, bx2, qunticCurve(localX));
		float tb = lerp(tx, bx, qunticCurve(localY));
		
		return tb;
	}
	
	private float lerp(float a, float b, float t) {
		return a + (b - a) * t;
		}
	
	private float dot(Point2D.Float a, Point2D.Float b) {
		return a.x * b.x + a.y * b.y;
	}
	
	private float qunticCurve(float t) {
		return t * t * t * (t * (t * 6 - 15) + 10);
	}
	
	private Point2D.Float getPseudoRandomGradientVector(int x, int y) {
		int v = (int)(((x * 1836311903L) ^ (y * 2971215073L) + 4807526976L) & 1023L);
		v = permutationTable[v] & 3;
		
		switch (v) {
		case 0:  return new Point2D.Float(1, 0);
		case 1:  return new Point2D.Float(-1, 0);
		case 2:  return new Point2D.Float(0, 1);
		default: return new Point2D.Float(0,-1);
		}
	}
}