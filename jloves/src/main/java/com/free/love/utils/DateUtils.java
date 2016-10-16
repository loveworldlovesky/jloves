package com.free.love.utils;

import java.util.Calendar;

public class DateUtils {
	/**
	 * 
	 * @param time
	 * @return 年-月-日 时：分：秒
	 */
	public static String getDateByMillis(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return year + "-" + month + "-" + day + " " + hour + ":"+minute + ":" + second;
	}

}
