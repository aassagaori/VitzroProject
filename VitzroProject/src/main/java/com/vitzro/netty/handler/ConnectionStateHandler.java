package com.vitzro.netty.handler;

import com.vitzto.netty.codec.TCPProtocolDecoder;
import com.vitzto.netty.codec.TCPProtocolEncoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/* Netty4 핸들러의 종류 
* https://netty.io/4.0/api/io/netty/channel/ChannelHandler.html
* 
* Netty에서 이벤트 발생에 따라서 다른 핸들로러 처리할 수 있게 여러게 등록할 수 있다.
* 연결 상태 관리 이벤트
* Incoder
* Decoder
* Decoder 한 패킷을 처리하는 핸들러 등등 
*  */
public class ConnectionStateHandler extends IdleStateHandler{

	public ConnectionStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(getClass() + "." +new Object(){}.getClass().getEnclosingMethod().getName());
		
		// 기존 ChannelPipeline 설정 제거하고 새로 설정
//		NettyHelper.removeAllChannelHandler(ctx);
		
		ChannelPipeline channelPipeline = ctx.pipeline();
		
		// Decodes a received {@link ByteBuf} into a {@link String}, DialupProcessHandler.channelRead0() 의 Param 이 String 이라서 사용??

		super.channelActive(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(getClass() + "." +new Object(){}.getClass().getEnclosingMethod().getName());
		
		super.channelInactive(ctx);
	}
	
	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt)
			throws Exception {
		System.out.println(getClass() + "." +new Object(){}.getClass().getEnclosingMethod().getName());
		
		super.channelIdle(ctx, evt);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println(getClass() + "." +new Object(){}.getClass().getEnclosingMethod().getName());
		
		super.exceptionCaught(ctx, cause);
	}

}
