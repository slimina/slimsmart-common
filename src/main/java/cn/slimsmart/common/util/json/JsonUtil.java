package cn.slimsmart.common.util.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * 封装jackson mapper相关便捷方法的工具类
 */
public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private JsonUtil(){}
	
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
	
	public static void writeValue(OutputStream out,Object value){
		try {
			mapper.writeValue(out, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public static void writeValue(Writer out,Object value){
		try {
			mapper.writeValue(out, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public static String writeValueAsString(Object value){
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
