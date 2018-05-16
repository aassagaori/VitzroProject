package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;

import com.vitzro.dto.IMessage;

import io.netty.channel.ChannelHandlerContext;

public interface ICompletableFutureProcessor<T extends IMessage, O> {
	
	public CompletableFuture <O> processing(T t, ChannelHandlerContext ctx) throws Exception;
	
}
