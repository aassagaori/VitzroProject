package com.vitzro.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolForm implements IReceivedMessage<byte[]>, ISendMessage<ProtocolForm, byte[]> {

	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	private ProtocolHeader header;
	private ProtocolTail tail;
	private byte[] content;
	
	@Override
	public int size() {
		return ProtocolHeader.SIZE + ( this.content == null ? 0 : content.length) +ProtocolTail.SIZE;
	}

	@Override
	public byte[] encode(ProtocolForm i) {
		
		ByteBuffer buffer = ByteBuffer.allocate(this.size());
		buffer.order(order);
		buffer.put(header.encode(i.getHeader()));
		if(content != null) { buffer.put(i.getContent()); }
		buffer.put(tail.encode(i.getTail()));
		return buffer.array();
	}

	@Override
	public void decode(byte[] i) {
		ByteBuffer buffer = ByteBuffer.wrap(i);		
		buffer.order(order);
		
		byte[] heaerArray = new byte[ProtocolHeader.SIZE]; 
		buffer.get(heaerArray, 0, heaerArray.length); 
		header = ProtocolHeader.builder().build();
		header.decode(heaerArray);
		
		if(header.getContentLength() != 0) {
			content = new byte[buffer.remaining()];
			buffer.get(content, 0, buffer.remaining()-ProtocolTail.SIZE); 
		}
					
		byte[] tailArray = new byte[ProtocolTail.SIZE]; 
		buffer.get(tailArray, 0, tailArray.length); 
		tail = ProtocolTail.builder().build();
		tail.decode(tailArray);
		
	}


}
