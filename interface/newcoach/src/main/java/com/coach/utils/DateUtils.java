package com.coach.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date yyyyMMddToDate(String str){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date date = f.parse(str);
			return date;
		} catch(Throwable e){
			return null;
		}
	}
	
	public static String dateToyyyyMMdd(Date date){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String str = f.format(date);
			return str;
		} catch(Throwable e){
			return "";
		}
	}

	public static String dateToyyyyMMddHHmiss(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToyyyyMMddHHmi(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}

	public static Timestamp yyyyMMddHHmmssToTimestamp(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}
	
	public static Timestamp yyyyMMddHHmmToTimestamp(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}

	public static Date getEndDate() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		return cal.getTime();
	}
	
	public static String dateToyyyyMMddHHmissWithSeparator(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static Date getFirstDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
	}

	public static Date yyyyMMToDate(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}
	
	public static String dateToyyyyMM(Date date){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try{
			String str = f.format(date);
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static boolean inRange(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		String timeStr = dateFormat.format(now);
		if(timeStr.compareTo("080000") > 0 && timeStr.compareTo("220000") < 0){
			return true;
		} else {
			return false;
		}
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return day <= 0 ? 7 : day;
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setToFirstDay(calendar);
		return calendar.getTime();
	}
	
    private static final int FIRST_DAY = Calendar.MONDAY;
    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

	public static Date addDay(Date startDate, int i) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, i);
		return calendar.getTime();
	}
	public static Date getFirstDayOfMonth(Date date, int increament) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, increament);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		return calendar.getTime();
	}
	
	public static Integer getHour(Timestamp timestamp){
		Calendar time = Calendar.getInstance(); 
		time.setTimeInMillis(timestamp.getTime());
		return time.get(Calendar.HOUR_OF_DAY);
	}

	public static String dateToMMDD(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		String timeStr = dateFormat.format(date);
		return timeStr;
	}
}
