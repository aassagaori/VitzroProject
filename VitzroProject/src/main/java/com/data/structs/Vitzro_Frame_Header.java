package com.data.structs;

import java.nio.ByteBuffer;




/**
 * @Author : Kim Ki Hwan
 * @Date   : 2018. 3. 26.
 * Vitzro inner protocol Common Header
 */
public class Vitzro_Frame_Header {
	private  byte DLE;	// DLE/STX :
	private  byte STX;		// [FLAME HEADER] 메시지의 시작 부분 (1byte)
							// DLE = 10H , STX = 02H
	private short ADDR;		// Controller Station Number (2byte)
	private byte OPCODE;	// Message Type (1byte)
							// = 5 byte
	
	public static int HEADER_FRAME_SIZE = 5;
	
	public static byte HEADER_FRAME_DLE = 0x10;
	public static byte HEADER_FRAME_STX = 0x02;
		
	
	public Vitzro_Frame_Header(){

		
	}
/*	
	public Frame_Header(ByteBuffer byteBuffer) {
		this.DLE = byteBuffer.get();
		this.STX = byteBuffer.get();
		this.ADDR = byteBuffer.getShort();
		this.OPCODE = byteBuffer.get();
	}

*/
	public Vitzro_Frame_Header(byte firstByteData, byte secondByteData) {
		
		
		this.DLE = firstByteData;
		this.STX = secondByteData;
	}

	public Vitzro_Frame_Header(ByteBuffer byteBuffer) {
		// TODO Auto-generated constructor stub
	}
	
	
	public byte getDLE() {
		return DLE;
	}

	public void setDLE(byte dLE) {
		DLE = dLE;
	}

	public void setSTX(byte sTX) {
		STX = sTX;
	}

	public byte getSTX() {
		return STX;
	}

	public short getADDR() {
		return ADDR;
	}

	public void setADDR(short aDDR) {
		ADDR = aDDR;
	}

	public byte getOPCODE() {
		return OPCODE;
	}

	public void setOPCODE(byte oPCODE) {
		OPCODE = oPCODE;
	}
	
	
	

	
	
	
}
