package cn.slimsmart.common.util.annotation;

import java.lang.annotation.Annotation;

/**
 * 获取注解工具类，包含Declared的为公共、保护、默认（包）访问和私有字段，但不包括继承的 不包含为公共的
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AnnotationUtil {

	private AnnotationUtil() {
	}

	// 所有公共注解
	public static Annotation[] getTypeAnnotations(Class clazz) {
		return clazz.getAnnotations();
	}

	// 所有公共、保护、默认（包）访问和私有字段，但不包括继承的注解
	public static Annotation[] getTypeDeclaredAnnotations(Class clazz) {
		return clazz.getDeclaredAnnotations();
	}

	public static Annotation getTypeAnnotation(Class clazz, Class annotationClass) {
		return clazz.getAnnotation(annotationClass);
	}

	public static Annotation[] getMethodAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getMethod(methodName, parameterTypes).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredMethodAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredMethodDeclaredAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getMethodDeclaredAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getMethod(methodName, parameterTypes).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[][] getMethodParameterAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getMethod(methodName, parameterTypes).getParameterAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[][] getDeclaredMethodParameterAnnotations(Class clazz, String methodName, Class... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes).getParameterAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getMethodAnnotation(Class clazz, Class annotationClass, String methodName, Class... parameterTypes) {
		try {
			return clazz.getMethod(methodName, parameterTypes).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getDeclaredMethodAnnotation(Class clazz, Class annotationClass, String methodName, Class... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getFieldAnnotations(Class clazz, String fieldName) {
		try {
			return clazz.getField(fieldName).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredFieldAnnotations(Class clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredFieldDeclaredAnnotations(Class clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getFieldDeclaredAnnotations(Class clazz, String fieldName) {
		try {
			return clazz.getField(fieldName).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getFieldAnnotation(Class clazz, String fieldName, Class annotationClass) {
		try {
			return clazz.getField(fieldName).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getDeclaredFieldAnnotation(Class clazz, String fieldName, Class annotationClass) {
		try {
			return clazz.getDeclaredField(fieldName).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getConstructorAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getConstructor(parameterTypes).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredConstructorAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getDeclaredConstructor(parameterTypes).getAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getDeclaredConstructorDeclaredAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getDeclaredConstructor(parameterTypes).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[] getConstructorDeclaredAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getConstructor(parameterTypes).getDeclaredAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[][] getConstructorParameterAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getConstructor(parameterTypes).getParameterAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation[][] getDeclaredConstructorParameterAnnotations(Class clazz, Class... parameterTypes) {
		try {
			return clazz.getDeclaredConstructor(parameterTypes).getParameterAnnotations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getConstructorAnnotation(Class clazz, Class annotationClass, Class... parameterTypes) {
		try {
			return clazz.getConstructor(parameterTypes).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Annotation getDeclaredConstructorAnnotation(Class clazz, Class annotationClass, Class... parameterTypes) {
		try {
			return clazz.getDeclaredConstructor(parameterTypes).getAnnotation(annotationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
