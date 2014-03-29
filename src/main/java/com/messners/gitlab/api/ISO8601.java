package com.messners.gitlab.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class provides utility methods for parsing and formatting ISO8601 formatted dates.
 * 
 * @author Greg Messner <greg@messners.com>
 */
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
	
	
	/**
	 * Get a ISO8601 formatted string for the current date and time.
	 *
	 * @return a ISO8601 formatted string for the current date and time
	 */
	public static String getTimestamp () {
		return iso8601Format.format(new Date());
	}

	
	/**
	 * Get a ISO8601 formatted string for the provided Calendar instance.
	 * 
	 * @param cal the Calendar instance to get the ISO8601 formatted string for 
	 * @return a ISO8601 formatted string for the provided Calendar instance, or null if call is null
	 */
	public static String toString (Calendar cal) {
		
		if (cal == null) {
			return (null);
		}

		return toString(cal.getTime());
	}

	
	/**
	 * Get a ISO8601 formatted string for the provided Date instance.
	 * 
	 * @param date the Date instance to get the ISO8601 formatted string for 
	 * @return a ISO8601 formatted string for the provided Date instance, or null if date is null
	 */
	public static synchronized String toString (Date date) {
		
		if (date == null) {
			return (null);
		}
		
		return iso8601OutputFormat.format(date);
	}
	
	
	/**
	 * Parses an ISO8601 formatted string a returns a Date instance.
	 * 
	 * @param dateTimeString the ISO8601 formatted string
	 * @return a Date instance for the ISO8601 formatted string
	 * @throws ParseException if the provided string is not in the proper format
	 */
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

	
	/**
	 * Parses an ISO8601 formatted string a returns a Calendar instance.
	 * 
	 * @param dateTimeString the ISO8601 formatted string
	 * @return a Calendar instance for the ISO8601 formatted string
	 * @throws ParseException if the provided string is not in the proper format
	 */
	public static Calendar toCalendar (String dateTimeString) throws ParseException {

		Date date = toDate(dateTimeString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal);
	}
}
