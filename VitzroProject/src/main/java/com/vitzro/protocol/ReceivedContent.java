package com.vitzro.protocol;

import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceivedContent implements IReceivedMessage<Byte[]>{
	
	public static final int SIZE=20;
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public void decode(Byte[] i) {
		// TODO Auto-generated method stub
		
	}

}
