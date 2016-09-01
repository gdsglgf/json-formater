package com.cims.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Calendar和Date的辅助类.
 * @author Luo Guofu
 */
public class DateUtils {
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 返回当前时间的字符串格式.
	 * @return 当前时间的字符串格式.
	 */
	public static String currentDateString() {
		Calendar calendar = Calendar.getInstance();
		String current = formatter.format(calendar.getTime());
		return current;
	}
	
	/**
	 * 返回当前时间对象
	 * @return 当前时间对象
	 */
	public static Date currentDate() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar.getTime();
	}
	
	/**
	 * 将时间对象格式化成字符串.
	 * @param date - 时间对象
	 * @return 格式化后的字符串.
	 */
	public static String format(Date date) {
		return formatter.format(date);
	}
}
