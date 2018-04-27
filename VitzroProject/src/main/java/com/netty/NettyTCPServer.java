package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;

public class NettyTCPServer {
	
	private int port;
	private ServerBootstrap serverBootstrap;
	private Channel serverChannel;
	
	public NettyTCPServer(int port, ServerBootstrap serverBootstrap) {
		this.port = port;
		this.serverBootstrap = serverBootstrap;
	}
	
	// 쓰레드 돌리면 call 메소드 실행
	public Object start() throws Exception{
		
		System.out.println("NettyTCPServer Start ["+this.port+"]");
		this.serverChannel = this.serverBootstrap.bind(this.port).sync().channel();
		this.serverChannel.closeFuture().await();
		
		return serverChannel;
		
	}

}
