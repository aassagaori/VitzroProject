package com.vitzro.dto;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedInts;
import com.google.common.primitives.UnsignedLong;
import com.google.common.primitives.UnsignedLongs;
import com.vitzro.util.TypeHelper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProtocolData implements IReceivedMessage, ISendMessage<ProtocolData, byte[]> {

	public static final int SIZE=44; //EB마을버스 데이터부분 패킷 사이즈, 모든 OPCODE별 데이터부분 사이즈가 고정임
	
	private int op_code;
	private String route_id;
	private String veh_id;
	private String occur_date;
	private String node_id;
	private double lng;
	private double lat;
	private int cdma;
	private int ord;
	private byte event_type;
	private byte node_type;

	@Builder.Default
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	@Override
	public int size() {
		return this.SIZE;
	}
	
	@Override
	public byte[] encode(ProtocolData i) {
		ByteBuffer bf = ByteBuffer.allocate(ProtocolData.SIZE);
		bf.order(i.getOrder());
		
	    bf.putShort((short)i.getOp_code());
	    bf.putInt(UnsignedInts.parseUnsignedInt((i.getRoute_id()==null || i.getRoute_id().length()==0 ? "0" :  i.getRoute_id().trim())));
	    bf.putInt(UnsignedInts.parseUnsignedInt( (i.getVeh_id()==null || i.getVeh_id().length()==0 ? "0" :  i.getVeh_id().trim())));
	    
	    byte[] occurDataArray = new byte[6];
	    occurDataArray[0] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? (byte)(Integer.valueOf(i.getOccur_date().substring(0, 4).trim()).intValue() - 1900) : 0);
	    occurDataArray[1] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? Byte.valueOf(i.getOccur_date().substring(4, 6).trim()).byteValue() : 0);
	    occurDataArray[2] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? Byte.valueOf(i.getOccur_date().substring(6, 8).trim()).byteValue() : 0);
	    occurDataArray[3] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? Byte.valueOf(i.getOccur_date().substring(8, 10).trim()).byteValue() : 0);
	    occurDataArray[4] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? Byte.valueOf(i.getOccur_date().substring(10, 12).trim()).byteValue() : 0);
	    occurDataArray[5] = ((i.getOccur_date() != null) && (i.getOccur_date().length() == 14) ? Byte.valueOf(i.getOccur_date().substring(12, 14).trim()).byteValue() : 0);
	    
	    bf.put(occurDataArray);
	    
	    bf.putLong(UnsignedLongs.parseUnsignedLong((i.getNode_id()==null || i.getNode_id().length()==0 ? "0" :  i.getNode_id().trim())));
	    bf.putInt((int)(i.getLng() * 1000000.0D));
	    bf.putInt((int)(i.getLat() * 1000000.0D));
	    bf.putInt(i.getCdma());
	    bf.putInt(i.getOrd());
	    bf.put(i.getEvent_type());
	    bf.put(i.getNode_type());
	    
	    bf.put((byte)0);
	    bf.put((byte)0);
		
		return bf.array();
	}

	@Override
	public void decode(byte[] array) {
		ByteBuffer bf = ByteBuffer.wrap(array);		
		bf.order(this.order);
		op_code = TypeHelper.unsignedShortToInt(bf.getShort());
		route_id = UnsignedInts.toString(bf.getInt());
		veh_id = UnsignedInts.toString(bf.getInt());
		
		this.occur_date = String.format("%04d%02d%02d%02d%02d%02d", new Object[] {
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get()) + 1900), 
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get())), 
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get())), 
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get())), 
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get())), 
		      Integer.valueOf(TypeHelper.unsignedByteToInt(bf.get())) });
		  
		this.node_id = String.valueOf(bf.getLong());
		
	    this.lng = (TypeHelper.intToUnsignedInt(bf.getInt()) / 1000000.0D);
	    this.lat = (TypeHelper.intToUnsignedInt(bf.getInt()) / 1000000.0D);
	    
	    this.cdma = bf.getInt();
	    this.ord = bf.getInt();
	    
	    this.event_type = bf.get();
	    this.node_type = bf.get();
	}
	
}
