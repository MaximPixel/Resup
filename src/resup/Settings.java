package resup;

import java.awt.event.KeyEvent;

public class Settings {
	
	public static KeyBinding LEFT_MOVE = keyBinding("leftMove", KeyEvent.VK_A);
	public static KeyBinding UP_MOVE = keyBinding("upMove", KeyEvent.VK_W);
	public static KeyBinding RIGHT_MOVE = keyBinding("rightMove", KeyEvent.VK_D);
	public static KeyBinding DOWN_MOVE = keyBinding("downMove", KeyEvent.VK_S);
	
	public static KeyBinding CURRENT_SLOT_0 = keyBinding("currentSlot0", KeyEvent.VK_1);
	public static KeyBinding CURRENT_SLOT_1 = keyBinding("currentSlot1", KeyEvent.VK_2);
	public static KeyBinding CURRENT_SLOT_2 = keyBinding("currentSlot2", KeyEvent.VK_3);
	public static KeyBinding CURRENT_SLOT_3 = keyBinding("currentSlot3", KeyEvent.VK_4);
	public static KeyBinding CURRENT_SLOT_4 = keyBinding("currentSlot4", KeyEvent.VK_5);
	public static KeyBinding CURRENT_SLOT_5 = keyBinding("currentSlot5", KeyEvent.VK_6);
	public static KeyBinding CURRENT_SLOT_6 = keyBinding("currentSlot6", KeyEvent.VK_7);
	public static KeyBinding CURRENT_SLOT_7 = keyBinding("currentSlot7", KeyEvent.VK_8);
	public static KeyBinding CURRENT_SLOT_8 = keyBinding("currentSlot8", KeyEvent.VK_9);
	public static KeyBinding CURRENT_SLOT_9 = keyBinding("currentSlot9", KeyEvent.VK_0);
	
	public static KeyBinding[] CURRENT_SLOT_ARRAY = new KeyBinding[] {
			CURRENT_SLOT_0, CURRENT_SLOT_1,
			CURRENT_SLOT_2, CURRENT_SLOT_3,
			CURRENT_SLOT_4, CURRENT_SLOT_5,
			CURRENT_SLOT_6, CURRENT_SLOT_7,
			CURRENT_SLOT_8, CURRENT_SLOT_9
	};
	
	public static KeyBinding keyBinding(String name, int standartKey) {
		return new KeyBinding(name, standartKey);
	}
}
