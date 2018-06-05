package com.vitzro.dto;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolTail implements IReceivedMessage, ISendMessage<ProtocolTail, byte[]> {

	public static final int SIZE=2;
	
	private short crc;
	
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		return this.SIZE;
	}
 
	@Override
	public byte[] encode(ProtocolTail i) {
		ByteBuffer bf = ByteBuffer.allocate(ProtocolTail.SIZE);
		bf.order(i.getOrder());
		
		bf.putShort(i.getCrc()); 
		
		return bf.array();
	}

	@Override
	public void decode(byte[] array) {
		ByteBuffer bf = ByteBuffer.wrap(array);		
		bf.order(this.order);
		
		crc = bf.getShort();
		
	}

	

}
