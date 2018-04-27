package com.data.structs;

import java.nio.ByteBuffer;

public class Vitzro_Frame_Tail {
	private byte DLE;	// DLE/ETX :
	private byte ETX;		// [FLAME TERMINATOR] 메시지 끝부분 (1byte)
							// DLE = 10H , ETX = 03H 
	private short CRC_16;	// IEEE's standard cyclic redundancy check code (2byte)
							// 4 byte
	
	
	public Vitzro_Frame_Tail(){
		
	}
	
	public Vitzro_Frame_Tail(ByteBuffer byteBuffer) {
		this.DLE = byteBuffer.get();
		this.ETX = byteBuffer.get();
		this.CRC_16 = byteBuffer.getShort();
	}

	public byte getDLE() {
		return DLE;
	}
	public void setDLE(byte dLE) {
		DLE = dLE;
	}
	public byte getETX() {
		return ETX;
	}

	public void setETX(byte eTX) {
		ETX = eTX;
	}

	public short getCRC_16() {
		return CRC_16;
	}

	public void setCRC_16(short cRC_16) {
		CRC_16 = cRC_16;
	}
}
