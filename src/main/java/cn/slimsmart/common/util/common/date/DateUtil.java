package cn.slimsmart.common.util.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间操作工具类
 * @author Zhu.TW
 *
 */
public class DateUtil extends DateUtils{
	
	public final static String YYYY_MM_DD="yyyy-MM-dd";
	public final static String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
	
	public final static SimpleDateFormat YMD= new SimpleDateFormat(YYYY_MM_DD);
	public final static SimpleDateFormat YMD_HMS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

	private DateUtil(){}
	
	/**
	 * 获取当前日期的字符串  yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDateYMD() {
		return YMD.format(new Date());
	}
	
	/**
	 * 获取当前日期的字符串  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateYMDHMS() {
		return YMD_HMS.format(new Date());
	}
	
	public static String getFormatDateStr(Date date,String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static Date getFormatDate(String dateStr,String format) {
		Date date=null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
