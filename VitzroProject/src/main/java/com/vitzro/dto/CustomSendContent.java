package com.vitzro.dto;

import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSendContent implements ISendMessage<CustomSendContent, byte[]>{
	
	public static final int SIZE=20;
	
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public byte[] encode(CustomSendContent i) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
