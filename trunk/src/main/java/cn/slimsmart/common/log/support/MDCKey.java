/*
 * All rights Reserved, tianwei7518.
 * Copyright(C) 2014-2015
 * 2013年3月14日 下午2:33:27 
 */
package cn.slimsmart.common.log.support;

/**
 * log4j 输出定义 在拦截器中加入，log4j配置中取出打印 MDC.put(MDCKey.USER_NAME.toString(),
 * userName.toString());
 * 
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
