package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;

import com.vitzro.protocol.IMessage;

import io.netty.channel.ChannelHandlerContext;

public interface ICompletableFutureProcessor<T extends IMessage> {
	
	public CompletableFuture <String> processing(T t, ChannelHandlerContext ctx) throws Exception;
	
}
