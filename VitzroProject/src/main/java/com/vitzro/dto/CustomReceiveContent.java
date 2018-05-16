package com.vitzro.dto;

import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomReceiveContent implements IReceivedMessage<byte[]>{
	
	public static final int SIZE=20;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public void decode(byte[] i) {
		// TODO Auto-generated method stub
		
	}

}
