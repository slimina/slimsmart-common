package cn.slimsmart.common.random;

import java.security.SecureRandom;

import cn.slimsmart.common.random.support.RandomGeneratorTool;
import cn.slimsmart.common.util.encode.EncodeUtil;

public final class RandomString extends RandomGeneratorTool{

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeUtil.encodeBase62(randomBytes);
	}
	
}
