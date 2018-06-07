package resup.entity;

import mpengine.EngineInput;
import resup.Resup;
import resup.Settings;
import resup.inventory.InventoryPlayer;

public class EntityPlayer extends Entity {
	
	public InventoryPlayer inventory;
	
	public EntityPlayer() {
		inventory = new InventoryPlayer();
	}
	
	@Override
	public void controll() {
		EngineInput input = Resup.input;
		
		int mx = 0;
		int my = 0;
		
		if (Settings.LEFT_MOVE.isKey(input)) {
			mx--;
		}
		if (Settings.UP_MOVE.isKey(input)) {
			my--;
		}
		if (Settings.RIGHT_MOVE.isKey(input)) {
			mx++;
		}
		if (Settings.DOWN_MOVE.isKey(input)) {
			my++;
		}
		if (mx != 0) {
			xPos += mx * 2.5D;
		}
		if (my != 0) {
			yPos += my * 2.5D;
		}
	}
}
