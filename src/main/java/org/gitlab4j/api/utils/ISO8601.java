package org.gitlab4j.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.DatatypeConverter;

/**
 * This class provides utility methods for parsing and formatting ISO8601 formatted dates.
 */
public class ISO8601 {

    public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String SPACEY_PATTERN = "yyyy-MM-dd HH:mm:ss Z";
    public static final String PATTERN_MSEC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String OUTPUT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String OUTPUT_MSEC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String UTC_PATTERN = "yyyy-MM-dd HH:mm:ss 'UTC'";

    private static final String PATTERN_REGEX = "\\d\\d\\d\\d-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d[-+]\\d\\d\\d\\d";
    private static final String SPACEY_PATTERN_REGEX = "\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d [-+]\\d\\d\\d\\d";

    // Set up ThreadLocal storage to save a thread local SimpleDateFormat keyed with the format string
    private static final class SafeDateFormatter {

        private static final ThreadLocal<Map<String, SimpleDateFormat>> safeFormats = new ThreadLocal<Map<String, SimpleDateFormat>>() {

            @Override
            public Map<String, SimpleDateFormat> initialValue() {
                return (new ConcurrentHashMap<>());
            }
        };

        private static SimpleDateFormat getDateFormat(String formatSpec) {

            Map<String, SimpleDateFormat> formatMap = safeFormats.get();
            SimpleDateFormat format = formatMap.get(formatSpec);
            if (format == null) {
                format = new SimpleDateFormat(formatSpec);
                format.setLenient(true);
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                formatMap.put(formatSpec, format);
            }

            return (format);
        }
    }

    /**
     * Get a ISO8601 formatted string for the current date and time.
     *
     * @return a ISO8601 formatted string for the current date and time
     */
    public static String getTimestamp() {
        return (SafeDateFormatter.getDateFormat(PATTERN).format(new Date()));
    }

    /**
     * Get a ISO8601formatted string for the current date and time.
     *
     * @param withMsec flag indicating whether to include milliseconds
     * @return a ISO8601 formatted string for the current date and time
     */
    public static String getTimestamp(boolean withMsec) {
        return (withMsec ? SafeDateFormatter.getDateFormat(PATTERN_MSEC).format(new Date()) :
            SafeDateFormatter.getDateFormat(PATTERN).format(new Date()));
    }

    /**
     * Get a ISO8601 formatted string for the provided Calendar instance.
     * 
     * @param cal the Calendar instance to get the ISO8601 formatted string for
     * @return a ISO8601 formatted string for the provided Calendar instance, or null if call is null
     */
    public static String toString(Calendar cal) {

        if (cal == null) {
            return (null);
        }

        return (toString(cal.getTime()));
    }

    /**
     * Get a ISO8601 formatted string for the provided Date instance.
     * 
     * @param date the Date instance to get the ISO8601 formatted string for
     * @param withMsec flag indicating whether to include milliseconds
     * @return a ISO8601 formatted string for the provided Date instance, or null if date is null
     */
    public static String toString(Date date, boolean withMsec) {

        if (date == null) {
            return (null);
        }

        long time = date.getTime();
        return (withMsec && time % 1000 != 0 ?
                SafeDateFormatter.getDateFormat(OUTPUT_MSEC_PATTERN).format(date) :
                SafeDateFormatter.getDateFormat(OUTPUT_PATTERN).format(date));
    }

    /**
     * Get a ISO8601 formatted string for the provided Date instance.
     * 
     * @param date the Date instance to get the ISO8601 formatted string for
     * @return a ISO8601 formatted string for the provided Date instance, or null if date is null
     */
    public static String toString(Date date) {
        return (toString(date, true));
    }

    /**
     * Parses an ISO8601 formatted string a returns a Date instance.
     * 
     * @param dateTimeString the ISO8601 formatted string
     * @return a Date instance for the ISO8601 formatted string
     * @throws ParseException if the provided string is not in the proper format
     */
    public static Date toDate(String dateTimeString) throws ParseException {

        if (dateTimeString == null) {
            return (null);
        }

        dateTimeString = dateTimeString.trim();
        if (dateTimeString.endsWith("UTC")) {
            return (SafeDateFormatter.getDateFormat(UTC_PATTERN).parse(dateTimeString));
        } else {
            try {
                Calendar cal = DatatypeConverter.parseDateTime(dateTimeString);
                return (cal.getTime());
            } catch (Exception e) {
                if (dateTimeString.matches(PATTERN_REGEX)) {
                    // Try using the ISO8601 format
                    return (SafeDateFormatter.getDateFormat(PATTERN).parse(dateTimeString));
                } else if (dateTimeString.matches(SPACEY_PATTERN_REGEX)) {
                    // Try using the invalid ISO8601 format with spaces, GitLab sometimes uses this
                    return (SafeDateFormatter.getDateFormat(SPACEY_PATTERN).parse(dateTimeString));
                } else {
                    throw e;
                }
            }
        }
    }

    /**
     * Parses an ISO8601 formatted string a returns a Calendar instance.
     * 
     * @param dateTimeString the ISO8601 formatted string
     * @return a Calendar instance for the ISO8601 formatted string
     * @throws ParseException if the provided string is not in the proper format
     */
    public static Calendar toCalendar(String dateTimeString) throws ParseException {

        Date date = toDate(dateTimeString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal);
    }
}
