package resup.tile;

import java.awt.Color;

import resup.util.TilePos;
import resup.tileentity.TileEntity;
import resup.tileentity.TileEntityComputer;
import resup.world.World;

public class TileComputer extends Tile {
	
	public TileComputer() {
		super(Color.BLACK, "computer");
	}

	public TileEntity createTileEntity(World world, TilePos pos) {
		return new TileEntityComputer();
	}
}
