package com.vitzro.netty.bootstrap;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.netty.initializer.CustomChannelInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

@Component
public class NettyClientBootstrapFactory {

	@Autowired
	private EventLoopGroup clientWorkerGroup;
	
	@Autowired
	private CustomChannelInitializer clientChannelInitializer;
	
	@Autowired
	private  HashMap<ChannelOption<?>, Object> tcpChannelOptions;
  
	private Bootstrap b;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public Bootstrap createBootstrap() {
		try {
			b = new Bootstrap();
			b.group(this.clientWorkerGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.AUTO_READ, true)
				.handler(this.clientChannelInitializer);
		
			for (ChannelOption channelOption : tcpChannelOptions.keySet()) {
				b.option(channelOption, tcpChannelOptions.get(channelOption));
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
    
		return b;
    }


	public EventLoopGroup getClientWorkerGroup() {
		return clientWorkerGroup;
	}


	public void setClientWorkerGroup(EventLoopGroup clientWorkerGroup) {
		this.clientWorkerGroup = clientWorkerGroup;
	}

	
	
	
}
