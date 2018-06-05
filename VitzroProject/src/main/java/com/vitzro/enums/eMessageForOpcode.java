package com.vitzro.enums;

import java.util.HashMap;
import java.util.Map;

import com.vitzro.dto.CustomReceiveContent;
import com.vitzro.processor.AckProcessor;
import com.vitzro.processor.FredProcessor;
import com.vitzro.util.TypeHelper;


public enum eMessageForOpcode {
  
	_Fred(0x01, FredProcessor.class.getName())
	,Ack(1010, AckProcessor.class.getName())
	;	
	
	private final int value;
	private final String strValue;

	private final static Map<Integer, eMessageForOpcode> map = new HashMap<Integer, eMessageForOpcode>();
	static {
		
		for (eMessageForOpcode e : values())
			map.put(e.value, e);
	}

	private eMessageForOpcode(int value, String strValue) {
		this.value = value;
		this.strValue = strValue;
	}

	public static eMessageForOpcode forValue(int value) {
		return map.get(value);
	}

	public static eMessageForOpcode forValue(byte value) {
		return map.get(TypeHelper.unsignedByteToInt(value));
	}
	
	public int getValue() {
		return this.value;
	}
	
	public byte[] getByteArrayValue() {
		byte[] b = new byte[1];
		b[0] = (byte)this.value;
		return b;
	}

	@Override
	public String toString() {
		return this.strValue;
	}
	
	
}

