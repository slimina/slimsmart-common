package cn.slimsmart.common.util.http;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderUtil {
	
	private HeaderUtil(){}
	
	//-- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	
  	private static final String CACHE_CONTROL = "Cache-Control";
  	private static final String CONTENT_DISPOSITION = "Content-Disposition";
  	private static final String EXPIRES = "Expires";
  	private static final String IF_MODIFIED_SINCE = "If-Modified-Since";
  	private static final String LAST_MODIFIED = "Last-Modified";
  	private static final String PRAGMA = "Pragma";

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
}
