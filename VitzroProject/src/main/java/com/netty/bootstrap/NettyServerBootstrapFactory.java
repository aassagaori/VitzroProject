package com.netty.bootstrap;

import java.util.HashMap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @Author : Kim Ki Hwan
 * @Date   : 2018. 4. 10.
 * 
 */
public class NettyServerBootstrapFactory implements INettyBootstrapFactory<ServerBootstrap> {

	
	/*
	NioEventLoop는 I/O 동작을 다루는 멀티스레드 이벤트 루프입니다.
	네티는 다양한 이벤트 루프를 제공합니다.
	이 예제에서는 두개의 Nio 이벤트 루프를 사용합니다.
	첫번째 'parent' 그룹은 인커밍 커넥션(incomming connection)을 액세스합니다.
	두번째 'child' 그룹은 액세스한 커넥션의 트래픽을 처리합니다.
	만들어진 채널에 매핑하고 스레드를 얼마나 사용할지는 EventLoopGroup 구현에 의존합니다.
	그리고 생성자를 통해서도 구성할 수 있습니다.
	출처 - http://jdm.kr/blog/151
*/
	
	private NioEventLoopGroup nioAcceptLoopGroup;
	private NioEventLoopGroup nioWorkLoopGroup;
	private HashMap<ChannelOption<?>, Object> channelOptions;
	private ChannelInitializer<ServerChannel> channelInitializer;
	
	/**
	 * TCP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */
	public NettyServerBootstrapFactory(NioEventLoopGroup nioAcceptLoopGroup
									 , NioEventLoopGroup nioWorkLoopGroup
									 , HashMap<ChannelOption<?>, Object> channelOptions
									 , ChannelInitializer<ServerChannel> channelInitializer) {
		this.nioAcceptLoopGroup = nioAcceptLoopGroup;
		this.nioWorkLoopGroup = nioWorkLoopGroup;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServerBootstrap createBootstrap() {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		
		serverBootstrap.group(this.nioAcceptLoopGroup, this.nioWorkLoopGroup)
						// 인커밍 커넥션을 액세스하기 위해 새로운 채널을 객체화 하는 클래스 지정
					   .channel(NioServerSocketChannel.class)
					   // 상세한 Channel 구현을 위해 옵션을 지정
		               .option(ChannelOption.AUTO_READ, true)
		               // 새롭게 액세스된 Channel을 처리합니다.
		               // ChannelInitializer는 특별한 핸들러로 새로운 Channel의 환경 구성을 도와 주는 것이 목적
		               .childHandler(this.channelInitializer);
		
		// context-network.xml 에서 작성한 옵션값을 설정한다.
		for (ChannelOption channelOption : channelOptions.keySet()) {
			serverBootstrap.option(channelOption, channelOptions.get(channelOption));
		}
		
		return serverBootstrap;
	}
	
}
