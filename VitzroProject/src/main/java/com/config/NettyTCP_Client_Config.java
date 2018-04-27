package com.config;

import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class NettyTCP_Client_Config {
	
	/* @Autowired - Spring의 의존관계(DI)를 자동으로 설정, 타입(by type)으로 연결 */
	@Autowired
	private Environment env;
	
	@Bean(name="tcpChannelOptions")
	public Map<ChannelOption<?>, Object> tcpChannelOptions(){
		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();

		options.put(ChannelOption.TCP_NODELAY, Boolean.parseBoolean(env.getProperty("netty.tcpNodelay")));
		options.put(ChannelOption.SO_RCVBUF, Integer.parseInt(env.getProperty("netty.rcvBufSize")));
		options.put(ChannelOption.SO_SNDBUF, Integer.parseInt(env.getProperty("netty.sndBufSize")));
		options.put(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.parseInt(env.getProperty("netty.connectTimeout")));
		options.put(ChannelOption.SO_KEEPALIVE, Boolean.parseBoolean(env.getProperty("netty.keepAlive")));
		
		return options;
		
	}

}
