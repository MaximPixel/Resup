package resup;

import java.awt.event.KeyEvent;

public class Settings {
	
	public static KeyBinding LEFT_MOVE = keyBinding("leftMove", KeyEvent.VK_A);
	public static KeyBinding UP_MOVE = keyBinding("upMove", KeyEvent.VK_W);
	public static KeyBinding RIGHT_MOVE = keyBinding("rightMove", KeyEvent.VK_D);
	public static KeyBinding DOWN_MOVE = keyBinding("downMove", KeyEvent.VK_S);
	
	public static KeyBinding keyBinding(String name, int standartKey) {
		return new KeyBinding(name, standartKey);
	}
}
