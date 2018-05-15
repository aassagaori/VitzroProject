package com.vitzro.netty.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vitzro.netty.NettyClient;
import com.vitzro.util.TypeHelper;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectFutureListener implements ChannelFutureListener {

//	private static final Logger logger = LoggerFactory.getLogger(ConnectFutureListener.class);
	
	private final NettyClient nettyClient;
	
	public ConnectFutureListener(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
		if(channelFuture.isCancelled()) {
			log.error("Connect cancel",channelFuture.cause());
			channelFuture.channel().close();	
		}
		else if (!channelFuture.isSuccess()) {
			log.error("Connect fail",channelFuture.cause());
			channelFuture.channel().close();			
		}else if(channelFuture.isSuccess()){
			log.debug("Connect Success {}",channelFuture.channel().toString());
		}
	}
}
