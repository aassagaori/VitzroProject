package com.vitzro.netty;

import java.net.InetSocketAddress;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.vitzro.netty.bootstrap.NettyClientBootstrapFactory;
import com.vitzro.netty.listener.CloseFutureListener;
import com.vitzro.netty.listener.ConnectFutureListener;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
/**
 * The type Netty server.
 */
@Service
@Slf4j
//@PropertySource(value = "file:${user.dir}/conf/application.properties")
public class NettyClient implements Runnable{

	@Autowired 
	private NettyClientBootstrapFactory factory;
		
	@Value("${client.port}")
	private int port;

	@Value("${client.ip}")
	private String ip;
	
	@Value("${client.reconnectTime}")
	private int reconnectTime;
	
	@Override
	@Async
	public void run() {
    	try {
    		log.debug("start client connect...");
    		Bootstrap b = factory.createBootstrap();
    		if(b==null) return ;
    		
			ChannelFuture cf = b.connect(new InetSocketAddress(this.ip, this.port))
					.addListener(new ConnectFutureListener(this))
					.channel().closeFuture().addListener(new CloseFutureListener(this)).sync();
		}
    	catch (InterruptedException e) {
    		log.error("",e);//
			Thread.currentThread().interrupt();
		} 
    	
    	return ;
	}
	public int getReconnectTime() {
		return reconnectTime;
	}
	public void setReconnectTime(int reconnectTime) {
		this.reconnectTime = reconnectTime;
	}
	
	@PreDestroy
	public void shutDown() {
		factory.getClientWorkerGroup().shutdownGracefully();
	}
}