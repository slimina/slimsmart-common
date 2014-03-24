package cn.slimsmart.common.util.http;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.slimsmart.common.http.ResponseMsg;
import cn.slimsmart.common.util.common.string.StringUtil;
import cn.slimsmart.common.util.json.JsonUtil;

@SuppressWarnings("unchecked")
public class ServletUtil {
	
	//-- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	
	private ServletUtil(){}
	
	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final HttpServletResponse response,final String contentType, final String content, final String... headers) {
		HeaderUtil.initResponseHeader(response,contentType, headers);
		try {
			if (content != null) {
				response.getWriter().write(content);
			}
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response,final String text, final String... headers) {
		render(response,TEXT_TYPE, text, headers);
	}
	
	
	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response,final String html, final String... headers) {
		render(response,HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response,final String xml, final String... headers) {
		render(response,XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response,final String jsonString, final String... headers) {
		render(response,JSON_TYPE, jsonString, headers);
	}


	/**
	 * 直接输出JSON
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response,final Object data, final String... headers) {
		HeaderUtil.initResponseHeader(response,JSON_TYPE, headers);
		try {
			JsonUtil.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * 直接输出JSON
	 * @param response
	 * @param msg
	 * @param headers
	 */
	public static void renderJson(final HttpServletResponse response,final ResponseMsg msg, final String... headers) {
		renderJson(response,msg,headers);
	}
	/**
	 * 直接输出excel
	 * 
	 * @dateTime 2012-8-16 下午4:01:22
	 * @param bytes
	 * @param headers
	 */
	public static void renderExcel(final HttpServletResponse response,final byte[] bytes, final String filename) {
		HeaderUtil.initResponseHeader(response,EXCEL_TYPE);
		HeaderUtil.setFileDownloadHeader(response, filename);
		if (null != bytes) {
			try {
				response.getOutputStream().write(bytes);
				response.getOutputStream().flush();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}
	
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(final HttpServletResponse response,final String callbackName, final Object object, final String... headers) {
		String jsonString = JsonUtil.writeValueAsString(object);
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();
		render(response,JS_TYPE, result, headers);
	}
	
	/**
	 * 将request中的参数解析到Map中
	 * 
	 * @return
	 * @throws java.text.ParseException
	 * @throws Exception
	 */
	public static Map<String, Object> resolveRequestParametersToFilterMap(HttpServletRequest request) {
		Map<String, Object> filters = new HashMap<String, Object>();
		Enumeration<String> enu = request.getParameterNames();
		String key = null;
		String value = null;
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			value = request.getParameter(key);
			if (StringUtil.isEmpty(value)){
				continue;
			}
			filters.put(key, value);
		}
		return filters;
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
		String isAjax = request.getParameter("isAjax");
		if ((requestType != null && requestType.equals("XMLHttpRequest") || "true".equalsIgnoreCase(isAjax))) {
			return true;
		} else {
			return false;
		}
	}
}
