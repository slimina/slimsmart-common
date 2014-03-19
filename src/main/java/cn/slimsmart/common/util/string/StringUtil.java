package cn.slimsmart.common.util.string;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串操作工具类
 * 
 * @author Zhu.TW
 * 
 */
public class StringUtil extends StringUtils {
	
	private StringUtil(){}

	/**
	 * 获取字符串字节数
	 * 
	 * @param str
	 * @return
	 */
	public static long byteLength(String str) {
		int length = str.length();
		for (int i = 0, l = str.length(); i < l; i++) {
			if (str.charAt(i) > 127) {
				length = length + 2;
			}
		}
		return length;
	}

	/**
	 * 去除字符串两边空白
	 * 
	 * @param str
	 * @return
	 */
	public static String trimString(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.trim();
		}
		return str;
	}

	/**
	 * 格式化字符串 如：format("{0}，{1}","你好",123) ==》"你好，123"
	 * 
	 * @param desc
	 * @param str
	 * @return
	 */
	public static String format(String desc, Object... str) {
		if (desc == null || str == null || str.length == 0) {
			return desc;
		}
		int length = str.length;
		for (int i = 0; i < length; i++) {
			desc = desc.replace("{" + i + "}", str[i].toString());
		}
		return desc;
	}

	/**
	 * string to Unicode
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		char[] arChar = str.toCharArray();
		int iValue = 0;
		String uStr = "";
		for (int i = 0; i < arChar.length; i++) {
			iValue = (int) str.charAt(i);
			if (iValue <= 256) {
				uStr += "\\u00" + Integer.toHexString(iValue);
			} else {
				uStr += "\\u" + Integer.toHexString(iValue);
			}
		}
		return uStr;
	}

	/**
	 * 首字母转小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowercaseFirstLetter(String str) {
		char c = str.charAt(0);
		String temp = new String(c + "");
		return str.replaceFirst(temp, StringUtils.lowerCase(temp));
	}

	 /**
     * 
     * 字符串中存在星号（表示多个字符）匹配
     * @param pattern  包含星号的字符串
     * @param str  要匹配的字符串
     * @return
     */
	public static boolean wildcardStarMatch(String pattern, String str) {
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0, patternLength = pattern.length(); patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardStarMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
	
	/**
	 * 对字符串进行编码，可以在所有的计算机上读取该字符串。
	 * * @ - _ + . / 。其他所有的字符都会被转义序列替换
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 对字符串进行解码
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
}
