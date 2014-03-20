package cn.slimsmart.common.util;

import java.security.SecureRandom;

import cn.slimsmart.common.util.encode.EncodeUtil;

/**
 * 获取UUID字符串
 * @author Zhu.TW
 *
 */
public class UUID {
	
	private static SecureRandom random = new SecureRandom();
	
	private UUID(){}

	public static String getUUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeUtil.encodeBase62(randomBytes);
	}
}
