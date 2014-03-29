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
	String display() default "";
	
	int width() default 10;
	int index();
	int length() default 1;

	String defaultValue() default "";
	int type() default Types.STRING;
	
	//选择的模板数据 以分号";"分隔 
	String selectTmplData() default "";
	String cellTipMsg() default "";
	
	//时间、日期转换格式
	String dataFormat() default "";
	
	int maxlength() default Integer.MAX_VALUE;
	int minlength() default 0;
	//校验
	CommType[] validateTypes() default {};
	// 支持正则表达式
	Regex[] Regexs() default {};
}
