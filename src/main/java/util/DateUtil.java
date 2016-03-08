package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具 Calendar的月份是从0开始的
 * 
 * @author haixiao
 */
@SuppressWarnings("all")
public class DateUtil {
	
	public static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD="yyyy-MM-dd";
	
	/**
	 * 获取某时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
	 * 
	 * @param date：日期
	 * @return
	 */
	public static String getWeekCS(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String[] week = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return week[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取当前时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
	 * 
	 * @return
	 */
	public static String getWeekCSToday() {
		return getWeekCS(new Date());
	}

	/**
	 * 用当前日期作为文件名，一般不会重名取到的值是从当前时间的字符串格式，带有微秒，建议作为记录id
	 * 
	 * @return
	 */
	public static String getTimeStamp(String strFormat) {
		Date currentTime = new Date();
		return dateToString(currentTime, strFormat);
	}

	/**
	 * 用当前日期作为文件名，一般不会重名取到的值是从1970年1月1日00:00:00开始算起所经过的微秒数
	 * 
	 * @return
	 */
	public static String getFileName() {
		Calendar calendar = Calendar.getInstance();
		String filename = String.valueOf(calendar.getTimeInMillis());
		return filename;
	}

	/**
	 * 获取两个日期之间所差的天数
	 * 
	 * @param from：开始日期
	 * @param to：结束日期
	 * @return：所差的天数(非负整数)
	 */
	public static int dateNum(Date from, Date to) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date fromDate = calendar.getTime();

		calendar.setTime(to);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date toDate = calendar.getTime();
		int diff = Math.abs((int) ((fromDate.getTime() - toDate.getTime()) / (24 * 3600 * 1000)));
		return diff;
	}

	/**
	 * 获取两个日期之间所差的周数
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int weekNum(Date from, Date to) {

		return 0;
	}

	/**
	 * 获取date前或后nDay天的日期
	 * 
	 * @param date：开始日期
	 * @param nDay：天数
	 * @param type：正为后nDay天的日期，否则为前nDay天的日期。
	 * @return
	 */
	private static Date getDate(Date date, int nDay, int type) {
		long millisecond = date.getTime(); // 从1970年1月1日00:00:00开始算起所经过的微秒数
		long msel = nDay * 24 * 3600 * 1000l; // 获取nDay天总的毫秒数
		millisecond = millisecond + ((type > 0) ? msel : (-msel));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millisecond);
		return calendar.getTime();
	}

	/**
	 * 获取n天后的日期
	 * 
	 * @param date
	 * @param nDay
	 * @return
	 */
	public static Date dateAfterNDate(Date date, int nDay) {
		return getDate(date, nDay, 1);
	}

	/**
	 * 获取n天后的日期
	 * 
	 * @param strDate
	 * @param nDay
	 * @return
	 */
	public static Date dateAfterNDate(String strDate, int nDay) {
		Date date = stringToDate(strDate, "yyyy-MM-dd HH:mm:ss");
		return dateAfterNDate(date, nDay);
	}

	/**
	 * 获取n天前的日期
	 * 
	 * @param date
	 * @param nDay
	 * @return
	 */
	public static Date dateBeforeNDate(Date date, int nDay) {
		return getDate(date, nDay, -1);
	}

	/**
	 * 获取n天前的日期
	 * 
	 * @param strDate
	 * @param nDay
	 * @return
	 */
	public static Date dateBeforeNDate(String strDate, int nDay) {
		Date date = stringToDate(strDate, "yyyy-MM-dd HH:mm:ss");
		return dateBeforeNDate(date, nDay);
	}

