/*
 * All rights Reserved, tianwei7518.
 * Copyright(C) 2014-2015
 * 2013年3月14日 下午2:33:27 
 */
package cn.slimsmart.common.random;


/**
 * 获取UUID字符串
 * @author Zhu.TW
 *
 */
public final class UUID {
	
	private UUID(){}

	public static String getLowerCaseUUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	public static String getUpperCaseUUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
