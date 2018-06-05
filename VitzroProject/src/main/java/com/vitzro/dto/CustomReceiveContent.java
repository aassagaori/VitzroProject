package com.vitzro.dto;

import java.nio.ByteOrder;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CustomReceiveContent implements IReceivedMessage{
	
	public static final int SIZE=20;
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public void decode(byte[] array) {
		log.debug("CustomReceiveContent CustomReceiveContent CustomReceiveContent");
		
	}

}
