package com.vitzro.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class CrcHelper {
	
	 public static int getCRC16(byte[] data)
	  {
	    int resultVal = 65535;
	    
	    byte[] arrayOfByte = data;int j = data.length;
	    for (int i = 0; i < j; i++)
	    {
	      byte b = arrayOfByte[i];
	      for (int i1 = 0; i1 < 8; i1++)
	      {
	        boolean bit = (b >> 7 - i1 & 0x1) == 1;
	        boolean c15 = (resultVal >> 15 & 0x1) == 1;
	        resultVal <<= 1;
	        if ((c15 ^ bit)) {
	          resultVal ^= 0x1021;
	        }
	      }
	    }
	    resultVal &= 0xFFFF;
	    
	    return resultVal;
	  }
	  
	  public static byte[] getCRC16ToByteArray(byte[] data)
	  {
	    int crc = getCRC16(data);
	    
	    byte hiByte = (byte)((crc & 0xFF00) >>> 8);
	    byte loByte = (byte)(crc & 0xFF);
	    
	    return new byte[] { hiByte, loByte };
	  }
	  
	  public static boolean checkCrc16(byte[] data, int recvCrc)
	  {
	    int checkCrc = getCRC16(data);
	    
	    return recvCrc == checkCrc;
	  }
	  
	  public static boolean checkCrc16(byte[] data, byte[] recvCrc)
	  {
	    byte[] checkCrc = getCRC16ToByteArray(data);
	    
	    return (recvCrc[0] == checkCrc[0]) && (recvCrc[1] == checkCrc[1]);
	  }

}
