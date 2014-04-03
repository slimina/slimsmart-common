package cn.slimsmart.common.random;

import java.security.SecureRandom;

public final class RandomNumber {
	
	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
}
