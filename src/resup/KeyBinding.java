package resup;

import mpengine.EngineInput;

public class KeyBinding {
	
	public String name;
	public int currentKey, standartKey;
	
	public KeyBinding(String name, int standartKey) {
		this.name = name;
		this.standartKey = standartKey;
		this.currentKey = standartKey;
	}
	
	public int setStandartKey() {
		return currentKey = standartKey;
	}
	
	public boolean isKey(EngineInput input) {
		return input.isKey(currentKey);
	}
	
	public boolean isKeyReal(EngineInput input) {
		return input.isKeyReal(currentKey);
	}
}
