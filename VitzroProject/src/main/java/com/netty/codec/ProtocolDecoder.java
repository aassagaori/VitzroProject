package com.netty.codec;

import java.util.List;

import com.util.TypeHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtocolDecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println(	new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		System.out.println("readerIndex - " + in.readerIndex());
		System.out.println("readableBytes - " + in.readableBytes());
		
		byte[] readBytes = new byte[in.readableBytes()];
		
		// getBytes() 는 readerIndex 의 변화가 없다
		in.getBytes(in.readerIndex(), readBytes);
		
		in.clear();
		
		System.out.println(TypeHelper.byteArrayToHex(readBytes));		
		
		out.add(readBytes);
		
	}
}
