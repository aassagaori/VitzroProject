package com.vitzro.netty.listener;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vitzro.netty.NettyClient;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloseFutureListener implements ChannelFutureListener {
	
//	private static final Logger logger = LoggerFactory.getLogger(CloseFutureListener.class);

	private final NettyClient nettyClient;
	
	public CloseFutureListener(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
//		System.out.println("Close  "+channelFuture.cause());
		log.warn("Close Future | Success | {} | Retry Connect - Channel Close",channelFuture.channel().toString());
		try {
			channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime(), TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("",e);
		}
		
		
	}

}

