package cn.slimsmart.common.annotation;

/**
 * log4j 输出定义   在拦截器中加入，log4j配置中取出打印
 * MDC.put(MDCKey.USER_NAME.toString(), userName.toString());
 * @author Zhu.TW
 */
public enum MDCKey {

	USER_NAME("userName");

	private String key;

	public String toString() {
		return key;
	}

	private MDCKey(String key) {
		this.key = key;
	}

}
