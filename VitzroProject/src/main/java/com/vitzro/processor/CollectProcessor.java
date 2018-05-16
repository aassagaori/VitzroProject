package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.dto.CollectContent;
import com.vitzro.dto.ProtocolForm;
import com.vitzro.service.CollectedDataStorage;
import com.vitzro.service.MessageHandler;
import com.vitzro.util.LogHelper;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope("prototype")
public class CollectProcessor implements ICompletableFutureProcessor<ProtocolForm,String> {
	
	@Override
	@Async
	public CompletableFuture<String> processing(ProtocolForm t, ChannelHandlerContext ctx) throws Exception {
		log.debug("UserProcessor UserProcessor UserProcessor");
		try {
			MessageHandler<ProtocolForm, CollectContent> m = new MessageHandler<>(ApplicationContextProvider.getBean(CollectedDataStorage.class));
			CollectContent c = m.handle(t);
			
		} catch (Exception e) {
			log.error("",e);
			return CompletableFuture.completedFuture("Fail | "+LogHelper.getPrintStackTrace(e));
		}
		return CompletableFuture.completedFuture("Success");
	}
}
