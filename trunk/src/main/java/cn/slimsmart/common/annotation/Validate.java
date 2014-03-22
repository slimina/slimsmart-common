/*
 * All rights Reserved, tianwei7518.
 * Copyright(C) 2014-2015
 * 2013年3月14日 下午2:33:27 
 */
package cn.slimsmart.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
	String name() default "";

	int maxlength() default Integer.MAX_VALUE;

	int minlength() default 0;

	String message() default "";

	CommType[] types() default {};

	// 支持正则表达式
	Regex[] Regexs() default {};
}
