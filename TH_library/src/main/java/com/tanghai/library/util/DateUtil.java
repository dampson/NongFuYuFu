package com.tanghai.library.util;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat") 
public class DateUtil {

	// 字符串格式
	public static String STRING_FORMAT = "yyyymmddhhmmss";

	// 短日期格式
	public static String DATE_FORMAT = "yyyy-MM-dd";

	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// 日期格式
	public static String TIME_FORMAT_M_D_H_M = "MM-dd HH:mm";

	// 长日期格式
	public static String TIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

	/**
	 * 当天日期前几天
	 */
	public static String getBeforeDate(int days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return df.format(calendar.getTime());
	}

	/**
	 * 当天日期后几天
	 */
	public static String getAfterDate(int days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return df.format(calendar.getTime());
	}

	/**
	 * 输入某年某月某日，判断这一天是这一年的第几天？
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDateDays(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		try {
			Date date = sdf.parse(date1);// 通过日期格式的parse()方法将字符串转换成日期
			Date dateBegin = sdf.parse(date2);
			long betweenTime = date.getTime() - dateBegin.getTime();
			betweenTime = betweenTime / 1000 / 60 / 60 / 24;
			return Integer.parseInt(String.valueOf(betweenTime));
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 根据传进来的参数格式化时间，并返回相应格式的时间
	 * 
	 * @param formate
	 *            想要的时间格式
	 * @return
	 */
	public static String getTimeByFormage(String formate) {
		// 使用默认
		if (null == formate || "".equals(formate)) {
			Logger.w("time formate is null, using " + STRING_FORMAT + " (default) instead of.");
			formate = STRING_FORMAT;
		}

		String finalTime = "";

		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(formate);
			Date date = new Date();
			finalTime = bartDateFormat.format(date);
		} catch (Exception e) {
			Logger.e("time formate error");
		}
		return finalTime;
	}

	/**
	 * 获取增加多少月的时间
	 * 
	 * @return addMonth - 增加多少月
	 */
	public static String getAddMonthDate(int addMonth, String formate) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, addMonth);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(formate);
		String finalTime = bartDateFormat.format(calendar.getTime());
		return finalTime;
	}

	/**
	 * 获取增加多少天的时间
	 * 
	 * @return addDay - 增加多少天
	 */
	public static String getAddDayDate(int addDay, String formate) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, addDay);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(formate);
		String finalTime = bartDateFormat.format(calendar.getTime());
		return finalTime;
	}

	/**
	 * 获取增加多少小时的时间
	 * 
	 * @return addDay - 增加多少消失
	 */
	public static String getAddHourDate(int addHour, String formate) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, addHour);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(formate);
		String finalTime = bartDateFormat.format(calendar.getTime());
		return finalTime;
	}

	/**
	 * 计算date之前n天的日期
	 */
	public static Date getDateBefore(Date date, int n) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - n);
		return now.getTime();
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
