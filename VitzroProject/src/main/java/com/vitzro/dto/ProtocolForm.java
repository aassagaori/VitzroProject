package com.vitzro.dto;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vitzro.util.CrcHelper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolForm implements IReceivedMessage, ISendMessage<ProtocolForm, byte[]> {
	
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;

	private ProtocolHeader header;
	private ProtocolTail tail;
	
//	private byte[] content;	
	@Builder.Default 
	private List<ProtocolData> contents = Collections.synchronizedList(new ArrayList<>());
	
	@Override
	public int size() {
		return ProtocolHeader.SIZE + (contents.size() == 0 ? 0 : contents.size()*ProtocolData.SIZE) +ProtocolTail.SIZE;
	}

	@Override
	public byte[] encode(ProtocolForm i) {
		
		ByteBuffer bf = ByteBuffer.allocate(this.size());
		bf.order(i.getOrder());
		bf.put(i.getHeader().encode(i.getHeader()));
		if(i.getContents().size() > 0) {
			for(int idx=0 ; idx < i.getContents().size() ; idx++) {
				ProtocolData d = i.getContents().get(idx);
				bf.put(d.encode(d));
			}
		}
		
		byte[] crc = new byte[ProtocolHeader.SIZE+i.getContents().size()*ProtocolData.SIZE];
		
		System.arraycopy(bf.array(), 0, crc, 0, crc.length);
		
		bf.put(i.getTail().encode(ProtocolTail.builder().crc((short)CrcHelper.getCRC16(crc)).build()));
		return bf.array();
	}
	
	@Override
	public void decode(byte[] array) {
		ByteBuffer bf = ByteBuffer.wrap(array);		
		bf.order(order);
		
		byte[] heaerArray = new byte[ProtocolHeader.SIZE]; 
		bf.get(heaerArray, 0, heaerArray.length); 
		header = ProtocolHeader.builder().build();
		header.decode(heaerArray);
		
		if(header.getDataCount() > 0) {
			byte[] remaining = new byte[bf.remaining()];
			bf.get(remaining, 0, bf.remaining()-ProtocolTail.SIZE); 
			
			for(int idx = 0 ; idx < header.getDataCount() ; idx++) {
				ProtocolData p = ProtocolData.builder().build();
				byte[] dest = new byte[ProtocolData.SIZE];
				
				System.arraycopy(remaining, idx*ProtocolData.SIZE, dest, 0, ProtocolData.SIZE);
				p.decode(dest);
				contents.add(p);
			}
		}
		byte[] tailArray = new byte[ProtocolTail.SIZE]; 
		bf.get(tailArray, 0, tailArray.length); 
		
		tail = ProtocolTail.builder().build();
		tail.decode(tailArray);
	}

}
