package cn.slimsmart.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.slimsmart.common.annotation.support.Types;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cell {
	String title() default "";
	
	int columnIndex();

	String defaultValue() default "";
	int type() default Types.STRING;
	
	int maxlength() default Integer.MAX_VALUE;
	int minlength() default 0;

	CommType[] types() default {};

	// 支持正则表达式
	Regex[] Regexs() default {};
}
