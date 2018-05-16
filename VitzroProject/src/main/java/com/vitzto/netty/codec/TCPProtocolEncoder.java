package com.vitzto.netty.codec;

import com.vitzro.dto.ProtocolForm;
import com.vitzro.util.NettyHelper;
import com.vitzro.util.TypeHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TCPProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
	
		
		ProtocolForm f = (ProtocolForm)msg;
		out.writeBytes(f.encode(f));
		
		log.info("[{}] | [({}){}] | {}", f.hashCode(), f.size(), TypeHelper.byteArrayToHex(f.encode(f), " "),NettyHelper.getRemoteAddress(ctx.channel()));
		log.info(" >>> [{}] | [{}]",f.hashCode(), f.getHeader().toString());
	}
}
