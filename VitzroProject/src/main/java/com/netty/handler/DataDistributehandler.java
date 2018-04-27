package com.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.config.ApplicationContextProvider;
import com.data.structs.Vitzro_ReceiveData;
import com.service.AsyService;

public class DataDistributehandler extends SimpleChannelInboundHandler<Object>{
	
	
	/* @Autowired 은 스프링 빈 파일에서만 쓸수 있따. 여기서 쓸려면 class가 @Service 등등 어노테이션이 작성되어 있어야한다. 
	@Autowired
    private AsyService asdasd;
	*/

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
		System.out.println(	new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		Vitzro_ReceiveData data = (Vitzro_ReceiveData)msg;
		
//		AsyService thread = (AsyService)ApplicationContextProvider.getBean(AsyService.class);
		ApplicationContextProvider.getBean(AsyService.class).method(ctx.channel(), data);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
