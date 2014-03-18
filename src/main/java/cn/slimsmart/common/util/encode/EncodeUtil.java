package cn.slimsmart.common.util.encode;

import java.security.MessageDigest;

/**
 * 常用编码工具类
 * 
 * @author Zhu.TW
 * 
 */
public class EncodeUtil {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final String DEFAULT_ALGORITHM = "MD5";

	/**
	 * 获取字符串的MD5值
	 * @param: @param source 加密源
	 * @return: String
	 */
	public static String getMD5(String source) {
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] sourceByte = source.getBytes(DEFAULT_URL_ENCODING);
			MessageDigest digest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
			digest.update(sourceByte);
			byte[] md = digest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 可逆的加密解密算法，一次加密，二次解密
	 * @param: @param source 加密源
	 * @return: String
	 */
	public static String  reverseEncode(String source){
		char[] arr = source.toCharArray();  
		  for (int i = 0,l=arr.length; i < l; i++) {  
			  arr[i] = (char) (arr[i] ^ 't');  
		  }  
		  return new String(arr);  
	}
}
