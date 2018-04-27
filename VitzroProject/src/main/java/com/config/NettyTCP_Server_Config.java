package com.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.netty.NettyTCPServer;
import com.netty.bootstrap.NettyServerBootstrapFactory;
import com.netty.initializer.TcpServerChannelInitializer;

@Configuration
public class NettyTCP_Server_Config {
	
	/* @Autowired - Spring의 의존관계(DI)를 자동으로 설정, 타입(by type)으로 연결 */
	@Autowired
	private Environment env;
	
	public HashMap<ChannelOption<?>, Object> tcpChannelOptions(){
		System.out.println(new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		HashMap<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();

		options.put(ChannelOption.TCP_NODELAY, Boolean.parseBoolean(env.getProperty("netty.tcpNodelay")));
		options.put(ChannelOption.SO_RCVBUF, Integer.parseInt(env.getProperty("netty.rcvBufSize")));
		options.put(ChannelOption.SO_SNDBUF, Integer.parseInt(env.getProperty("netty.sndBufSize")));
		options.put(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.parseInt(env.getProperty("netty.connectTimeout")));
		options.put(ChannelOption.SO_KEEPALIVE, Boolean.parseBoolean(env.getProperty("netty.keepAlive")));
		
		return options;
	}
	
	public NioEventLoopGroup serverAcceptGroup(){
		System.out.println(new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		return new NioEventLoopGroup(Integer.parseInt(env.getProperty("netty.server.acceptthread.count")));
	}
	
	public NioEventLoopGroup serverWorkerGroup(){
		System.out.println(new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		return new NioEventLoopGroup(Integer.parseInt(env.getProperty("netty.server.workerthread.count")));
	}
	
	public TcpServerChannelInitializer channelInitializer(){
		System.out.println(new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		return new TcpServerChannelInitializer();
	}
	
	@Bean(name="serverBootstrap")
	public NettyServerBootstrapFactory serverBootstrapFactory(){
		System.out.println(new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		return new NettyServerBootstrapFactory(serverAcceptGroup()
											 , serverWorkerGroup()
											 , tcpChannelOptions()
											 , (ChannelInitializer)channelInitializer()
											 );
	}
	
	@Bean
	public NettyTCPServer nettyServer(){
		return new NettyTCPServer(Integer.parseInt(env.getProperty("signal.server.port"))
								, serverBootstrapFactory().createBootstrap()
								);
	}
	
	
}
