package com.vitzro.util;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;

public abstract class DateHelper {

	public static String convertCustomDateTime(String strDate) {
		String convertDate = strDate;
		if (convertDate.lastIndexOf(".") != -1)
			convertDate = convertDate.substring(0, convertDate.lastIndexOf("."));
		return convertDate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
	}
		
	
	public static String strDate() {
		FastDateFormat f = FastDateFormat.getInstance("yyyyMMddHHmmss", new Locale("ko_KR"));
		return f.format(new Date());
	}
	public static String strHourMinute() {
		FastDateFormat f = FastDateFormat.getInstance("HHmm", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strYear() {
		FastDateFormat f = FastDateFormat.getInstance("yyyy", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strMonth() {
		FastDateFormat f = FastDateFormat.getInstance("MM", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strDay() {
		FastDateFormat f = FastDateFormat.getInstance("dd", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strHour() {
		FastDateFormat f = FastDateFormat.getInstance("HH", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strMinute() {
		FastDateFormat f = FastDateFormat.getInstance("mm", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strSecond() {
		FastDateFormat f = FastDateFormat.getInstance("ss", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static boolean isBetweenCurrentHourMinutes(String sHHmm, String eHHmm) {
		
		FastDateFormat f = FastDateFormat.getInstance("HHmm", new Locale("ko_KR"));		
		String current = f.format(new Date());
		int cur = Integer.parseInt(current);
		int st = Integer.parseInt(sHHmm);
		int ed = Integer.parseInt(eHHmm);
		if(st<ed) {
			if(st<cur && ed>cur) {return true;}		
			else return false;
		}else {
			if(ed<cur && st>cur) return false;			
		}		
		return true;
	}

		
}
