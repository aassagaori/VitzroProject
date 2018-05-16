package com.vitzro.dto;

import java.nio.ByteOrder;

import com.vitzro.dto.ProtocolHeader.ProtocolHeaderBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolTail implements IReceivedMessage<byte[]>, ISendMessage<ProtocolTail, byte[]> {

	public static final int SIZE=2;
	
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public byte[] encode(ProtocolTail i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decode(byte[] i) {
		// TODO Auto-generated method stub
		
	}

	

}
