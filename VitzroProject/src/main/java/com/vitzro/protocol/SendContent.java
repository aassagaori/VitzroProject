package com.vitzro.protocol;

import java.nio.ByteOrder;

import com.vitzro.protocol.ReceivedContent.ReceivedContentBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendContent implements ISendMessage<SendContent, byte[]>{
	
	public static final int SIZE=20;
	@Builder.Default 
	private ByteOrder order = ByteOrder.BIG_ENDIAN;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public byte[] encode(SendContent i) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
