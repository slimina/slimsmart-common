package cn.slimsmart.common.util;

/**
 * 获取UUID字符串
 * @author Zhu.TW
 *
 */
public class UUID {

	public static String getUUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
