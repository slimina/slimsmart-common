package cn.slimsmart.common.random.support;

public class RandomGeneratorTool {
	
	private static LongNumericGenerator numericGenerator = new DefaultLongNumericGenerator(0);
	private static  DefaultRandomStringGenerator randomStringGenerator = new DefaultRandomStringGenerator(20);
	private static  UniqueGenerator defaultUniqueGenerator = new DefaultUniqueGenerator();
	private static  UniqueGenerator samlUniqueGenerator = new SamlCompliantUniqueGenerator("",true);
	
	//服务重启从0开始
	public static long incrementLongNumer(){
		return numericGenerator.getNextLong();
	}
	
	/**
	 * 获取随机字符串 长度为20
	 */
	public static String randomString(){
		return randomStringGenerator.getNewString();
	}
	
	
	public static String defaultUniqueString(String prefix){
		return defaultUniqueGenerator.getRandomString(prefix);
	}
	
	public static String samlUniqueString(){
		return samlUniqueGenerator.getRandomString(null);
	}
}
