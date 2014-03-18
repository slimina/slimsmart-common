package cn.slimsmart.common.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间操作工具类
 * @author Zhu.TW
 *
 */
public class DateUtil extends DateUtils{
	
	private final static SimpleDateFormat YMD= new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
}
