package com.vitzro.netty.handler;

import java.util.concurrent.CompletableFuture;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.dto.ProtocolData;
import com.vitzro.dto.ProtocolForm;
import com.vitzro.dto.ProtocolHeader;
import com.vitzro.dto.ProtocolHeader.ProtocolHeaderBuilder;
import com.vitzro.dto.ProtocolTail;
import com.vitzro.factory.ReceivedProcessorFactory;
import com.vitzro.util.DateHelper;

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
		
/*		ProtocolHeader h = ProtocolHeader.builder().build();
		ProtocolData d = ProtocolData.builder().build();
		ProtocolTail t = ProtocolTail.builder().build();
		
		h.decode(form.getHeader().encode(form.getHeader()));
		h.setDataCount(1);
		d.setOp_code(op_code);
		
		ProtocolForm ack = ProtocolForm.builder().header(form.getHeader())
		ProtocolHeader.builder().name(form.getHeader().getName())*/
		
//		ack 보내고 완료후 메시지 디코딩하도록 수정
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			//to do : ack 전송
				return form;
			}				
			, (ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor")
		).thenApplyAsync(result -> {
			String ret = "Success";
			for(int i=0 ; i<result.getHeader().getDataCount() ; i++) {
				
				try {
					ret = (String)ApplicationContextProvider.getBean(ReceivedProcessorFactory.class)
								.create(result.getContents().get(i).getOp_code())
								.processing(form);
				} catch (Exception e) {
					log.error("({} | {} | {}) | ",result.hashCode(),result.getContents().get(i).getOp_code(),result.getHeader().toString(),e);
					if(e.getClass().getSimpleName().equals("InterruptedException")) {
						Thread.currentThread().interrupt();
					}
					return "Fail";
				}
			}
			return ret;
		}, (ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor"));
		
		CompletableFuture.allOf(cf).join();
		log.debug("({} | {}) | {}",form.hashCode(),form.getHeader().toString(),cf.get());
		
/*		CompletableFuture cf= CompletableFuture.supplyAsync(() -> {
			Object obj = null;
			try {
				 = ApplicationContextProvider.getBean(ReceivedProcessorFactory.class)
				.create(form.getHeader().getOpCode())
				.processing(form);
			} catch (Exception e) {
				log.error("({} | {}) | ",form.hashCode(),form.getHeader().toString(),e);
				if(e.getClass().getSimpleName().equals("InterruptedException")) {
					Thread.currentThread().interrupt();
				}
			}
			return obj;
		}
				, (ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor"));	*/	
				
				

          /*  .thenApply(str->str+"+ tailed")

            .thenAccept(finalResult->System.out.println(finalResult));*/
		
		
	/*	CompletableFuture<String> future = null;
		try {
			future = ApplicationContextProvider.getBean(CompletableFutureProcessorFactory.class)
												.create(form.getHeader().getOpCode())
												.processing(form, ctx);
		}
		catch (Exception e) {
			log.error("({} | {}) | ",form.hashCode(),form.getHeader().toString(),e);
			if(e.getClass().getSimpleName().equals("InterruptedException")) {
				Thread.currentThread().interrupt();
			}
			return;
		};*/
		
	}

}
