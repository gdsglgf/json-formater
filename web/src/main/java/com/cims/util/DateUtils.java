package com.cims.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String currentDateString() {
		Calendar calendar = Calendar.getInstance();
		String current = formatter.format(calendar.getTime());
		return current;
	}
	
	public static Date currentDate() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar.getTime();
	}
	
	public static String format(Date date) {
		return formatter.format(date);
	}
}
