package resup.tile;

import java.awt.Color;

import mp.math.TilePos;
import resup.tileentity.TileEntity;
import resup.world.World;

public class Tile {
	
	public int id;
	public Color color;
	
	public Tile(Color color) {
		this.color = color;
	}
	
	public void onPlace(World world, TilePos pos) {
		TileEntity te = createTileEntity(world, pos);
		if (te != null) {
			world.addTileEntity(te, pos);
		}
	}
	
	public void onBreak(World world, TilePos pos) {
		world.removeTileEntity(pos);
	}
	
	public TileEntity createTileEntity(World world, TilePos pos) {
		return null;
	}
}
