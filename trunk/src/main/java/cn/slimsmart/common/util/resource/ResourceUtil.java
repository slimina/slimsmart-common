package cn.slimsmart.common.util.resource;

import java.util.ResourceBundle;

/**
 * 资源文件操作工具类
 * @author Zhu.TW
 *
 */
public class ResourceUtil {
	
	public static String getResources(String prop) {
		ResourceBundle rb = ResourceBundle.getBundle("application");
		String result = rb.getString(prop);
		return result;
	}
}
