package com.vitzro.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.vitzro.netty.initializer.CustomChannelInitializer;

import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource(value="file:${user.dir}/conf/netty.properties")
@Slf4j
public class TCPConfig {
	
	/* @Autowired - Spring의 의존관계(DI)를 자동으로 설정, 타입(by type)으로 연결 */
	@Autowired
	public Environment env;
	
	@Profile("TCPServer")
	public static class ServerConfig {
		@Autowired
		public Environment env;

		@Bean
		public EventLoopGroup serverAcceptGroup(){
			
			return new NioEventLoopGroup(Integer.parseInt(env.getProperty("server.acceptThread.count")));
		}
		@Bean
		public EventLoopGroup serverWorkerGroup(){
			
			return new NioEventLoopGroup(Integer.parseInt(env.getProperty("server.workerThread.count")));
		}
		@Bean
		public CustomChannelInitializer serverChannelInitializer(){
			
			return new CustomChannelInitializer();
		}
	}
	
	@Profile("TCPClient")
	public static class ClientConfig {
		@Autowired
		public Environment env;
		
		@Bean
		public EventLoopGroup clientWorkerGroup(){
			
			return new NioEventLoopGroup(Integer.parseInt(env.getProperty("client.workerthread.count")));
		}
		@Bean
		public CustomChannelInitializer clientChannelInitializer(){
			
			return new CustomChannelInitializer();
		}
	}
	
	@Bean
	public HashMap<ChannelOption<?>, Object> tcpChannelOptions(){
		
		HashMap<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		
		options.put(ChannelOption.TCP_NODELAY, Boolean.parseBoolean(env.getProperty("netty.tcpNodelay")));
		options.put(ChannelOption.SO_RCVBUF, Integer.parseInt(env.getProperty("netty.rcvBufSize")));
		options.put(ChannelOption.SO_SNDBUF, Integer.parseInt(env.getProperty("netty.sndBufSize")));
		options.put(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.parseInt(env.getProperty("netty.connectTimeout")));
		options.put(ChannelOption.SO_KEEPALIVE, Boolean.parseBoolean(env.getProperty("netty.keepAlive")));
		
		return options;
	}
	
	
	/*@Bean(name="serverBootstrap")
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
	}*/
	
	
}
