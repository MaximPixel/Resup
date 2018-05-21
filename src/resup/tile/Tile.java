package resup.tile;

import java.awt.Color;

import mp.math.TilePos;
import resup.init.Items;
import resup.item.Item;
import resup.item.ItemTile;
import resup.tileentity.TileEntity;
import resup.world.World;

public class Tile {
	
	public int id;
	public Color color;
	public String name;
	
	public Tile(Color color, String name) {
		this.color = color;
		this.name = name;
	}
	
	public Tile addItem() {
		Items.addToReg(new ItemTile(this));
		return this;
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
	
	public Item getTileItem() {
		return Items.PICKAXE;
	}
}
