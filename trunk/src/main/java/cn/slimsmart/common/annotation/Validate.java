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