	/**
	 * 将日期转化为字符串的形式
	 * 
	 * @param date
	 * @param strFormat
	 * @return
	 */
	public static String dateToString(Date date, String strFormat) {
		if (strFormat == null) {
			strFormat = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		return format.format(date);
	}

	/**
	 * 将字符串转化为Date类型。如果该字符串无法转化为Date类型的数据，则返回null。
	 * 
	 * @param strDate
	 * @param strFormat
	 *            strDate和strFormat的格式要一样。即如果strDate="20061112"，则strFormat="yyyyMMdd"
	 * @return
	 */
	public static Date stringToDate(String strDate, String strFormat) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			date = sdf.parse(strDate);
			if (!sdf.format(date).equals(strDate)) {
				date = null;
			}
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	/**
	 * 获取n月之前或之后的日期
	 * 
	 * @param date
	 * @param nMonth
	 * @param type(只能为-1或1)
	 * @return
	 */
	public static Date getDateMonth(Date date, int nMonth, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int nYear = nMonth / 12;
		int month = nMonth % 12;
		calendar.add(Calendar.YEAR, nYear * type);
		Date desDate = calendar.getTime();
		calendar.add(Calendar.MONTH, month * type);
		if (type < 0) {
			while (!calendar.getTime().before(desDate)) {
				calendar.add(Calendar.YEAR, type);
			}
		} else {
			while (!calendar.getTime().after(desDate)) {
				calendar.add(Calendar.YEAR, type);
			}
		}
		return calendar.getTime();
	}

	/**
	 * 获取当前时间所在的周的最后一天（周一为第一天）
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_WEEK);
		index = (index == 1) ? (index + 7) : index;
		date = DateUtil.dateAfterNDate(date, 8 - index);
		return date;
	}

	/**
	 * 获取当前时间所在的周的第一天（周一为第一天）
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_WEEK);
		index = (index == 1) ? (index + 7) : index;
		date = DateUtil.dateBeforeNDate(date, index - 2);
		return date;
	}

	/**
	 * 获取date所在的月份的最后一天 方法是获取下个月的第一天，然后获取前一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		date = calendar.getTime();
		date = DateUtil.getDateMonth(date, 1, 1);
		date = DateUtil.dateBeforeNDate(date, 1);
		return date;
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 获取季度的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.MONTH);
		index = index / 3;
		Date[] dates = new Date[4];
		calendar.set(calendar.get(Calendar.YEAR), 2, 31);
		dates[0] = calendar.getTime();
		calendar.set(calendar.get(Calendar.YEAR), 5, 30);
		dates[1] = calendar.getTime();
		calendar.set(calendar.get(Calendar.YEAR), 8, 30);
		dates[2] = calendar.getTime();
		calendar.set(calendar.get(Calendar.YEAR), 11, 31);
		dates[3] = calendar.getTime();
		return dates[index];
	}

	/**
	 * 创建日期date
	 * 
	 * @param year：年
	 * @param month：月
	 * @param day：日
	 * @return
	 */
	public static Date createDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	public static Date getDateByPattern(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = null;
		try {
			curDate = format.parse(format.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDate;
	}

	public static Date getBeginDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getToDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 根据指定日期参数，判断该日期是否在某段时间内
	 * 
	 * @param curDate
	 *            当前时间
	 * @param begDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static boolean isInDate(Date curDate, Date begDate, Date endDate) {
		boolean isIn = false;
		//Date b = new Date(begDate.getYear(), begDate.getMonth(), begDate.getDate() - 1);
		//Date e = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate() + 1);
		if (null != begDate && null != endDate && curDate.after(begDate) && curDate.before(endDate)) {
			isIn = true;
		}

		return isIn;
	}

	public static boolean beforeDate(Date d1, Date d2) {
		if (d1.after(d2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取某个日期是今年的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 去掉字符串中的时分秒
	 * @param date
	 * @return
	 */
	public static String strTostrForDate(String date) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = new Date();
		try{
			newDate = dateFormat.parse(date);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(newDate);
	}
	
	/**
	 * 返回两个日期时间差 hour+"小时"+min+"分"+s+"秒"
	 * @param strBegDate 长格式  
	 * @param strEndDate 长格式
	 * @return
	 * @throws ParseException
	 */
	public static String getDifferentTime(String strBegDate, String strEndDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       java.util.Date now = df.parse(strEndDate);
	       java.util.Date date=df.parse(strBegDate);
	       long l=now.getTime()-date.getTime();
	       long day=l/(24*60*60*1000);
	       long hour=(l/(60*60*1000)-day*24);
	       long min=((l/(60*1000))-day*24*60-hour*60);
	       long s=(l/1000-day*24*60*60-hour*60*60-min*60);
	       //System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
	       return hour+"小时"+min+"分"+s+"秒"; 
	}
	
	/**
	 * 返回两个日期时间差 hour+":"+min+":"+s
	 * @param strBegDate 长格式  
	 * @param strEndDate 长格式
	 * @return
	 * @throws ParseException
	 */
	public static String getDifferentTimes(java.util.Date begDate, java.util.Date endDate) throws ParseException {
		long l = endDate.getTime() - begDate.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		hour = hour+ day * 24;
		return (hour<10 ? "0"+ hour : hour) + ":" + (min<10 ? "0"+ min : min) + ":" + (s<10 ? "0"+ s : s);
	}
	
	public static void main(String[] args) {
		//System.out.println(getWeekOfYear(new Date()));
		try {
			//String s = getDifferentTime("2010-12-01 10:12:33", "2010-12-01 20:00:00");
			Date date=getFirstDateOfMonth(new Date());
			System.out.println(date);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}