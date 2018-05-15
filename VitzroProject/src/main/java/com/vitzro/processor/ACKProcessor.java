package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vitzro.netty.NettyClient;
import com.vitzro.protocol.ProtocolForm;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Scope("prototype")
public class ACKProcessor implements ICompletableFutureProcessor<ProtocolForm> {
	
	@Override
	@Async
	public CompletableFuture<String> processing(ProtocolForm t, ChannelHandlerContext ctx) throws Exception {
		log.debug("UserProcessor UserProcessor UserProcessor");
		
		
		return CompletableFuture.completedFuture("Success 11111");
	}

	
}
