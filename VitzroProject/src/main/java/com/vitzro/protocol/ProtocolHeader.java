package com.vitzro.protocol;

import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolHeader implements IReceivedMessage<byte[]>, ISendMessage<ProtocolHeader, byte[]> {

	public static final int SIZE=10;
	
	private int contentLength;
	private byte opCode;
	
	@Builder.Default
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public byte[] encode(ProtocolHeader i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decode(byte[] i) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
