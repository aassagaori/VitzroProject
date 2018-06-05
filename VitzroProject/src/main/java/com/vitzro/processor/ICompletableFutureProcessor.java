package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;

import com.vitzro.dto.IMessage;

public interface ICompletableFutureProcessor<T extends IMessage, O> {
	
	public CompletableFuture<O> processing(T t);
	
}
