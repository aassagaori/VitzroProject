package com.vitzro.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.springframework.core.env.Environment;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.netty.handler.ConnectionStateHandler;
import com.vitzro.netty.handler.ProtocolDistributehandler;
import com.vitzto.netty.codec.TCPProtocolDecoder;
import com.vitzto.netty.codec.TCPProtocolEncoder;


//새롭게 액세스된 Channel을 처리합니다.
//ChannelInitializer는 특별한 핸들러로 새로운 Channel의 환경 구성을 도와 주는 것이 목적
public class CustomChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		/*ChannelPipeline - Channel에 드나드는 inbound / outbound 이벤트를 처리
		 * 				   - Intercepting Filter 패턴 처리, ChannelHandler 리스트 */
		ChannelPipeline channelPipeline = socketChannel.pipeline();

		int all = Integer.valueOf(ApplicationContextProvider.getBean(Environment.class).getProperty("allidletime","10"));
		int read = Integer.valueOf(ApplicationContextProvider.getBean(Environment.class).getProperty("readeridletime","10"));
		int write = Integer.valueOf(ApplicationContextProvider.getBean(Environment.class).getProperty("writeridletime","10"));

		channelPipeline.addLast("ConnectionStateHandler", new ConnectionStateHandler(read, write, all));
		channelPipeline.addLast("TCPProtocolDecoder", new TCPProtocolDecoder());
		channelPipeline.addLast("TCPProtocolEncoder", new TCPProtocolEncoder());
		channelPipeline.addLast("ProtocolDistributehandler", new ProtocolDistributehandler());
		
	}

}
