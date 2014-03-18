package cn.slimsmart.common.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
	
	private HttpUtil(){}
	
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
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), HeaderUtil.getDefultEnCoding()));
		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}
		bufferedReader.close();
		return result;
	}
}
