package com.messners.gitlab.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ISO8601 {
	public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String OUTPUT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String ALTERNATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final SimpleDateFormat iso8601Format;
	private static final SimpleDateFormat iso8601OutputFormat;
	private static final SimpleDateFormat iso8601AlternateFormat;
	static { 
		iso8601Format = new SimpleDateFormat(PATTERN);
		iso8601Format.setLenient(true);
		iso8601Format.setTimeZone(TimeZone.getTimeZone("GMT"));  
		iso8601OutputFormat = new SimpleDateFormat(OUTPUT_PATTERN);
		iso8601OutputFormat.setLenient(true);
		iso8601OutputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  
		iso8601AlternateFormat = new SimpleDateFormat(ALTERNATE_PATTERN);
		iso8601AlternateFormat.setLenient(true);
		iso8601AlternateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  
	}
	
	public static String getTimestamp () {
		return iso8601Format.format(new Date());
	}

	public static String toString (Calendar cal) {
		
		if (cal == null) {
			return (null);
		}

		return toString(cal.getTime());
	}

	public static synchronized String toString (Date date) {
		
		if (date == null) {
			return (null);
		}
		
		return iso8601OutputFormat.format(date);
	}
	
	public static Date toDate (String dateTimeString) throws ParseException {
		
		if (dateTimeString == null) {
			return (null);
		}
		
		dateTimeString = dateTimeString.trim();

		SimpleDateFormat fmt;
		if (dateTimeString.length() > 10) {
			fmt = (dateTimeString.charAt(10) == 'T' ? 
					(dateTimeString.endsWith("Z") ? iso8601OutputFormat : iso8601Format) : iso8601AlternateFormat);
		} else {
			fmt = iso8601Format;
		}

		synchronized (fmt) {
			return (fmt.parse(dateTimeString));
		}		
	}

	public static Calendar toCalendar (String dateTimeString) throws ParseException {

		Date date = toDate(dateTimeString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal);
	}
}
