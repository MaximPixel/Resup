package resup.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataOutput {
	
	public ByteArrayOutputStream bos = new ByteArrayOutputStream();
	public DataOutputStream dos = new DataOutputStream(bos);
	
	public static void main(String... args) {
		//DataOutput out = new DataOutput();
		
		//DataInput in = new DataInput(out.getBytes());
	}
	
	public void writeByte(byte v) {
		try {
			dos.writeByte(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeBytes(byte[] v) {
		for (byte b : v) {
			writeByte(b);
		}
	}
	
	public void writeBool(boolean v) {
		try {
			dos.writeBoolean(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeInt(int v) {
		try {
			dos.writeInt(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeShort(short v) {
		try {
			dos.writeShort(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeLong(long v) {
		try {
			dos.writeLong(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFloat(float v) {
		try {
			dos.writeFloat(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeDouble(double v) {
		try {
			dos.writeDouble(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeString(String v) {
		byte[] b = v.getBytes(StandardCharsets.UTF_8);
		writeInt(b.length);
		writeBytes(b);
	}
	
	public byte[] getBytes() {
		return bos.toByteArray();
	}
}
