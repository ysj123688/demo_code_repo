package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateHelper {
	public static String DefaultDateFormat = "yyyy-MM-dd";

	public static String ShortDateFormat = "yyyy-MM-dd";

	public static String LongDateFormat24 = "yyyy-MM-dd HH:mm:ss";

	public static String LongDateFormat12 = "yyyy-MM-dd hh:mm:ss";

	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, null);
	}

	public static Date stringToDate(String dateString, String dateFormat) {
		if (StringUtil.isEmpty(dateFormat)) {
			dateFormat = DefaultDateFormat;
		}
		DateFormat dd = new SimpleDateFormat(dateFormat);
		Date newDate = null;
		try {
			newDate = dd.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public static String getCurrentDataString() {
		SimpleDateFormat dateFm = new SimpleDateFormat(LongDateFormat24);
		String dateTime = dateFm.format(new java.util.Date());
		return dateTime;
	}

	public static String getCurrentDataString(Date date) {
		SimpleDateFormat dateFm = new SimpleDateFormat(LongDateFormat24);
		String dateTime = dateFm.format(new java.util.Date());
		return dateTime;
	}

	public static String getDataString(String dateFormat) {
		SimpleDateFormat dateFm = new SimpleDateFormat(dateFormat);
		String dateTime = dateFm.format(new java.util.Date());
		return dateTime;
	}

	public static Date getMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		return calendar.getTime();
	}

	public static Date getMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return calendar.getTime();
	}
}
