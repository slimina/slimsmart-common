package cn.slimsmart.common.util.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

	private HtmlUtil() {
	}

	public static String encode(String str) {
		String s = "";
		if (str.length() == 0)
			return "";
		s = str.replaceAll("&", "&amp;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("\'", "'");
		s = s.replaceAll("\"", "&quot;");
		return s;
	}

	public static String decode(String str) {
		String s = "";
		if (str.length() == 0)
			return "";
		s = str.replaceAll("&amp;", "&");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("'", "\'");
		s = s.replaceAll("&quot;", "\"");
		return s;
	}

	public static String replaceHtml(String html) {
		String regEx = "<.+?>"; // 表示标签
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

}
