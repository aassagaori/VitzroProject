package com.vitzro.enums;

import java.util.HashMap;
import java.util.Map;

import com.vitzro.processor.AckProcessor;
import com.vitzro.processor.FredProcessor;
import com.vitzro.util.TypeHelper;


public enum eProcessorForOpcode {
  
	_Fred(0x01, FredProcessor.class.getName())
	,Ack(1010, AckProcessor.class.getName())

	;	
	
	private final int value;
	private final String strValue;

	private final static Map<Integer, eProcessorForOpcode> map = new HashMap<Integer, eProcessorForOpcode>();
	static {
		
		for (eProcessorForOpcode e : values())
			map.put(e.value, e);
	}

	private eProcessorForOpcode(int value, String strValue) {
		this.value = value;
		this.strValue = strValue;
	}

	public static eProcessorForOpcode forValue(int value) {
		return map.get(value);
	}

	public static eProcessorForOpcode forValue(byte value) {
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

