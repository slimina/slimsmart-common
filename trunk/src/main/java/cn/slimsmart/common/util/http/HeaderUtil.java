package cn.slimsmart.common.util.http;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.slimsmart.common.util.string.StringUtil;

public class HeaderUtil {
	
	private HeaderUtil(){}
	
  	private static final String CACHE_CONTROL = "Cache-Control";
  	private static final String CONTENT_DISPOSITION = "Content-Disposition";
  	private static final String EXPIRES = "Expires";
  	private static final String IF_MODIFIED_SINCE = "If-Modified-Since";
  	private static final String LAST_MODIFIED = "Last-Modified";
  	private static final String PRAGMA = "Pragma";
  	
 // -- header 常量定义 --//
 	private static final String HEADER_ENCODING = "encoding";

 	private static final String HEADER_NOCACHE = "no-cache";

 	private static final String DEFAULT_ENCODING = "UTF-8";

 	private static final boolean DEFAULT_NOCACHE = true;
  	
 	public static String getDefultEnCoding(){
 		return DEFAULT_ENCODING;
 	}
	/**
	 * 设置客户端缓存过期时间 的Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header, set a time after now.
		response.setHeader(CACHE_CONTROL, "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader(EXPIRES, 1L);
		response.addHeader(PRAGMA, "no-cache");
		// Http 1.1 header
		response.setHeader(CACHE_CONTROL, "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader(LAST_MODIFIED, lastModifiedDate);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader(IF_MODIFIED_SINCE);
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader(CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}
	
	/**
	 * 分析并设置contentType与headers.
	 */
	public static void initResponseHeader(HttpServletResponse response,final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtil.substringBefore(header, ":");
			String headerValue = StringUtil.substringAfter(header, ":");
			if (StringUtil.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtil.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "is not valid HeaderType.");
			}
		}
		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			setNoCacheHeader(response);
		}
	}
}
