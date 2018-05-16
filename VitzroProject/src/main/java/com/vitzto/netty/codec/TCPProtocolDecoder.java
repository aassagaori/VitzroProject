package com.vitzto.netty.codec;

import java.util.List;

import com.vitzro.dto.ProtocolForm;
import com.vitzro.dto.ProtocolHeader;
import com.vitzro.dto.ProtocolTail;
import com.vitzro.util.NettyHelper;
import com.vitzro.util.TypeHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TCPProtocolDecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
		
		if (log.isDebugEnabled()) { 
			byte[] readBytes = new byte[byteBuf.readableBytes()];//현재 수신버퍼에서 읽을 수 있는 크기만큼 바이트배열을 생성하여
			try {
				byteBuf.getBytes(byteBuf.readerIndex(), readBytes);//readerindex를 증가시키지 않는 getBytes함수를 이용하여 버퍼에 쌓인 패킷을 바이트배열에 저장한다.
				
				//패킷은 헥사로 변환한다.
				log.debug("[({} | {}-{}){}]", byteBuf.readableBytes(), byteBuf.readerIndex(),
						byteBuf.writerIndex(), TypeHelper.byteArrayToHex(readBytes, " "));
			} catch (Exception e) {
				log.error("Logging stream data fail : ", e);
			}
		}

		while (byteBuf.readableBytes() >= ProtocolHeader.SIZE) {
			// Header Read
			ByteBuf headBuf = byteBuf.readBytes(ProtocolHeader.SIZE);
			ProtocolHeader h = ProtocolHeader.builder().build();			
			h.decode(headBuf.array());

			// Data Read
			if (h.getContentLength() > byteBuf.readableBytes()) {
				log.warn("D | Must be accepted more data(protocol), length[{}] > readableBytes[{}]", h.getContentLength(),
						byteBuf.readableBytes());
				return; // 다 못받은 것이므로 리턴함
			}

			ByteBuf contentBuf = byteBuf.readBytes(h.getContentLength());

			if (ProtocolTail.SIZE > byteBuf.readableBytes()) {
				log.warn("D | Must be accepted more data(tail), length[{}] > readableBytes[{}]", ProtocolTail.SIZE,
						byteBuf.readableBytes());
				return; // 다 못받은 것이므로 리턴함
			}

			ByteBuf tailBuf = byteBuf.readBytes(ProtocolTail.SIZE);
			ProtocolTail t = ProtocolTail.builder().build();
			t.decode(tailBuf.array());
			
			ProtocolForm f = ProtocolForm.builder()
					.header(h)
					.content(contentBuf.array())
					.tail(t)
					.build();
			
			out.add(f);

			byteBuf.markReaderIndex();
			log.info("[{}] | [({}){}] | {}", f.hashCode(), f.size(), TypeHelper.byteArrayToHex(f.encode(f), " "),NettyHelper.getRemoteAddress(ctx.channel()));
			log.info(" >>> [{}] | [{}]",f.hashCode(), f.getHeader().toString());
		}
		
	}
}
