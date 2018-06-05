package com.vitzro.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public abstract class TypeHelper {
	
	private static int BYTE_MASKED_ARRAY[] = { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80 };

	public static int unsignedByteToInt(byte srcValue) {
		return srcValue & 0xFF;
	}

	public static int unsignedShortToInt(short srcValue) {
		return srcValue & 0xFFFF;
	}

	public static long intToUnsignedInt(int srcValue) {
		return srcValue & 0xFFFFFFFFL;
	}

	public static int unsignedIntToInt(long srcValue) {
		return (int)(srcValue & 0xFFFFFFFFL);
	}
	
	// long To Bit String
	public static String byteToBitStr(byte srcValue){
		return String.format("%8s", Long.toBinaryString(srcValue & 0xFF)).replace(' ', '0');
			}
	
	// long To Bit String
	public static String shortToBitStr(short srcValue){
		return String.format("%16s", Long.toBinaryString(srcValue & 0xFF)).replace(' ', '0');
			}

	// int To Bit String
	public static String intToBitStr(int srcValue){
		return String.format("%32s", Integer.toBinaryString(srcValue & 0xFF)).replace(' ', '0');
	}

	// long To Bit String
	public static String longToBitStr(long srcValue){
		return String.format("%64s", Long.toBinaryString(srcValue & 0xFF)).replace(' ', '0');
	}	
	
	public static int extractBits(byte srcValue, int beginBitDigit, int endBitDigit) {
		int maskedValue = 0;
		for (int idx = beginBitDigit ; idx <= endBitDigit ; ++idx) { maskedValue += BYTE_MASKED_ARRAY[idx]; }
		
		return (srcValue & maskedValue) >> beginBitDigit;
	}
	
	public static int extractBit(byte srcValue, int bitDigit) {
		return ((srcValue & BYTE_MASKED_ARRAY[bitDigit]) == BYTE_MASKED_ARRAY[bitDigit]) ? 1 : 0;
	}

	public static String byteArrayToHex(byte[] ba, String seperator) {
	    if (ba == null || ba.length == 0) {
	        return null;
	    }

	    StringBuffer sb = new StringBuffer(ba.length * 2);
	    String hexNumber;
	    for (int x = 0; x < ba.length; x++) {
	        hexNumber = "0" + Integer.toHexString(0xff & ba[x]).toUpperCase();

	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	        if (seperator != null && !seperator.isEmpty()) { sb.append(seperator); }
	    }
	    return sb.toString();
	}

	
	public static String byteArrayToHex(byte[] ba) {
		return byteArrayToHex(ba, null);
	}

	public static String byteToHex(byte b) {
        String hexNumber = "0" + Integer.toHexString(0xff & b).toUpperCase();
        return hexNumber.substring(hexNumber.length() - 2);
	}



}