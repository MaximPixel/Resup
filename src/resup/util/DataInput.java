package resup.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataInput {
	
	public ByteArrayInputStream bis;
	public DataInputStream dis;
	
	public DataInput(byte[] data) {
		bis = new ByteArrayInputStream(data);
		dis = new DataInputStream(bis);
	}
	
	public byte readByte() {
		try {
			return dis.readByte();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Byte.MIN_VALUE;
	}
	
	public byte[] readBytes(int l) {
		byte[] v = new byte[l];
		for (int a = 0; a < l; a++) {
			v[a] = readByte();
		}
		return v;
	}
	
	public boolean readBool() {
		try {
			return dis.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int readInt() {
		try {
			return dis.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public short readShort() {
		try {
			return dis.readShort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long readLong() {
		try {
			return dis.readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public float readFloat() {
		try {
			return dis.readFloat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double readDouble() {
		try {
			return dis.readDouble();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String readString() {
		try {
			int a = dis.readInt();
			return new String(readBytes(a), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
