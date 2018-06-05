package com.vitzro.dto;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.common.primitives.UnsignedBytes;
import com.vitzro.util.TypeHelper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolHeader implements IReceivedMessage, ISendMessage<ProtocolHeader, byte[]> {

	public static final int SIZE=21;
	
	private String name;
	private String dateTime;
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	private int dataCount;
	private int dataPacketNo;

	@Builder.Default
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		return this.SIZE;
	}

	@Override
	public byte[] encode(ProtocolHeader i) {
		ByteBuffer bf = ByteBuffer.allocate(ProtocolHeader.SIZE);
		bf.order(i.getOrder());
		
		byte[] nameArray = new byte[9];
		System.arraycopy(i.getName().getBytes(), 0, nameArray, 0, nameArray.length);
		bf.put(nameArray);   
		
		int y = i.getYear()-1900;
		byte by = (byte)y;
		bf.put((byte)(y&0xff));
		bf.put((byte)i.getMonth());
		bf.put((byte)i.getDate());
		bf.put((byte)i.getHour());
		bf.put((byte)i.getMinute());
		bf.put((byte)i.getSecond());
		bf.putShort((short)i.getDataCount());
		bf.putInt(i.getDataPacketNo());
		
		return bf.array();
	}

	@Override
	public void decode(byte[] array) {
		ByteBuffer bf = ByteBuffer.wrap(array);		
		bf.order(this.order);
		
		byte[] nameArray = new byte[9];
		bf.get(nameArray);		
		name = new String(nameArray);
		
		year = UnsignedBytes.toInt(bf.get())+1900;
		month = UnsignedBytes.toInt(bf.get());
		date = UnsignedBytes.toInt(bf.get());
		hour = UnsignedBytes.toInt(bf.get());
		minute = UnsignedBytes.toInt(bf.get());
		second = UnsignedBytes.toInt(bf.get());
		
		dataCount = TypeHelper.unsignedShortToInt(bf.getShort());
		dataPacketNo = bf.getInt();
	}

}
