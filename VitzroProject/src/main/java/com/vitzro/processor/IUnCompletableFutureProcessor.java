package com.vitzro.processor;

import com.vitzro.protocol.IMessage;

public interface IUnCompletableFutureProcessor<T extends IMessage> {
	
	public void processing(T t) throws Exception;
	
}
