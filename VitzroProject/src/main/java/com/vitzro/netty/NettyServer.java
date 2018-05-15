package com.vitzro.netty;

import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.vitzro.netty.bootstrap.NettyServerBootstrapFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
/**
 * The type Netty server.
 */
@Component
@Slf4j
//@PropertySource(value = "file:${user.dir}/conf/application.properties")
public class NettyServer {
	/* application.properties 에 있는 값들은 
	 * 따로 불러올 필요 없이 사용 가능
	 * */
	
	@Autowired 
	private NettyServerBootstrapFactory factory;
		
//	@Value("#{'${server.ports}'.split(',')}")
//	private List<Integer> portList;
//	
	@Value("${server.port}")
	private int port;

	   
	public void run() {
    	
    	try {
    		ServerBootstrap b = factory.createBootstrap();
    		if(b==null) return;
//    		System.out.println(port);
    		
			ChannelFuture channelFuture = b.bind(port).sync()
					.addListener(new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture future) throws Exception {
							if(future.isSuccess()) {
								System.out.println("Bind Success "+ port);
							}
						}
					});
			channelFuture.channel().closeFuture().sync();
		}
    	catch (InterruptedException e) {
			System.out.println("aaaaa+"+e);
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("Server Shutwdown..");
			factory.getServerAcceptGroup().shutdownGracefully();
			factory.getServerWorkerGroup().shutdownGracefully();
		} 
	}
}