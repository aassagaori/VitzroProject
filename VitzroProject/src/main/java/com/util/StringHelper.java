package com.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class StringHelper {

	/**
	 * 두개의 문자열 중 작은 길이의 문자열 기준으로 문자열 비교
	 * @return the value 0 if the argument string is equal to this string; a value less than 0 if this string is lexicographically less than the string argument; and a value greater than 0 if this string is lexicographically greater than the string argument.
	 */
	public static int compareWithLength(String sourceString, String targetString) {
		int minLength = Math.min(sourceString.length(), targetString.length());

		return sourceString.substring(0, minLength).compareTo(targetString.substring(0, minLength));
	}

	public static int compareWithLength(String sourceString, String targetString, int length) {

		return sourceString.substring(0, length).compareTo(targetString.substring(0, length));
	}
	
	
    // e.printStackTrace() to String 
    public static String makeStackTrace(Throwable t){
    	
    	if(t == null) return "";
    	
    	try{
    		ByteArrayOutputStream bout = new ByteArrayOutputStream();
    		t.printStackTrace(new PrintStream(bout));
    		bout.flush();
    		String error = new String(bout.toByteArray());

    		return error;
    	}catch(Exception ex){
    		return "";
    	}
    }

}