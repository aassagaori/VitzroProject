package com.vitzro.processor;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.dto.CustomReceiveContent;
import com.vitzro.dto.ProtocolForm;
import com.vitzro.service.CustomReceivedDataStorage;
import com.vitzro.service.MessageHandler;
import com.vitzro.util.LogHelper;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope("prototype")
public class CustomCollectProcessor implements ICompletableFutureProcessor<ProtocolForm,String> {
	
	@Override
	@Async
	public CompletableFuture<String> processing(ProtocolForm t, ChannelHandlerContext ctx) throws Exception {	
		try {
			CustomReceivedDataStorage s = ApplicationContextProvider.getBean(CustomReceivedDataStorage.class);
			MessageHandler<ProtocolForm, CustomReceiveContent> m = new MessageHandler<>(s);
			CustomReceiveContent c = m.handle(t);
			
		} catch (Exception e) {
			log.error("",e);
			return CompletableFuture.completedFuture("Fail | "+LogHelper.getPrintStackTrace(e));
		}
		return CompletableFuture.completedFuture("Success");
	}
}
