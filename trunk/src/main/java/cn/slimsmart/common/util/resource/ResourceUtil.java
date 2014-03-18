package cn.slimsmart.common.util.resource;

import java.util.ResourceBundle;

/**
 * 资源文件操作工具类
 * @author Zhu.TW
 *
 */
public class ResourceUtil {
	
	private ResourceUtil(){}
	
	public static String getResources(String prop) {
		return getResources("application");
	}
	
	public static String getResources(String baseName,String prop) {
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		String result = rb.getString(prop);
		return result;
	}
}
