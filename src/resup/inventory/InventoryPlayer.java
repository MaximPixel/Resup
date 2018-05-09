package resup.inventory;

public class InventoryPlayer extends Inventory {

	public InventoryPlayer() {
		super("playerInventory");
		for (int a = 0; a < 10; a++) {
			slots.add(new Slot());
		}
	}
}
