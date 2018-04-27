package com.util;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import java.net.InetSocketAddress;


public abstract class NettyHelper {

	/**
	 * 모든 채널 핸들러를 제거한다.
	 */
	public static void removeAllChannelHandler(ChannelHandlerContext ctx) {
		ChannelPipeline channelPipeline = ctx.pipeline();

		for (ChannelHandler channelHandler : channelPipeline.toMap().values()) {
			channelPipeline.remove(channelHandler);
		}
	}

	/**
	 * 원격지 IP 주소를 문자열로 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static String getRemoteAddress(ChannelHandlerContext channelHandlerContext) {
		String remoteAddress = null; // 원격지 주소
		InetSocketAddress inetAddr = (InetSocketAddress)channelHandlerContext.channel().remoteAddress();
		if(inetAddr != null) {
			remoteAddress = inetAddr.getAddress().getHostAddress();
		} else {
			remoteAddress = "unknown";
		}
		return remoteAddress;
	}
	/**
	 * 원격지 PORT 번호를 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static int getRemotePort(ChannelHandlerContext channelHandlerContext) {
		int remotePort = 0; // 원격지 포트
		InetSocketAddress inetAddr = (InetSocketAddress)channelHandlerContext.channel().remoteAddress();
		if(inetAddr != null) {
			remotePort = inetAddr.getPort();
		} else {
			remotePort = 0;
		}
		return remotePort;
	}
	
	/**
	 * 로컬 IP 주소를 문자열로 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static String getLocalAddress(ChannelHandlerContext channelHandlerContext) {
		String localAddress = null; // 원격지 주소
		InetSocketAddress inetAddr = (InetSocketAddress)channelHandlerContext.channel().localAddress();
		if(inetAddr != null) {
			localAddress = inetAddr.getAddress().getHostAddress();
		} else {
			localAddress = "unknown";
		}
		return localAddress;
	}
	/**
	 * 로컬 PORT 번호를 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static int getLocalPort(ChannelHandlerContext channelHandlerContext) {
		int localPort = 0; // 원격지 포트
		InetSocketAddress inetAddr = (InetSocketAddress)channelHandlerContext.channel().localAddress();
		if(inetAddr != null) {
			localPort = inetAddr.getPort();
		} else {
			localPort = 0;
		}
		return localPort;
	}
}