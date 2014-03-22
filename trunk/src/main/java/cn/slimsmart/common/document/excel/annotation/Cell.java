package cn.slimsmart.common.document.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.slimsmart.common.document.excel.annotation.support.Types;
import cn.slimsmart.common.validate.annotation.CommType;
import cn.slimsmart.common.validate.annotation.Regex;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cell {
	String title() default "";
	int columnWidth() default 15;
	int columnIndex();

	String defaultValue() default "";
	int type() default Types.STRING;
	
	int maxlength() default Integer.MAX_VALUE;
	int minlength() default 0;
	
	//时间、日期转换格式
	String dataFormat() default "";

	CommType[] types() default {};

	// 支持正则表达式
	Regex[] Regexs() default {};
}
