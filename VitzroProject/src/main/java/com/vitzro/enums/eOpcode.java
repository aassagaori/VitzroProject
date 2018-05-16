package com.vitzro.enums;

import java.util.HashMap;
import java.util.Map;

import com.vitzro.processor.CollectProcessor;
import com.vitzro.util.TypeHelper;


public enum eOpcode {
  
	_Collect(0x01, CollectProcessor.class.getName())	

	;	
	
	   
	   
	private final int value;
	private final String strValue;

	private final static Map<Integer, eOpcode> map = new HashMap<Integer, eOpcode>();
	static {
		
		for (eOpcode e : values())
			map.put(e.value, e);
	}

	private eOpcode(int value, String strValue) {
		this.value = value;
		this.strValue = strValue;
	}

	public static eOpcode forValue(int value) {
		return map.get(value);
	}

	public static eOpcode forValue(byte value) {
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

