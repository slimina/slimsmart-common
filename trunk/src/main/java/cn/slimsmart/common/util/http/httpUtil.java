package cn.slimsmart.common.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class httpUtil {
	
	private httpUtil(){}
	
	public static String buildUri(String schema, String host, String path, Map<String, String> param) {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(schema).append("://").append(host).append(path).append("?");
		int size = param.entrySet().size();
		int index = 0;
		for (Entry<String, String> entry : param.entrySet()) {
			uriBuilder.append(entry.getKey()).append("=").append(entry.getValue());
			index++;
			if (index <= size - 1) {
				uriBuilder.append("&");
			}
		}
		return uriBuilder.toString();
	}

	public static String getResponseBodyAsString(String uri) throws IOException {
		String line;
		String result = "";
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}
		bufferedReader.close();
		return result;
	}
	
	/**
	 * 获取客户端IP
	 * 
	 * @return
	 */
	public static String getRequestIp(final HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 判断是否为Ajax请求
	 * 
	 * @return 是true, 否false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
}
