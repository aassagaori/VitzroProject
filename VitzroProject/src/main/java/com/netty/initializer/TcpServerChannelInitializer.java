package com.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import com.netty.handler.ConnectionHandler;


//새롭게 액세스된 Channel을 처리합니다.
//ChannelInitializer는 특별한 핸들러로 새로운 Channel의 환경 구성을 도와 주는 것이 목적
public class TcpServerChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		/*ChannelPipeline - Channel에 드나드는 inbound / outbound 이벤트를 처리
		 * 				   - Intercepting Filter 패턴 처리, ChannelHandler 리스트 */
		ChannelPipeline channelPipeline = socketChannel.pipeline();

		channelPipeline.addLast("ConnectionHandler", new ConnectionHandler(10, 10, 20));
		
	}

}
