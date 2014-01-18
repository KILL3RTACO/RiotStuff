package com.kill3rtaco.riotstuff;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

public class Util {
	
	public static String[] removeArgs(String[] array, int startIndex) {
		if(array.length == 0)
			return array;
		if(array.length < startIndex)
			return new String[0];
		
		String[] newSplit = new String[array.length - startIndex];
		System.arraycopy(array, startIndex, newSplit, 0, array.length - startIndex);
		return newSplit;
	}
	
	public static String[] removeFirstArg(String[] array) {
		return removeArgs(array, 1);
	}
	
	public static String getFriendlyDate(long epoch) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(epoch);
		String weekday, month, ap;
		int day, year, hour, min;
		int style = Calendar.SHORT;
		Locale locale = Locale.getDefault();
		month = calendar.getDisplayName(Calendar.MONTH, style, locale);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		weekday = calendar.getDisplayName(Calendar.DAY_OF_WEEK, style, locale);
		year = calendar.get(Calendar.YEAR);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour > 12)
			hour -= 12;
		else if(hour == 0)
			hour = 12;
		min = calendar.get(Calendar.MINUTE);
		ap = calendar.getDisplayName(Calendar.AM_PM, style, locale);
		
		return weekday + ", " + month + " " + day + ", " + year + " - " + hour + ":" + min + ap;
	}
	
	public static String getTime(long seconds) {
		String str = "";
		int min = 60;
		int hour = min * 60;
		if(seconds > hour) { //1 hour
			str += (seconds / (hour)) + "h";
		}
		if(seconds % (hour) > min) {
			str += (seconds % (hour)) / min + "m";
		}
		if(seconds % hour % min > 0) {
			str += seconds % hour % min + "s";
		}
		return str;
	}
	
	public static String getFriendlyNum(long num) {
		DecimalFormat format = new DecimalFormat("#.#");
		if(num >= 1000000) {
			return format.format(num / 1000000D) + "m";
		} else if(num >= 1000) {
			return format.format(num / 1000D) + "k";
		}
		return num + "";
	}
	
}
