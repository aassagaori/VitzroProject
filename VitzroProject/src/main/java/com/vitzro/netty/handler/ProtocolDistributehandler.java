package com.vitzro.netty.handler;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.concurrent.CompletableFuture;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.enums.eOpcode;
import com.vitzro.factory.CompletableFutureProcessorFactory;
import com.vitzro.processor.ICompletableFutureProcessor;
import com.vitzro.protocol.ProtocolForm;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtocolDistributehandler extends SimpleChannelInboundHandler<Object>{
	
	
	/* @Autowired 은 스프링 빈 파일에서만 쓸수 있따. 여기서 쓸려면 class가 @Service 등등 어노테이션이 작성되어 있어야한다. 
	@Autowired
    private AsyService asdasd;
	*/

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		ProtocolForm form = (ProtocolForm)msg;
		CompletableFuture<String> future = null;
		try {
			future = ApplicationContextProvider.getBean(CompletableFutureProcessorFactory.class)
												.create(form.getHeader().getOpCode())
												.processing(form, ctx);
		} 
		catch (Exception e) {
			log.error("{} | {} | ",form.hashCode(),form.getHeader().toString(),e);
			if(e.getClass().getSimpleName().equals("InterruptedException")) {
				Thread.currentThread().interrupt();
			}
			return;
		};
		/*CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(()->{
			try {
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		});*/
	}

}
