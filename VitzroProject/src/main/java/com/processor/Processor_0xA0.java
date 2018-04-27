package com.processor;

import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.structs.Vitzro_ReceiveData;

public class Processor_0xA0 implements Processable {
	
	private static final Logger logger = LoggerFactory.getLogger(Processor_0xA0.class);

	private final Channel channel;
	private final Vitzro_ReceiveData data;
	
	public Processor_0xA0(Channel channel, Vitzro_ReceiveData data) {
		this.channel = channel;
		this.data = data;
	}

	@Override
	public void Processing() {
		// TODO Auto-generated method stub
		
	}

}
