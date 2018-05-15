package com.vitzro.netty.bootstrap;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.netty.initializer.CustomChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class NettyServerBootstrapFactory {
	
	@Autowired
	private EventLoopGroup serverAcceptGroup;
	
	@Autowired
	private EventLoopGroup serverWorkerGroup;
	
	@Autowired
	private CustomChannelInitializer serverChannelInitializer;
	
	@Autowired
	private  HashMap<ChannelOption<?>, Object> tcpChannelOptions;
  
	private ServerBootstrap b;

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public ServerBootstrap createBootstrap() {
		try {
			b = new ServerBootstrap();
			b.group(this.serverAcceptGroup, this.serverWorkerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.AUTO_READ, true)
				.childHandler(this.serverChannelInitializer);
		
			for (ChannelOption channelOption : tcpChannelOptions.keySet()) {
				b.option(channelOption, tcpChannelOptions.get(channelOption));
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
    
		return b;
    }

	public EventLoopGroup getServerAcceptGroup() {
		return serverAcceptGroup;
	}

	public void setServerAcceptGroup(EventLoopGroup serverAcceptGroup) {
		this.serverAcceptGroup = serverAcceptGroup;
	}

	public EventLoopGroup getServerWorkerGroup() {
		return serverWorkerGroup;
	}

	public void setServerWorkerGroup(EventLoopGroup serverWorkerGroup) {
		this.serverWorkerGroup = serverWorkerGroup;
	}
	
	
}
