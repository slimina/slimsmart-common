package cn.slimsmart.common.base.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;

public class Struts2Utils {

	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";

	private static final String HEADER_NOCACHE = "no-cache";

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final boolean DEFAULT_NOCACHE = true;

	private static ObjectMapper mapper = new ObjectMapper();

	// -- 取得Request/Response/Session的简化函数 --//
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession(boolean isNew) {
		return ServletActionContext.getRequest().getSession(isNew);
	}

	/**
	 * 取得HttpSession中Attribute的简化函数.
	 */
	public static Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

	public static void setSessionAttribute(String name, Object attribute) {
		HttpSession session = getSession(true);
		session.setAttribute(name, attribute);
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static void setRequestAttribute(String name, Object attribute) {
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute(name, attribute);
	}

	/**
	 * 取得HttpRequest中Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = initResponseHeader(contentType, headers);
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
	public static void renderText(final String text, final String... headers) {
		render(ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final String... headers) {
		render(ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 将对象转换为json
	 * 
	 * @param Object
	 *            obj对象
	 * @return jsonString json字符串.
	 * @see #render(Object)
	 */
	public static String objectToJson(final Object obj) {
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data, final String... headers) {
		HttpServletResponse response = initResponseHeader(ServletUtils.JSON_TYPE, headers);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 获取客户端IP
	 * 
	 * @return
	 */
	public static String getRequestIp() {
		HttpServletRequest request = getRequest();
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
	 * 获取请求路径
	 * 
	 * @return
	 */
	public static String getServletPath() {
		return getRequest().getServletPath();
	}

	/**
	 * 直接输出excel
	 * 
	 * @author cuizhouwei
	 * @dateTime 2012-8-16 下午4:01:22
	 * @param bytes
	 * @param headers
	 */
	public static void renderExcel(final byte[] bytes, final String filename) {
		HttpServletResponse response = initResponseHeader(ServletUtils.EXCEL_TYPE);
		ServletUtils.setFileDownloadHeader(response, filename);
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
	public static void renderJsonp(final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();
		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句, 如callback197("{html:'Hello World!!!'}");
		render(ServletUtils.JS_TYPE, result, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");
			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setNoCacheHeader(response);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String[]> getAttributeAndValues() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameterMap();
	}

	/**
	 * 将request中的参数解析到Map中
	 * 
	 * @return
	 * @throws java.text.ParseException
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, Object> resolveRequestParametersToFilterMap() {
		Map<String, Object> filters = new HashMap<String, Object>();
		String key;
		String value;
		HttpServletRequest request = Struts2Utils.getRequest();
		@SuppressWarnings("unchecked")
		Enumeration<String> enu = request.getParameterNames();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			value = request.getParameter(key);
			if (StringUtils.isEmpty(value))
				continue;
			if (key.endsWith("Time")) // 专门preparedstatement处理时间类型参数
			{
				try {
					Date date = dateFormat.parse(value);
					if (key.toLowerCase().contains("end")) {
						date.setDate(date.getDate() + 1);
					}
					filters.put(key, date);
				} catch (ParseException e) {
					throw new RuntimeException(key + "时间格式不符合YYYY-MM-DD的格式");
				}
			} else {
				filters.put(key, value);
			}
		}
		return filters;
	}
}
