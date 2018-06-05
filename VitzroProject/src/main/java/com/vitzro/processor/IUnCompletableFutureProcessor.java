package com.vitzro.processor;

import com.vitzro.dto.IMessage;

public interface IUnCompletableFutureProcessor<T extends IMessage, O> {
	
	public O processing(T t);
	
}
