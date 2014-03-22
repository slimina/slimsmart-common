package cn.slimsmart.common.document.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
	String title();
	int defaultColumnWidth() default 15;
	short defaultRowHeight() default 1;
	String author() default "";
	String comment() default "";
}
