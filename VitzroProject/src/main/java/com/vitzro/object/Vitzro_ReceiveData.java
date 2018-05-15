package com.vitzro.object;

import java.util.Arrays;


public class Vitzro_ReceiveData {

	private Vitzro_Frame_Header header;
	private byte[] data;
	private Vitzro_Frame_Tail tail;
	
	public Vitzro_ReceiveData(){
		
	}
	
	public Vitzro_ReceiveData(Vitzro_Frame_Header header, byte[] byteData, Vitzro_Frame_Tail tail ) {
		this.header = header;
		this.data = byteData;
		this.tail = tail;
	}
	
	public void setReceiveData(Vitzro_Frame_Header header, byte[] byteData, Vitzro_Frame_Tail tail ) {
		this.header = header;
		this.data = byteData;
		this.tail = tail;
	}

	public Vitzro_Frame_Header getHeader() {
		return header;
	}

	public void setHeader(Vitzro_Frame_Header header) {
		this.header = header;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Vitzro_Frame_Tail getTail() {
		return tail;
	}

	public void setTail(Vitzro_Frame_Tail tail) {
		this.tail = tail;
	}

	@Override
	public String toString() {
		return "ReceiveData [header=" + header + ", data=" + Arrays.toString(data) + ", tail=" + tail + "]";
	}
	
	
	
	
	
}
